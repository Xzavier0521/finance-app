package cn.zhishush.finance.ext;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.zhishush.finance.core.common.constants.WeChatConstant;
import cn.zhishush.finance.domainservice.service.activity.RedEnvelopeRainNoticeService;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.ext.api.model.WeiXinTemplateData;
import cn.zhishush.finance.ext.api.request.WeiXinQueryTokenRequest;
import cn.zhishush.finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import cn.zhishush.finance.ext.api.response.WeiXinLongUrlToShortResponse;
import cn.zhishush.finance.ext.api.response.WeiXinQueryTokenResponse;
import cn.zhishush.finance.ext.api.response.WeiXinTempQrCreateResponse;
import cn.zhishush.finance.ext.api.response.WeiXinTemplateMessageSendResponse;
import cn.zhishush.finance.ext.integration.weixin.WeiXinQrCodeClient;
import cn.zhishush.finance.ext.integration.weixin.WeiXinQueryTokenClient;
import cn.zhishush.finance.ext.integration.weixin.WeiXinTemplateMessageClient;
import cn.zhishush.finance.ext.integration.weixin.WeiXinUserInfoQueryClient;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeiXinTest.java, v0.1 2018/10/21 9:17 PM lili Exp $
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class WeiXinTest {

    @Resource
    private WeiXinTemplateMessageClient  weiXinTemplateMessageClient;

    @Resource
    private WeiXinQueryTokenClient       weiXinQueryTokenClient;

    @Resource
    private WeiXinUserInfoQueryClient    weiXinUserInfoQueryClient;

    @Resource
    private WeiXinQrCodeClient           weiXinQrCodeClient;

    @Resource
    private WechatService                wechatService;

    @Resource
    private RedEnvelopeRainNoticeService redEnvelopeRainNoticeService;

    @Test
    public void testSend() {
        WeiXinTemplateMessageSendRequest template = new WeiXinTemplateMessageSendRequest();
        WeiXinQueryTokenRequest request = new WeiXinQueryTokenRequest();
        request.setAppid("wx05deb8b352f736eb");
        request.setAppsecret("161f16feacce63bfb3c9dd2501a7205d");
        template.setAccessToken(weiXinQueryTokenClient.getAccessToken(request).getAccess_token());
        template.setUrl("http://test.zhishush.cn/finance-admin/login.html");
        template.setTouser("oF5yF0gopGn-V04ex1iAFL9yDF0E");
        template.setTemplate_id("QoOhuMtAX-UICs-eCB24oIj0ro-nJ0yaR2DXjOW_wSM");
        Map<String, WeiXinTemplateData> m = new HashMap<>();
        WeiXinTemplateData first = new WeiXinTemplateData();
        first.setColor("#000000");
        first.setValue("您好，您有一条待确认订单。");
        m.put("first", first);
        WeiXinTemplateData keyword1 = new WeiXinTemplateData();
        keyword1.setColor("#328392");
        keyword1.setValue("OD0001");
        m.put("keyword1", keyword1);
        WeiXinTemplateData keyword2 = new WeiXinTemplateData();
        keyword2.setColor("#328392");
        keyword2.setValue("预定订单");
        m.put("keyword2", keyword2);
        WeiXinTemplateData keyword3 = new WeiXinTemplateData();
        keyword3.setColor("#328392");
        keyword3.setValue("大龙虾");
        m.put("keyword3", keyword3);
        WeiXinTemplateData remark = new WeiXinTemplateData();
        remark.setColor("#929232");
        remark.setValue("请及时确认订单！");
        m.put("remark", remark);
        template.setData(m);
        WeiXinTemplateMessageSendResponse response = weiXinTemplateMessageClient
            .sendMessage(template);
        log.info("{}", response);
    }

    @Test
    public void testGetAccessToken() {
        WeiXinQueryTokenRequest request = new WeiXinQueryTokenRequest();
        request.setAppid("wx05deb8b352f736eb");
        request.setAppsecret("161f16feacce63bfb3c9dd2501a7205d");
        WeiXinQueryTokenResponse response = weiXinQueryTokenClient.getAccessToken(request);
        log.info("{}", response);
    }

    @Test
    public void testUserList() {
        String accessToken = "14_kekLGK8WeFQ7U7SoC2oWYmYMZvEgCTGGwEGidPzccfGPcjTifVMSggt9k8dTMI8VvTY_RMn29WPZvz5yjU60-17GwSFkez8_ifK1fkPASdEcVrVfu2506OfT8OYa37AaCA3hL6H05h7d4x7KVLHfAAANDZ";
        weiXinUserInfoQueryClient.queryUserList(accessToken, "");
    }

    @Test
    public void testQrCode() {

        String token = wechatService.getWechatPubAccessToken();
        WeiXinTempQrCreateResponse response = weiXinQrCodeClient.createTempQr(token, null,
            "bcb68155012c4a2d9c96af07d4d51008");
        WeiXinLongUrlToShortResponse longUrlToShortResponse = weiXinQrCodeClient
            .longUrl2Short(token, StringUtils.replace(WeChatConstant.QR_GET_URL,
                WeChatConstant.WEB_CHAT_TICKET, response.getTicket()));
    }

    @Test
    public void testSendMessage() {
        redEnvelopeRainNoticeService.process();
    }
}
