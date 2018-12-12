package cn.zhishush.finance.domainservice.service.activity;

/**
 * <p>排行榜数据更新到缓存</p>
 * @author lili
 * @version 1.0: LeaderBoardDataSyncToCacheService.java, v0.1 2018/11/26 9:32 AM lili Exp $
 */
public interface LeaderBoardDataSyncToCacheService {

	/**
	 * 一级邀请人数排行榜
	 * 
	 * @param parentUserId
	 *            上级用户ID
	 */
	void fistLeaderBoardSync(Long parentUserId);

	/**
	 * 二级邀请人数排行榜
	 * 
	 * @param grandUserId
	 *            上级的上级用户id
	 */
	void secondLeaderBoardSync(Long grandUserId);

	/**
	 * 邀请人数总榜
	 * 
	 * @param userId
	 *            上级用户ID
	 */
	void allLeaderBoardSync(Long userId);
}
