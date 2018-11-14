package finance.domainservice.service.wechat;

/**
 * 微信服务接口
 */
public interface WechatService {
   String getWechatPubAccessToken();

   String getWechatPubJsapiTicket();
}
