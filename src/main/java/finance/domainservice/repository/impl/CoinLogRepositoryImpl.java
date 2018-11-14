package finance.domainservice.repository.impl;

import finance.domain.CoinLog;
import finance.domainservice.converter.CoinLogConverter;
import finance.mapper.FinanceCoinLogDAO;
import finance.domainservice.repository.CoinLogRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinLogRepositoryImpl.java, v 0.1 2018/9/28 上午9:33 lili Exp $
 */
@Repository("coinLogRepository")
public class CoinLogRepositoryImpl implements CoinLogRepository {


    @Resource
    private FinanceCoinLogDAO financeCoinLogMapper;

    /**
     * 查询用户的最新日志
     *
     * @param userIds 用户id列表
     * @return List<CoinLog>
     */
    @Override
    public List<CoinLog> queryLatestLog(List<Long> userIds) {
        return CoinLogConverter.convert2List(financeCoinLogMapper.queryLatestLog(userIds));
    }
}
