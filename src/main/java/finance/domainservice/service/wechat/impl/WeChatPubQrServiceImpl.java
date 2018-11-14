package finance.domainservice.service.wechat.impl;

import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import finance.core.common.constant.WeChatConstant;
import finance.domainservice.service.wechat.WeChatPubQrService;
import finance.ext.api.response.WeiXinTempQrCreateResponse;
import finance.ext.integration.weixin.WeiXinQrCodeClient;
import finance.domainservice.service.wechat.WechatService;

/**
 * <p>微信公众号二维码</p>
 * @author lili
 * @version $Id: WeChatPubQrServiceImpl.java, v0.1 2018/10/29 9:45 PM lili Exp $
 */
@Service("weChatPubQrService")
public class WeChatPubQrServiceImpl implements WeChatPubQrService {

    @Resource
    private WechatService      wechatService;
    @Resource
    private WeiXinQrCodeClient weiXinQrCodeClient;

    @Override
    public String createTempQr(String activityCode, String inviteCode) {
        String token = wechatService.getWechatPubAccessToken();
        WeiXinTempQrCreateResponse response = weiXinQrCodeClient.createTempQr(token,
            WeChatConstant.QR_EXPIRE_SECONDS,
            MessageFormat.format("{0}_{1}", activityCode, inviteCode));
        Preconditions.checkArgument(Objects.nonNull(response));
        Preconditions.checkArgument(StringUtils.isNotBlank(response.getTicket()));
        return StringUtils.replace(WeChatConstant.QR_GET_URL, WeChatConstant.WEB_CHAT_TICKET,
            response.getTicket());
    }
}
