package finance.domainservice.service.register;

import java.math.BigDecimal;

/**
 * <p>登陆注册发送微信模版消息</p>
 * @author lili
 * @version $Id: RegisterSendMessageService.java, v0.1 2018/10/24 3:37 PM lili Exp $
 */
public interface RegisterSendMessageService {

    /**
     * 发送模版消息
     * @param userId 用户消息
     */
    void sendMessage(Long userId);

    /**
     * 发送佣金提醒
     * @param userId      用户id
     * @param rewardAmount 金额
     */
    void sendRewardMessage(Long userId, BigDecimal rewardAmount);
}
