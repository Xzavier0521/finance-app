package cn.zhishush.finance.domainservice.service.trans.impl;

import java.math.BigDecimal;
import java.util.Random;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.account.UserAccountDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinLogDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinMoneyLogDAO;
import cn.zhishush.finance.core.dal.dao.order.OrderDAO;
import cn.zhishush.finance.core.dal.dao.order.ProfitDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserBankCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinMoneyLogDO;
import cn.zhishush.finance.core.dal.dataobject.order.OrderDO;
import cn.zhishush.finance.core.dal.dataobject.order.ProfitDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.enums.OrderStatus;
import cn.zhishush.finance.core.common.enums.OrderType;
import cn.zhishush.finance.domain.dto.RedisLockDto;
import cn.zhishush.finance.domain.dto.UserWithdrawDto;
import cn.zhishush.finance.domainservice.service.AbstractCoinDealMulti;
import cn.zhishush.finance.domainservice.service.trans.AccountService;

/**
 * @author hewenbin
 * @version v1.0 2018年8月22日 下午2:54:15 hewenbin
 */
@Service("accountService")
public class AccountServiceImpl extends AbstractCoinDealMulti implements AccountService {
    /** 转账手续费 */
    @Value("${beecloud.exchangeFee}")
    private BigDecimal      exchangeFee;

    @Resource
    private UserAccountDAO accountMapper;
    @Resource
    private OrderDAO orderMapper;
    @Resource
    private ProfitDAO profitMapper;
    @Resource
    private IdCardInfoDAO idCardInfoMapper;
    @Resource
    private CoinLogDAO coinLogMapper;
    @Resource
    private CoinMoneyLogDAO coinMoneyLogMapper;

    @Override
    public ResponseResult<UserAccountDO> createAccountInfo(Long userId) {
        UserAccountDO account = new UserAccountDO();
        account.setUserId(userId);
        account.setStatus("0");
        account.setIncomeMoney(BigDecimal.ZERO);
        account.setCanWithdrawMoney(BigDecimal.ZERO);
        account.setWithdrawedMoney(BigDecimal.ZERO);
        account.setSumChargeMoney(BigDecimal.ZERO);
        accountMapper.insertSelective(account);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void charge(String reason, Long userId, BigDecimal addMoney, String mobileNum) {
        // 账户充值
        accountMapper.chargeMoney(userId, addMoney);

        // 记录分润信息(为了能够在返现记录中查询)
        ProfitDO profit = new ProfitDO();
        profit.setTerminalId(userId);
        profit.setProdId(0L);
        profit.setProdName(reason);
        profit.setStatus("00"); // ExcelDetailStatus.success.getCode()
        profit.setTerminalPhone(mobileNum);
        profit.setDetailId(0L);
        profit.setTerminalMoney(addMoney);
        profitMapper.insertSelective(profit);

        // 记录流水
        OrderDO order = new OrderDO();
        Random rand = new Random();
        int h = rand.nextInt(899) + 100;
        order.setProfitId(profit.getId());
        order.setSerialId(System.currentTimeMillis() + "" + h);
        order.setStatus(OrderStatus.rechargeSuccess.getCode());
        order.setUserId(userId);
        order.setMoney(addMoney);
        order.setTransType(OrderType.charge.getCode());
        orderMapper.insertSelective(order);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void dealCoinTask(@SuppressWarnings("rawtypes") RedisLockDto redisLockDto) {
        UserWithdrawDto paramDto = (UserWithdrawDto) redisLockDto.getParam();
        UserInfoDO user = paramDto.getUser();
        UserBankCardInfoDO bankCard = paramDto.getBankCard();
        CoinLogDO coinLog = paramDto.getCoinLog();

        /* 判断是否够扣减 */
        if (paramDto.isByCoin()) {
            Integer coinNum = coinLogMapper.selectCoinNumByUserId(user.getId());
            if (coinNum < -coinLog.getNum()) {
                // 抵扣手续费的金币数量不足
                redisLockDto.setRes(CodeEnum.coinNumNotEnough);
                return;
            }
        }

        /* 扣账户的钱 */
        int resCount = accountMapper.countDownMoney(user.getId(), paramDto.getMoneyDecimal());

        /* 保存申请提现流水 */
        if (resCount == 1) {
            IdCardInfoDO idCard = idCardInfoMapper.selectByUserId(user.getId()); // 肯定存在，不存在不允许提现
            Random rand = new Random();
            int h = rand.nextInt(899) + 100;
            OrderDO order = new OrderDO();
            order.setUserName(idCard.getRealName());
            order.setStatus(OrderStatus.init.getCode());
            order.setUserId(user.getId());
            order.setBankCardId(bankCard.getId());
            order.setAccountNo(bankCard.getAccountNo());
            order.setBankFullname(bankCard.getBankName());
            order.setBankType(Constant.card_type_de);
            order.setTitle(Constant.transfer_title);
            order.setMoney(paramDto.getMoneyDecimal());
            order.setPhone(user.getMobileNum());
            order.setTransType(OrderType.debit.getCode());
            order.setSerialId(System.currentTimeMillis() + "" + h);
            orderMapper.insertSelective(order);

            if (paramDto.isByCoin()) {
                /* 保存金币兑换钱日志 */
                CoinMoneyLogDO coinMoneyLog = new CoinMoneyLogDO();
                coinMoneyLog.setCoinNum(-paramDto.getCoinLog().getNum()); // 原值是负数
                coinMoneyLog.setOrderId(order.getId());
                coinMoneyLog.setRemark("金币抵扣提现手续费");
                coinMoneyLog.setUserId(user.getId());
                coinMoneyLog.setMoney(exchangeFee); // XXX 手续费
                coinMoneyLogMapper.insertSelective(coinMoneyLog);

                /* 开始扣减金币 */
                coinLogMapper.insertSelective(coinLog);
            }

            redisLockDto.setRes(CodeEnum.succ);
        } else {
            // 账户余额不足，不能提现
            redisLockDto.setRes(CodeEnum.extendWithDrawMoney);
        }
    }

}
