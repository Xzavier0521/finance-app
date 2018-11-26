package finance.domainservice.repository;

import finance.domain.coin.CoinGameLog;

import java.util.List;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version :1.0 CoinGameLogRepository.java, v 0.1 2018/9/28 上午9:42 lili Exp $
 */
public interface CoinGameLogRepository {
	/**
	 * 查询用户的最新日志
	 * 
	 * @param userIds
	 *            用户id列表
	 * @return List<CoinLog>
	 */
	List<CoinGameLog> queryLatestLog(List<Long> userIds);
}
