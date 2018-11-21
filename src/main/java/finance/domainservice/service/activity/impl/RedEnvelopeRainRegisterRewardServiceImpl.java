package finance.domainservice.service.activity.impl;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.domain.user.UserInfo;
import finance.domain.user.UserInviteInfo;
import finance.domainservice.repository.CoinLogRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.service.activity.RedEnvelopeRainRegisterRewardService;

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
    private String               redEnvelopRainSwitch;

    @Value("${red.envelope.rain.rewardCoinNum")
    private Integer              rewardCoinNum;

    @Resource
    private CoinLogRepository    coinLogRepository;

    @Resource
    private UserInviteRepository userInviteRepository;

    @Override
    public void process(UserInfo userInfo) {
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
        coinLogRepository.save(userInviteInfo.getParentUserId(), rewardCoinNum, "红包雨活动邀请好友注册奖励");

    }
}
