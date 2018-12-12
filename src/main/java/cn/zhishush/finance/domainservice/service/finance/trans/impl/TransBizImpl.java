package cn.zhishush.finance.domainservice.service.finance.trans.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.account.IdCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.account.UserAccountDAO;
import cn.zhishush.finance.core.dal.dao.account.UserBankCardInfoDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinGameDAO;
import cn.zhishush.finance.core.dal.dao.coin.CoinLogDAO;
import cn.zhishush.finance.core.dal.dataobject.account.IdCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserBankCardInfoDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;
import cn.zhishush.finance.core.dal.dataobject.order.OrderDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.financeProfit.FinanceProfitVO;
import cn.zhishush.finance.api.model.vo.transRecord.FinanceOrderVo;
import cn.zhishush.finance.core.common.enums.*;
import cn.zhishush.finance.core.common.util.DateUtil;
import cn.zhishush.finance.domain.dto.RedisLockDto;
import cn.zhishush.finance.domain.dto.UserWithdrawDto;
import cn.zhishush.finance.domainservice.service.AbstractCoinDealMulti;
import cn.zhishush.finance.domainservice.service.finance.order.OrderBiz;
import cn.zhishush.finance.domainservice.service.finance.profit.ProfitBiz;
import cn.zhishush.finance.domainservice.service.finance.trans.TransBiz;
import cn.zhishush.finance.domainservice.service.trans.AccountService;
import cn.zhishush.finance.domainservice.service.validate.PwdValidateService;

/**
 * @author yaolei
 * @Title: TransBizImpl
 * @ProjectName finance-app
 * @Description: 交易
 * @date 2018/7/6下午1:41
 */
@Slf4j
@Component
public class TransBizImpl implements TransBiz {
    // private static final Logger logger =
    // LoggerFactory.getLogger(TransBizImpl.class);

    /** 首次登录金融家送的金额 */
    @Value("${login.money}")
    private String                money;

    @Resource
    private OrderBiz              orderBiz;
    @Resource
    private ProfitBiz             profit;
    @Resource
    private UserBankCardInfoDAO bankCardMapper;
    @Resource
    private PwdValidateService    pwdValidateService;
    @Resource
    private IdCardInfoDAO idCardInfoMapper;
    @Resource
    private UserAccountDAO accountMapper;
    @Resource
    private CoinLogDAO coinLogMapper;
    @Resource
    private CoinGameDAO coinGameMapper;

    @Resource(name = "accountService")
    private AbstractCoinDealMulti abstractAccountService;

    @Resource
    private AccountService        accountService;

    @Override
    public ResponseResult<String> withdraw(UserInfoDO userInfo, UserWithdrawDto paramDto) {
        Long userId = userInfo.getId();
        ResponseResult<String> res = ResponseResult.success("success");

        // 校验密码
        if (!pwdValidateService.validatePwd(PwdType.withdraw, userId, paramDto.getPwd())) {
            return ResponseResult.error(CodeEnum.pwdError);
        }

        UserBankCardInfoDO bankCard = bankCardMapper.selectDefaultBankCard(userId);
        if (bankCard == null) {
            return ResponseResult.error(CodeEnum.bankCardNotExist);
        }

        IdCardInfoDO idCardInfo = idCardInfoMapper.selectByUserId(userId);
        if (idCardInfo == null || AuthStatus.is_auth.getCode() != idCardInfo.getAuthStatus()) {
            return ResponseResult.error(CodeEnum.idCardNotExist);
        }

        UserAccountDO userAcc = accountMapper.getAccountByUserId(userId);
        if (userAcc.getCanWithdrawMoney().compareTo(paramDto.getMoneyDecimal()) < 0) {
            // 申请提现的金额 > 可提现的金额，不给提现
            return ResponseResult.error(CodeEnum.extendWithDrawMoney);
        }
        if (paramDto.isByCoin()) {
            // 需要金币抵扣手续费
            CoinGameDO coinGame = coinGameMapper
                .selectByTaskType(GameTaskType.exchangeFee.getCode(), GameType.activity.getCode());
            Integer coinNum = coinLogMapper.selectCoinNumByUserId(userId);
            if (coinNum < coinGame.getNum()) {
                // 抵扣手续费的金币数量不足
                return ResponseResult.error(CodeEnum.coinNumNotEnough);
            }
            // 查询 taskId
            CoinLogDO coinLog = new CoinLogDO();
            coinLog.setNum(-coinGame.getNum());
            coinLog.setTaskId(coinGame.getId());
            coinLog.setTaskName(coinGame.getTaskName());
            coinLog.setUserId(userId);
            paramDto.setCoinLog(coinLog);
        }

        // TODO 校验 order表中可提现的总额是否和用户账户的可提现的金额相等,order表中充值成功总金额是否和用户账户的冻结金额相等

        paramDto.setUser(userInfo);
        paramDto.setBankCard(bankCard);
        RedisLockDto<UserWithdrawDto, CodeEnum> redisLockDto = new RedisLockDto<>(
            userInfo.getId().toString(), paramDto, CodeEnum.succ);

        // 开始申请提现
        abstractAccountService.handle(redisLockDto);
        if (!redisLockDto.hasLock()) {
            res.setErrorCode(CodeEnum.joinFail.getCode());
            res.setErrorMessage(CodeEnum.joinFail.getMsg());
        } else {
            CodeEnum resCodeEnum = (CodeEnum) redisLockDto.getRes();
            res.setErrorCode(resCodeEnum.getCode());
            res.setErrorMessage(resCodeEnum.getMsg());
        }
        return res;
    }

    @Override
    public List<FinanceProfitVO> myProfit(Long userId, Page<FinanceProfitVO> page) {
        // TODO 分页格式化
        List<FinanceProfitVO> list = profit.myProfit(userId, page);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult<String> recharge(Long userId, String mobileNum) {
        // accountService.charge("注册", userId, new BigDecimal(money), mobileNum);
        return ResponseResult.success(null);
    }

    @Override
    public List<FinanceOrderVo> transRecords(Long userId, Page<FinanceOrderVo> page) {
        Page<OrderDO> paramPage = new Page<>(page.getPageSize(), page.getPageNum());
        orderBiz.transRecords(userId, paramPage);
        List<OrderDO> l = paramPage.getDataList();
        List<FinanceOrderVo> datalist = new ArrayList<FinanceOrderVo>();
        for (OrderDO order : l) {
            FinanceOrderVo financeOrderVo = new FinanceOrderVo();
            financeOrderVo.setMoney(order.getMoney().subtract(BigDecimal.valueOf(1.5)));
            financeOrderVo.setStatus(order.getStatus());
            financeOrderVo
                .setBankCode(order.getAccountNo().substring(order.getAccountNo().length() - 4));
            financeOrderVo.setBankName(order.getBankFullname());
            financeOrderVo.setCreateTime(
                DateUtil.dateToString(order.getCreateTime(), DateUtil.fm_yyyy_MM_dd_HHmmss));
            datalist.add(financeOrderVo);
        }
        page.setDataList(datalist);
        page.setTotalCount(paramPage.getTotalCount());
        return datalist;
    }

}
