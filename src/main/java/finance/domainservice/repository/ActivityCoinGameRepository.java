package finance.domainservice.repository;

import finance.domain.activity.ActivityCoinGame;

/**
 * <p>
 * 活动-金币游戏
 * </p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameRepository.java, v0.1 2018/11/15 11:30 AM PM
 *          lili Exp $
 */
public interface ActivityCoinGameRepository {

	/**
	 * 保存
	 * 
	 * @param activityCoinGame
	 *            记录
	 * @return int
	 */
	int save(ActivityCoinGame activityCoinGame);

	/**
	 * 查询
	 * 
	 * @param userId
	 *            用户id
	 * @param activityCode
	 *            活动代码
	 * @param gameCode
	 *            游戏 代码
	 * @return ActivityCoinGame
	 */
	ActivityCoinGame queryByCondition(Long userId, String activityCode, String gameCode);
}
