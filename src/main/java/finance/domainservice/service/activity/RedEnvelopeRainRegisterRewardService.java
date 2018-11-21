package finance.domainservice.service.activity;

import finance.domain.user.UserInfo;

/**
 * <p>红包雨活动邀请注册金额奖励</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRegisterRewardService.java, v0.1 2018/11/21 2:12 PM PM lili Exp $
 */
public interface RedEnvelopeRainRegisterRewardService {
    /**
     * 处理金币奖励
     * @param userInfo 用户信息
     */
    void process(UserInfo userInfo);
}