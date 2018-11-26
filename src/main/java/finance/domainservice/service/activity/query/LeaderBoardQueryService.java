package finance.domainservice.service.activity.query;

import java.util.List;

import finance.domain.activity.LeaderBoard;
import finance.domain.user.UserInfo;
import finance.core.common.enums.LeaderBoardTypeEnum;

/**
 * <p>
 * 排行榜查询服务
 * </p>
 * 
 * @author lili
 * @version $Id: LeaderBoardQueryService.java, v0.1 2018/10/19 5:37 PM lili Exp
 *          $
 */
public interface LeaderBoardQueryService {

	/**
	 * 查询排行榜
	 * 
	 * @param leaderBoardType
	 *            排行榜类型
	 * @param activityCode
	 *            活动代码
	 * @param num
	 *            展示记录数
	 * @return List<LeaderBoardVO>
	 */
	List<LeaderBoard> queryByType(LeaderBoardTypeEnum leaderBoardType, String activityCode, int num);

	/**
	 * 查询用户当前排行榜
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param leaderBoardType
	 *            排行榜类型
	 * @return LeaderBoard
	 */
	LeaderBoard queryCurrentLeaderBoard(UserInfo userInfo, LeaderBoardTypeEnum leaderBoardType);

}
