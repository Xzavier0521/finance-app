package cn.zhishush.finance.domainservice.service.activity.query;

import cn.zhishush.finance.core.common.enums.ActivityCodeEnum;
import cn.zhishush.finance.domain.activity.ActivityConfig;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>推广活动配置查询</p>
 * 
 * @author lili
 * @version $Id: ActivityConfigQueryService.java, v0.1 2018/10/11 1:32 PM lili Exp $
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
