package cn.zhishush.finance.domainservice.repository.coin;

import java.util.List;

import cn.zhishush.finance.domain.coin.CoinGameLog;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version :1.0 CoinGameLogRepository.java, v 0.1 2018/9/28 上午9:42 lili Exp $
 */
public interface CoinGameLogRepository {
    /**
     * 查询用户的最新日志
     * @param userIds 用户id列表
     * @return List<CoinLog>
     */
    List<CoinGameLog> queryLatestLog(List<Long> userIds);
}
