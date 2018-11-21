package finance.domainservice.service.activity.impl;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.core.common.enums.WeiXinMessageTemplateCodeEnum;
import finance.domain.user.ThirdAccountInfo;
import finance.domain.user.UserInfo;
import finance.domain.user.UserInviteInfo;
import finance.domain.weixin.WeiXinMessageTemplate;
import finance.domainservice.repository.CoinLogRepository;
import finance.domainservice.repository.ThirdAccountInfoRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.repository.WeiXinMessageTemplateRepository;
import finance.domainservice.service.activity.RedEnvelopeRainRegisterRewardService;
import finance.domainservice.service.wechat.WeiXinTemplateMessageSendService;

/**
 * <p>红包雨活动邀请注册金额奖励</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRegisterRewardServiceImpl.java, v0.1 2018/11/21 2:13 PM PM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainRegisterRewardService")
public class RedEnvelopeRainRegisterRewardServiceImpl implements
                                                      RedEnvelopeRainRegisterRewardService {

    @Value("${red.envelope.rain.switch}")
    private String                           redEnvelopRainSwitch;

    @Resource
    private CoinLogRepository                coinLogRepository;

    @Resource
    private UserInviteRepository             userInviteRepository;

    @Resource
    private WeiXinTemplateMessageSendService weiXinTemplateMessageSendService;

    @Resource
    private WeiXinMessageTemplateRepository  weiXinMessageTemplateRepository;

    @Resource
    private ThirdAccountInfoRepository       thirdAccountInfoRepository;

    @Override
    public void process(UserInfo userInfo) {
        log.info("红包雨活动邀请用户注册奖励");
        if (!"1".equals(redEnvelopRainSwitch)) {
            log.info("红包雨活动已经结束");
            return;
        }
        // 查询用户邀请关系
        UserInviteInfo userInviteInfo = userInviteRepository.queryByCondition(userInfo.getId());
        if (Objects.isNull(userInviteInfo)) {
            log.info("用户:{}无邀请关系，不发放金币奖励!", userInfo.getMobileNum());
            return;
        }
        coinLogRepository.save(userInviteInfo.getParentUserId(), 500, "红包雨活动邀请好友注册奖励");
        WeiXinMessageTemplate weiXinMessageTemplate = weiXinMessageTemplateRepository
            .query(WeiXinMessageTemplateCodeEnum.RED_ENVELOPE_RAIN_NOTICE.getCode());
        if (Objects.isNull(weiXinMessageTemplate)) {
            log.info("微信消息模版不存在，不发送模版消息！");
        }
        ThirdAccountInfo thirdAccountInfo = thirdAccountInfoRepository
            .queryByCondition(userInviteInfo.getParentUserId());
    }
}
