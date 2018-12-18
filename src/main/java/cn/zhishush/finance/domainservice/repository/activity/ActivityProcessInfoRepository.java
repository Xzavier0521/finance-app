package cn.zhishush.finance.domainservice.repository.activity;

import cn.zhishush.finance.domain.activity.ActivityProcessInfo;

/**
 * <p>活动执行记录</p>
 *
 * @author lili
 * @version 1.0: ActivityProcessInfoRepository.java, v0.1 2018/12/18 5:20 PM PM lili Exp $
 */
public interface ActivityProcessInfoRepository {

    int save(ActivityProcessInfo activityProcessInfo);

    int update(ActivityProcessInfo activityProcessInfo);

    ActivityProcessInfo query(Long userId, String activityCode);

    int queryInviteNum(Long userId, String activityCode);
}
