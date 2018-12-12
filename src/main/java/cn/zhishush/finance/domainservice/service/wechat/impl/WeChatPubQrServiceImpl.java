package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.service.aliyunOss.StoreClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import cn.zhishush.finance.core.common.constants.WeChatConstant;
import cn.zhishush.finance.core.common.util.HttpClientUtil;
import cn.zhishush.finance.domain.weixin.WeCharQrInfo;
import cn.zhishush.finance.domainservice.service.wechat.WeChatPubQrService;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.ext.api.response.WeiXinTempQrCreateResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinQrCodeClient;

/**
 * <p>微信公众号二维码</p>
 * 
 * @author lili
 * @version $Id: WeChatPubQrServiceImpl.java, v0.1 2018/10/29 9:45 PM lili Exp $
 */
@Service("weChatPubQrService")
public class WeChatPubQrServiceImpl implements WeChatPubQrService {

    @Resource
    private WechatService      wechatService;
    @Resource
    private WeiXinQrCodeClient weiXinQrCodeClient;
    @Resource
    private StoreClient storeClient;

    @Override
    public WeCharQrInfo createTempQr(String activityCode, String inviteCode) {
        WeCharQrInfo weCharQrInfo = new WeCharQrInfo();
        String token = wechatService.getWechatPubAccessToken();
        WeiXinTempQrCreateResponse response = weiXinQrCodeClient.createTempQr(token,
            WeChatConstant.QR_EXPIRE_SECONDS,
            MessageFormat.format("{0}_{1}", activityCode, inviteCode));
        Preconditions.checkArgument(Objects.nonNull(response));
        Preconditions.checkArgument(StringUtils.isNotBlank(response.getTicket()));
        weCharQrInfo.setUrl(StringUtils.replace(WeChatConstant.QR_GET_URL,
            WeChatConstant.WEB_CHAT_TICKET, response.getTicket()));
        weCharQrInfo.setTicket(response.getTicket());
        InputStream inputStream = HttpClientUtil.getImageStream(weCharQrInfo.getUrl());
        String picName = weCharQrInfo.getTicket() + "wechatQR.jpg";
        storeClient.init();
        String qrUrl = storeClient.putObject(picName, inputStream);
        weCharQrInfo.setQrUrl(qrUrl);
        return weCharQrInfo;
    }
}
