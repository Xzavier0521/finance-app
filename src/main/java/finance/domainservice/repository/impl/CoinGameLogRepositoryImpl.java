package finance.domainservice.repository.impl;

import finance.core.dal.dao.CoinGameDAO;
import finance.domain.coin.CoinGameLog;
import finance.domainservice.converter.CoinGameLogConverter;
import finance.domainservice.repository.CoinGameLogRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: CoinGameLogRepositoryImpl.java, v 0.1 2018/9/28 上午10:36 lili
 *          Exp $
 */
@Repository("coinGameLogRepository")
public class CoinGameLogRepositoryImpl implements CoinGameLogRepository {

	@Resource
	private CoinGameDAO financeCoinGameMapper;
	/**
	 * 查询用户的最新日志
	 *
	 * @param userIds
	 *            用户id列表
	 * @return List<CoinLog>
	 */
	@Override
	public List<CoinGameLog> queryLatestLog(List<Long> userIds) {
		return CoinGameLogConverter.convert2List(financeCoinGameMapper.queryLatestLog(userIds));
	}
}
