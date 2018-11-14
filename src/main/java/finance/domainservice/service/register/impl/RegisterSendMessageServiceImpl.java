package finance.domainservice.service.register.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.domain.ThirdAccountInfo;
import finance.domain.UserInfo;
import finance.domain.UserInviteInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.domainservice.repository.UserInfoRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.repository.WeiXinMessageTemplateRepository;
import finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;
import finance.domainservice.service.register.RegisterSendMessageService;
import finance.core.common.util.DateUtils;

/**
 * <p>新用户注册发送模版消息</p>
 * @author lili
 * @version $Id: RegisterSendMessageServiceImpl.java, v0.1 2018/10/24 3:38 PM lili Exp $
 */
@Slf4j
@Service("registerSendMessageService")
public class RegisterSendMessageServiceImpl implements RegisterSendMessageService {

    @Resource
    private UserInfoRepository               userInfoRepository;

    @Resource
    private UserInviteRepository             userInviteRepository;
    @Resource
    private ThirdAccountInfoRepository       thirdAccountInfoRepository;
    @Resource
    private WeiXinMessageTemplateRepository  weiXinMessageTemplateRepository;
    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    /**
     * 发送模版消息
     *
     * @param userId   用户消息
     */
    @Override
    public void sendMessage(Long userId) {
        List<UserInfo> userInfoList = userInfoRepository
            .queryByCondition(Lists.newArrayList(userId));
        if (CollectionUtils.isEmpty(userInfoList)) {
            return;
        }
        UserInfo userInfo = userInfoList.get(0);
        UserInviteInfo userInviteInfo = userInviteRepository.queryByCondition(userId);
        if (Objects.isNull(userInviteInfo)) {
            log.info("用户id：{}，无邀请关系，不发送微信模版消息", userId);
            return;
        }
        // 父级绑定信息
        ThirdAccountInfo parentThirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(userInviteInfo.getParentUserId());
        if (Objects.isNull(parentThirdAccountInfo)) {
            log.info("用户id:{},未绑定open_id,不发送微信模版消息", userInviteInfo.getParentUserId());
            return;
        }
        sendFirstInviteMessage(userInfo, parentThirdAccountInfo);
        sendSecondInviteMessage(userInfo, parentThirdAccountInfo);

    }

    /**
     * 新增一级用户发送模版消息
     * @param userInfo 用户信息
     * @param parentThirdAccountInfo 父级用户绑定信息
     */
    private void sendFirstInviteMessage(UserInfo userInfo,
                                        ThirdAccountInfo parentThirdAccountInfo) {
        // 微信消息模版
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.FIRST_INVITE_NOTICE.getCode());
        weiXinTemplateMessageSendService.send(userInfo, parentThirdAccountInfo,
            weiXinMessageTemplate, Maps.newHashMap());
    }

    /**
     * 新增二级用户发送模版消息
     * @param userInfo 用户信息
     * @param parentThirdAccountInfo 父级用户绑定信息
     */
    private void sendSecondInviteMessage(UserInfo userInfo,
                                         ThirdAccountInfo parentThirdAccountInfo) {

        UserInviteInfo parentUserInviteInfo = userInviteRepository
            .queryByCondition(parentThirdAccountInfo.getUserId());
        if (Objects.isNull(parentUserInviteInfo)) {
            log.info("用户id:{},无邀请关系,不发送微信模版消息", parentThirdAccountInfo.getUserId());
            return;
        }
        ThirdAccountInfo grandThirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(parentUserInviteInfo.getParentUserId());
        if (Objects.isNull(grandThirdAccountInfo)) {
            log.info("用户id：{}没有绑定open_id，不发送微信模版消息", parentUserInviteInfo.getParentUserId());
            return;
        }
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.SECOND_INVITE_NOTICE.getCode());
        weiXinTemplateMessageSendService.send(userInfo, grandThirdAccountInfo,
            weiXinMessageTemplate, Maps.newHashMap());

    }

    /**
     * 发送佣金提醒
     * @param userId 用户信息
     * @param rewardAmount 金额
     */
    @Override
    public void sendRewardMessage(Long userId, BigDecimal rewardAmount) {
        log.info("userId:{},开始发送佣金提醒", userId);
        List<UserInfo> userInfoList = userInfoRepository
            .queryByCondition(Lists.newArrayList(userId));
        if (CollectionUtils.isEmpty(userInfoList)) {
            log.info("userId：{}用户信息不存在，不发送佣金提醒", userId);
            return;
        }
        UserInfo userInfo = userInfoList.get(0);
        ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository.queryByCondition(userId);
        if (Objects.isNull(thirdAccountInfo)) {
            log.info("userId:{},未绑定open_id,不发送佣金提醒", userId);
            return;
        }
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.BROKERAGE_ARRIVAL_NOTICE.getCode());
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("release_time", DateUtils.format(new Date(), DateUtils.LONG_WEB_FORMAT));
        parameters.put("reward_amount", rewardAmount.toString());
        weiXinTemplateMessageSendService.send(userInfo, thirdAccountInfo, weiXinMessageTemplate,
            parameters);
        log.info("userId:{},结束发送佣金提醒", userId);
    }
}
