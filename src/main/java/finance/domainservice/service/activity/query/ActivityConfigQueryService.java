package finance.domainservice.service.activity.query;

import finance.domain.activity.ActivityConfig;
import finance.domain.user.UserInfo;
import finance.core.common.enums.ActivityCodeEnum;

/**
 * <p>
 * 推广活动配置查询
 * </p>
 * 
 * @author lili
 * @version $Id: ActivityConfigQueryService.java, v0.1 2018/10/11 1:32 PM lili
 *          Exp $
 */
public interface ActivityConfigQueryService {

	/**
	 * 根据活动代码查询用户推广信息
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param activityCode
	 *            活动代码
	 * @return String
	 */
	ActivityConfig querySpreadUrlByCode(UserInfo userInfo, ActivityCodeEnum activityCode);
}
