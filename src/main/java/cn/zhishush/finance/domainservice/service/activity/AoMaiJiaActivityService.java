package cn.zhishush.finance.domainservice.service.activity;

import cn.zhishush.finance.api.model.vo.activity.ActivityInviteInfoVO;
import cn.zhishush.finance.api.model.vo.activity.ActivityParticipantInfoVO;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>奥买家活动</p>
 *
 * @author lili
 * @version 1.0: AoMaiJiaActivityService.java, v0.1 2018/12/18 4:10 PM PM lili Exp $
 */
public interface AoMaiJiaActivityService {

    ActivityParticipantInfoVO queryParticipantInfo(UserInfo userInfo, String inviteCode,
                                                   String activityCode);

    ActivityInviteInfoVO queryInviteInfo(UserInfo userInfo, String activityCode);

    void saveOperationEvent(UserInfo userInfo, String activityCode, String eventType);
}
