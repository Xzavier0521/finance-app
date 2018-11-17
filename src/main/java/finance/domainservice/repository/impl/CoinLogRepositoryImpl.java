package finance.domainservice.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import finance.core.dal.dao.FinanceCoinLogDAO;
import finance.core.dal.dataobject.FinanceCoinLog;
import finance.domain.coin.CoinLog;
import finance.domainservice.converter.CoinLogConverter;
import finance.domainservice.repository.CoinLogRepository;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinLogRepositoryImpl.java, v 0.1 2018/9/28 上午9:33 lili Exp $
 */
@Repository("coinLogRepository")
public class CoinLogRepositoryImpl implements CoinLogRepository {

    @Resource
    private FinanceCoinLogDAO financeCoinLogDAO;

    /**
     * 查询用户的最新日志
     *
     * @param userIds 用户id列表
     * @return List<CoinLog>
     */
    @Override
    public List<CoinLog> queryLatestLog(List<Long> userIds) {
        return CoinLogConverter.convert2List(financeCoinLogDAO.queryLatestLog(userIds));
    }

    @Override
    public int save(Long userId, Integer coinNum, String reason) {
        FinanceCoinLog financeCoinLog = new FinanceCoinLog();
        financeCoinLog.setUserId(userId);
        financeCoinLog.setTaskId(userId);
        financeCoinLog.setTaskName(reason);
        financeCoinLog.setNum(coinNum);
        return financeCoinLogDAO.insertSelective(financeCoinLog);
    }

    @Override
    public Integer selectCoinNumByUserId(Long userId) {
        return financeCoinLogDAO.selectCoinNumByUserId(userId);
    }
}
