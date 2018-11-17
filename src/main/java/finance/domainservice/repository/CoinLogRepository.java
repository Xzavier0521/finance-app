package finance.domainservice.repository;

import java.util.List;

import finance.domain.coin.CoinLog;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version :1.0 CoinLogRepository.java, v 0.1 2018/9/28 上午9:24 lili Exp $
 */
public interface CoinLogRepository {

    /**
     *  查询用户的最新日志
     * @param userIds 用户id列表
     * @return  List<CoinLog>
     */
    List<CoinLog> queryLatestLog(List<Long> userIds);

    int save(Long userId, Integer coinNum, String reason);

    Integer selectCoinNumByUserId(Long userId);

}
