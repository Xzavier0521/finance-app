package finance.domainservice.service.lock.impl;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseUtils;
import finance.core.dal.dao.FinanceCoinLogDAO;
import finance.core.dal.dataobject.FinanceCoinLog;
import finance.domain.coin.PayCoinCondition;
import finance.domainservice.service.lock.DistributedLockService;

/**
 * <p>支付金币进行业务操作</p>
 *
 * @author lili
 * @version 1.0: DistributedLockServiceImpl.java, v0.1 2018/11/15 10:12 PM PM lili Exp $
 */
@Service("distributedLockService")
public class DistributedLockServiceImpl implements DistributedLockService {
    @Resource
    private FinanceCoinLogDAO   financeCoinLogDAO;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private TransactionTemplate transactionTemplate;

    /**
     * 获取分布式锁
     * @param lockKey 前缀
     * @return boolean
     */
    private boolean getLock(String lockKey) {
        // 是否有正在执行的线程
        boolean hasLock = false;
        try {
            hasLock = stringRedisTemplate.opsForValue().setIfAbsent(lockKey, "ing");
            if (hasLock) {
                stringRedisTemplate.expire(lockKey, 1, TimeUnit.MINUTES);
            }
        } catch (Exception e) {
            // 避免成功获取到锁，但是未成功设置过期时间
            stringRedisTemplate.expire(lockKey, 1, TimeUnit.MINUTES);
        }
        return hasLock;
    }

    private void releaseLock(String lockKey) {
        stringRedisTemplate.delete(lockKey);
    }

    @Override
    public BasicResponse process(PayCoinCondition payCoinCondition) {
        BasicResponse response;
        String lockKey = payCoinCondition.getKeyPrefix() + payCoinCondition.getUserId();
        if (!getLock(lockKey)) {
            //未获取到锁
            return ResponseUtils.buildResp(ReturnCode.SYS_BUSY);
        }
        //获取到锁，开始处理
        try {
            // 表示获取到锁
            response = transactionTemplate.execute(status -> {
                // 校验用户金币数目
                validateCoinNum(payCoinCondition.getUserId(), payCoinCondition.getCoinNum());
                // 记录金币日志
                recordCoinLog(payCoinCondition.getUserId(), payCoinCondition.getCoinNum(),
                    payCoinCondition.getPayReason());
                //业务逻辑执行
                payCoinCondition.getFunctions()
                    .forEach(func -> func.apply(payCoinCondition.getParameter()));
                return ResponseUtils.buildResp(ReturnCode.SUCCESS);
            });
        } finally {
            releaseLock(lockKey);
        }
        return response;
    }

    /**
     * 校验用户金币数目
     * @param userId 用户id
     * @param coinNum 金币数目
     */
    private void validateCoinNum(Long userId, Integer coinNum) {
        Integer totalCoin = financeCoinLogDAO.selectCoinNumByUserId(userId);
        PreconditionUtils.checkArgument(Objects.nonNull(totalCoin), ReturnCode.COIN_NUM_NOT_ENOUGH);
        PreconditionUtils.checkArgument(totalCoin >= coinNum, ReturnCode.COIN_NUM_NOT_ENOUGH);
    }

    /**
     *  记录金币日志
     * @param userId 用户id
     * @param coinNum 金币数目
     */
    private void recordCoinLog(Long userId, Integer coinNum, String reason) {
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setTaskId(userId);
        financeCoinLog.setTaskName(reason);
        financeCoinLog.setNum(-coinNum);
        financeCoinLogDAO.insertSelective(financeCoinLog);
    }
}
