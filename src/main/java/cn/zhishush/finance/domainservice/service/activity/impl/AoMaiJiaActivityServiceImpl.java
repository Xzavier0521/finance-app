package cn.zhishush.finance.domainservice.service.activity.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.vo.activity.ActivityDailyInviteInfoVO;
import cn.zhishush.finance.api.model.vo.activity.ActivityInviteInfoVO;
import cn.zhishush.finance.api.model.vo.activity.ActivityParticipantInfoVO;
import cn.zhishush.finance.api.model.vo.weixin.WeiXinUserInfoDetailVO;
import cn.zhishush.finance.core.common.enums.ActivityEventTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.domain.activity.ActivityDailyInviteInfo;
import cn.zhishush.finance.domain.activity.ActivityProcessInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.user.UserInviteInfo;
import cn.zhishush.finance.domainservice.repository.activity.ActivityProcessInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInfoRepository;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.domainservice.service.activity.AoMaiJiaActivityService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinUserInfoQueryService;

import com.google.common.collect.Lists;

/**
 * <p>奥买家活动</p>
 *
 * @author lili
 * @version 1.0: AoMaiJiaActivityServiceImpl.java, v0.1 2018/12/18 4:11 PM PM lili Exp $
 */
@Slf4j
@Service("aoMaiJiaActivityService")
public class AoMaiJiaActivityServiceImpl implements AoMaiJiaActivityService {

    @Resource
    private ActivityProcessInfoRepository activityProcessInfoRepository;

    @Resource
    private UserInfoRepository            userInfoRepository;
    @Resource
    private UserInviteInfoRepository      userInviteInfoRepository;

    @Resource
    private WeiXinUserInfoQueryService    weiXinUserInfoQueryService;

    @Override
    public ActivityParticipantInfoVO queryParticipantInfo(UserInfo userInfo, String inviteCode,
                                                          String activityCode) {
        ActivityParticipantInfoVO activityParticipantInfoVO = new ActivityParticipantInfoVO();
        activityParticipantInfoVO.setUserId(userInfo.getId());
        activityParticipantInfoVO.setMobileNum(userInfo.getMobileNum());
        ActivityProcessInfo activityProcessInfo = activityProcessInfoRepository
            .query(userInfo.getId(), activityCode);
        if (Objects.nonNull(activityProcessInfo)) {
            activityParticipantInfoVO
                .setParticipate("Y".equals(activityProcessInfo.getIsParticipate()));
            activityParticipantInfoVO.setFinished("Y".equals(activityProcessInfo.getIsFinished()));
        }
        UserInviteInfo userInviteInfo = userInviteInfoRepository.queryByCondition(userInfo.getId());
        if (Objects.isNull(userInviteInfo)) {
            activityParticipantInfoVO.setSubordinate(false);
            return activityParticipantInfoVO;
        }
        UserInfo parentUserInfo = userInfoRepository.query(userInviteInfo.getParentUserId());
        if (Objects.isNull(parentUserInfo)) {
            activityParticipantInfoVO.setSubordinate(false);
            return activityParticipantInfoVO;
        }
        log.error("parentUserInfo:{}",parentUserInfo);
        activityParticipantInfoVO.setSubordinate(inviteCode.equals(parentUserInfo.getInviteCode()));
        return activityParticipantInfoVO;
    }

    @Override
    public ActivityInviteInfoVO queryInviteInfo(UserInfo userInfo, String activityCode) {
        ActivityInviteInfoVO activityInviteInfoVO = new ActivityInviteInfoVO();
        activityInviteInfoVO.setUserId(userInfo.getId());
        activityInviteInfoVO.setInviteCode(userInfo.getInviteCode());
        activityInviteInfoVO.setMobileNum(userInfo.getMobileNum());
        WeiXinUserInfoDetailVO weiXinUserInfoDetailVO = weiXinUserInfoQueryService.query(userInfo);
        if (Objects.nonNull(weiXinUserInfoDetailVO)) {
            activityInviteInfoVO.setNickName(weiXinUserInfoDetailVO.getNickname());
            activityInviteInfoVO.setHeadImgUrl(weiXinUserInfoDetailVO.getHeadimgurl());
        }
        activityInviteInfoVO.setInviteNum(
            activityProcessInfoRepository.queryInviteNum(userInfo.getId(), activityCode));
        //
        List<ActivityDailyInviteInfoVO> items = Lists.newArrayList();
        List<ActivityDailyInviteInfo> activityDailyInviteInfos = activityProcessInfoRepository
            .queryDailyInviteInfo(userInfo.getId(), activityCode);
        ConvertBeanUtil.copyListBeanPropertiesToList(activityDailyInviteInfos, items,
            ActivityDailyInviteInfoVO.class);
        activityInviteInfoVO.setItems(items);
        return activityInviteInfoVO;
    }

    @Override
    public void saveOperationEvent(UserInfo userInfo, String activityCode, String eventType) {
        boolean isNew = false;
        ActivityProcessInfo activityProcessInfo = activityProcessInfoRepository
            .query(userInfo.getId(), activityCode);
        ActivityEventTypeEnum activityEventType = ActivityEventTypeEnum.getByCode(eventType);
        PreconditionUtils.checkArgument(Objects.nonNull(activityEventType), ReturnCode.SYS_ERROR);
        if (Objects.isNull(activityProcessInfo)) {
            activityProcessInfo = new ActivityProcessInfo();
            activityProcessInfo.setActivityCode(activityCode);
            activityProcessInfo.setUserId(userInfo.getId());
            activityProcessInfo.setMobileNum(userInfo.getMobileNum());
            isNew = true;
        }
        switch (activityEventType) {
            case JOIN:
                activityProcessInfo.setIsParticipate("Y");
                break;
            case DONE:
                activityProcessInfo.setIsFinished("Y");
                break;
            case OLDER_JOIN:
                activityProcessInfo.setIsPromoter("Y");
                break;
            default:
        }
        if (isNew) {
            activityProcessInfoRepository.save(activityProcessInfo);
        } else {
            activityProcessInfoRepository.update(activityProcessInfo);
        }

    }

}
