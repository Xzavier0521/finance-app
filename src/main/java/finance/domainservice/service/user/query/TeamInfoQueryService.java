package finance.domainservice.service.user.query;

import finance.domain.team.TeamInfoQueryResult;
import finance.core.common.enums.CustomerQueryTypeEnum;

/**
 * @author lili
 */
public interface TeamInfoQueryService {

	/**
	 * 查询用户的好友列表-一级和二级用户
	 * 
	 * @param userId
	 *            用户id
	 * @param type
	 *            用户类型
	 * @param maxCount
	 *            最大记录数
	 * @return TeamInfoQueryResult
	 */
	TeamInfoQueryResult queryTeamUserList(Long userId, CustomerQueryTypeEnum type, int maxCount);
}
