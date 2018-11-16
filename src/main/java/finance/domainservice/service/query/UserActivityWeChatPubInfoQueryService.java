package finance.domainservice.service.query;

import finance.domain.weixin.UserActivityWeChatPubInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: UserActivityWeChatPubInfoQueryService.java, v0.1 2018/11/7 4:36 PM lili Exp $
 */
public interface UserActivityWeChatPubInfoQueryService {

    /**
     * 用户活动微信数据
     * @param activityCode 活动代码
     * @param inviteCode 邀请码
     * @return UserActivityWeChatPubInfo
     */
    UserActivityWeChatPubInfo query(String activityCode, String inviteCode);

    /**
     * 查询用户本次活动取消关注数
     * @param activityCode 活动代码
     * @param inviteCode 邀请码
     * @param userId 用户id
     * @return Long
     */
    Long queryUnsubscribeNum(String activityCode, String inviteCode, Long userId);
}
