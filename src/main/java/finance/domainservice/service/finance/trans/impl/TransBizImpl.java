package finance.domainservice.service.finance.trans.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.financeProfit.FinanceProfitVO;
import finance.api.model.vo.transRecord.FinanceOrderVo;
import finance.core.common.enums.*;
import finance.core.common.util.DateUtil;
import finance.domain.dto.RedisLockDto;
import finance.domain.dto.UserWithdrawDto;
import finance.domainservice.service.AbstractCoinDealMulti;
import finance.domainservice.service.finance.order.OrderBiz;
import finance.domainservice.service.finance.profit.ProfitBiz;
import finance.domainservice.service.finance.trans.TransBiz;
import finance.domainservice.service.trans.AccountService;
import finance.domainservice.service.validate.PwdValidateService;
import finance.mapper.*;
import finance.model.po.*;

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
    //private static final Logger logger = LoggerFactory.getLogger(TransBizImpl.class);

    /** 首次登录金融家送的金额*/
    @Value("${login.money}")
    private String                     money;

    @Resource
    private OrderBiz                   orderBiz;
    @Resource
    private ProfitBiz                  profit;
    @Resource
    private FinanceUserBankCardInfoDAO bankCardMapper;
    @Resource
    private PwdValidateService         pwdValidateService;
    @Resource
    private FinanceIdCardInfoDAO       idCardInfoMapper;
    @Resource
    private FinanceUserAccountDAO      accountMapper;
    @Resource
    private FinanceCoinLogDAO          coinLogMapper;
    @Resource
    private FinanceCoinGameDAO         coinGameMapper;

    @Resource(name = "accountService")
    private AbstractCoinDealMulti      abstractAccountService;

    @Resource
    private AccountService             accountService;

    @Override
    public ResponseResult<String> withdraw(FinanceUserInfo userInfo, UserWithdrawDto paramDto) {
        Long userId = userInfo.getId();
        ResponseResult<String> res = ResponseResult.success("success");

        //校验密码
        if (!pwdValidateService.validatePwd(PwdType.withdraw, userId, paramDto.getPwd())) {
            return ResponseResult.error(finance.core.common.enums.CodeEnum.pwdError);
        }

        FinanceUserBankCardInfo bankCard = bankCardMapper.selectDefaultBankCard(userId);
        if (bankCard == null) {
            return ResponseResult.error(finance.core.common.enums.CodeEnum.bankCardNotExist);
        }

        FinanceIdCardInfo idCardInfo = idCardInfoMapper.selectByUserId(userId);
        if (idCardInfo == null || AuthStatus.is_auth.getCode() != idCardInfo.getAuthStatus()) {
            return ResponseResult.error(finance.core.common.enums.CodeEnum.idCardNotExist);
        }

        FinanceUserAccount userAcc = accountMapper.getAccountByUserId(userId);
        if (userAcc.getCanWithdrawMoney().compareTo(paramDto.getMoneyDecimal()) < 0) {
            // 申请提现的金额 > 可提现的金额，不给提现
            return ResponseResult.error(finance.core.common.enums.CodeEnum.extendWithDrawMoney);
        }
        if (paramDto.isByCoin()) {
            // 需要金币抵扣手续费
            FinanceCoinGame coinGame = coinGameMapper
                .selectByTaskType(GameTaskType.exchangeFee.getCode(), GameType.activity.getCode());
            Integer coinNum = coinLogMapper.selectCoinNumByUserId(userId);
            if (coinNum < coinGame.getNum()) {
                // 抵扣手续费的金币数量不足
                return ResponseResult.error(finance.core.common.enums.CodeEnum.coinNumNotEnough);
            }
            // 查询 taskId
            FinanceCoinLog coinLog = new FinanceCoinLog();
            coinLog.setNum(-coinGame.getNum());
            coinLog.setTaskId(coinGame.getId());
            coinLog.setTaskName(coinGame.getTaskName());
            coinLog.setUserId(userId);
            paramDto.setCoinLog(coinLog);
        }

        // TODO 校验 order表中可提现的总额是否和用户账户的可提现的金额相等,order表中充值成功总金额是否和用户账户的冻结金额相等

        paramDto.setUser(userInfo);
        paramDto.setBankCard(bankCard);
        RedisLockDto<UserWithdrawDto, finance.core.common.enums.CodeEnum> redisLockDto = new RedisLockDto<>(
            userInfo.getId().toString(), paramDto, finance.core.common.enums.CodeEnum.succ);

        // 开始申请提现
        abstractAccountService.handle(redisLockDto);
        if (!redisLockDto.hasLock()) {
            res.setErrorCode(finance.core.common.enums.CodeEnum.joinFail.getCode());
            res.setErrorMessage(finance.core.common.enums.CodeEnum.joinFail.getMsg());
        } else {
            finance.core.common.enums.CodeEnum resCodeEnum = (CodeEnum) redisLockDto.getRes();
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
        Page<FinanceOrder> paramPage = new Page<>(page.getPageSize(), page.getPageNum());
        orderBiz.transRecords(userId, paramPage);
        List<FinanceOrder> l = paramPage.getDataList();
        List<FinanceOrderVo> datalist = new ArrayList<FinanceOrderVo>();
        for (FinanceOrder order : l) {
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
