package finance.domainservice.service.wechat;

/**
 * <p>微信公众号二维码</p>
 *
 * @author lili
 * @version $Id: WeChatPubQrService.java, v0.1 2018/10/29 9:45 PM lili Exp $
 */
public interface WeChatPubQrService {

    /**
     * 生成带邀请码参数的微信公众号二维码
     * @param activityCode 活动代码 四位 0000-默认
     * @param  inviteCode 邀请码
     * @return String
     */
    String createTempQr(String activityCode, String inviteCode);

}
