package cn.zhishush.finance.domainservice.service.wechat;

/**
 * <p>微信服务接口</p>
 * @author lili
 * @version 1.0: WechatService.java, v0.1 2018/11/26 6:53 PM lili Exp $
 */
public interface WechatService {

    String getWechatPubAccessToken();

    String getWechatPubJsapiTicket();
}
