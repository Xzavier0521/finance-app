package finance.web.controller.noauth.weixin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import finance.core.common.constants.WeChatConstant;
import finance.core.common.enums.OperatorTypeEnum;
import finance.core.common.util.CheckUtils;
import finance.core.common.util.MessageUtils;
import finance.domain.weixin.*;
import finance.domainservice.service.wechat.WeChatDataSynchronizeService;
import finance.domainservice.service.wechat.WeChatOpenInfoService;
import finance.core.common.util.ConvertBeanUtil;

/**
 * <p>微信-事件推送</p>
 * @author lili
 * @version $Id: WeiXinTemplateMessageNoticeController.java, v0.1 2018/10/22 2:10 PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("/weixin")
public class WeiXinNoticeController {

    @Resource
    private WeChatDataSynchronizeService weChatDataSynchronizeService;

    @Resource
    private WeChatOpenInfoService        weChatOpenInfoService;

    @Resource
    private ThreadPoolExecutor           threadPoolExecutor;

    @RequestMapping(value = "notice", method = RequestMethod.GET)
    public void noticeGet(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String ech = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        if (CheckUtils.checkSignature(signature, timestamp, nonce)) {
            log.info("校验成功");
        }
        out.print(ech);
    }

    /**
     * post方法用于接收微信服务端消息
     */
    @RequestMapping(value = "notice", method = RequestMethod.POST)
    public void noticePost(HttpServletRequest request, HttpServletResponse response) {
        try {
            processRequest(request, response);
        } catch (final Exception e) {
            log.error("[处理微信通知消息异常]:{}", ExceptionUtils.getStackTrace(e));
        }
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();

        //微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp，nonce参数
        String signature = request.getParameter("signature");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //随机数
        String nonce = request.getParameter("nonce");
        //随机字符串
        String echostr = request.getParameter("echostr");
        log.info("signature:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce,
            echostr);
        Map<String, String> wxData = MessageUtils.parseXml(request);
        process(wxData, outputStream);
    }

    private void process(Map<String, String> wxData, OutputStream outputStream) {
        log.info("[收到微信服务器消息]:{}", wxData);
        if (wxData.get("MsgType") != null) {
            if ("event".equals(wxData.get("MsgType"))) {
                log.info("解析消息内容为：事件推送");
                // 用户关注消息推送
                String fromUserName;
                switch (wxData.get("Event")) {
                    case WeChatConstant.EVENT_TYPE_SUBSCRIBE:
                        fromUserName = wxData.get("FromUserName");
                        log.info("[接收到用户关注公众号推送消息],新增关注用户:{}", fromUserName);
                        // 异步执行，防止回复微信服务器超时
                        CompletableFuture.supplyAsync(() -> {
                            String eventKey = wxData.get("EventKey");
                            if (StringUtils.isNotBlank(eventKey)) {
                                String[] keys = StringUtils.split(eventKey, "_");
                                if (keys.length >= 3) {
                                    weChatOpenInfoService.save(keys[1], keys[2], fromUserName);
                                } else {
                                    log.info("无邀请代码，不绑定open_info");
                                }
                            }
                            weChatDataSynchronizeService.process(fromUserName,
                                OperatorTypeEnum.ADD);
                            return true;
                        },threadPoolExecutor);
                        outputStreamWrite(outputStream,
                            buildTextMessage(wxData, MessageUtils.welcomeText()));
                        break;
                    case WeChatConstant.EVENT_TYPE_UNSUBSCRIBE:
                        fromUserName = wxData.get("FromUserName");
                        log.info("[接收到用户取消关注公众号推送消息],取消关注用户:{}", fromUserName);
                        // 异步执行，防止回复微信服务器超时
                        CompletableFuture.supplyAsync(() -> {
                            weChatDataSynchronizeService.process(fromUserName,
                                OperatorTypeEnum.DEL);
                            return true;
                        },threadPoolExecutor);
                        break;
                    case WeChatConstant.EVENT_TYPE_CLICK:
                        // 菜单点击事件
                        processMenuClick(wxData, outputStream);
                        break;
                    default:
                }
            }
        } else {
            outputStreamWrite(outputStream, "");
        }
    }

    private void processMenuClick(Map<String, String> wxData, OutputStream outputStream) {
        String message;
        switch (wxData.get("EventKey")) {
            case WeChatConstant.JINRONGJIA_CUSTOMER_SERVICE:
                message = buildTextMessage(wxData, WeChatConstant.CUSTOMER_SERVICE_CONTENT);
                outputStreamWrite(outputStream, message);
                break;
            case WeChatConstant.JINRONGJIA_APP:
                message = buildMediaMessage(wxData, WeChatConstant.APP_MEDIA_ID);
                outputStreamWrite(outputStream, message);
                break;
            case WeChatConstant.JINRONGJIA_INTRODUCTION:
                //
                item graphicItem = item.builder().Title(WeChatConstant.WE_CHAT_PUB_TITLE)
                    .Description(WeChatConstant.WE_CHAT_PUB_DESCRIPTION)
                    .PicUrl(WeChatConstant.WE_CHAT_PUB_PIC_URL).Url(WeChatConstant.WE_CHAT_PUB_URL)
                    .build();
                String content = buildGraphicMessage(wxData, 1, Lists.newArrayList(graphicItem));
                content = StringUtils.replace(content, "finance.domain.weixin.", "");
                outputStreamWrite(outputStream, content);
                break;
            default:
        }
    }

    private String buildTextMessage(Map<String, String> wxData, String content) {
        Message message = Message.builder().FromUserName(wxData.get("ToUserName"))
            .ToUserName(wxData.get("FromUserName")).MsgType(WeChatConstant.RESP_MESSAGE_TYPE_TEXT)
            .CreateTime(System.currentTimeMillis()).build();
        TextMessage textMessage = new TextMessage();
        ConvertBeanUtil.copyBeanProperties(message, textMessage);
        textMessage.setContent(content);
        return MessageUtils.objectToXml(textMessage);
    }

    private String buildMediaMessage(Map<String, String> wxData, String meiaId) {
        Message message = Message.builder().FromUserName(wxData.get("ToUserName"))
            .ToUserName(wxData.get("FromUserName")).MsgType(WeChatConstant.RESP_MESSAGE_TYPE_IMAGE)
            .CreateTime(System.currentTimeMillis()).build();
        MediaMessage mediaMessage = new MediaMessage();
        ConvertBeanUtil.copyBeanProperties(message, mediaMessage);
        mediaMessage.setImage(Image.builder().MediaId(meiaId).build());
        return MessageUtils.objectToXml(mediaMessage);
    }

    private String buildGraphicMessage(Map<String, String> wxData, Integer articleCount,
                                       List<item> articles) {
        GraphicMessage graphicMessage = new GraphicMessage();
        Message message = Message.builder().FromUserName(wxData.get("ToUserName"))
            .ToUserName(wxData.get("FromUserName")).MsgType(WeChatConstant.RESP_MESSAGE_TYPE_NEWS)
            .CreateTime(System.currentTimeMillis()).build();
        ConvertBeanUtil.copyBeanProperties(message, graphicMessage);
        graphicMessage.setArticleCount(articleCount);
        graphicMessage.setArticles(articles);
        return MessageUtils.objectToXml(graphicMessage);
    }

    private boolean outputStreamWrite(OutputStream outputStream, String text) {
        try {
            outputStream.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (final IOException e) {
            log.error("{}", ExceptionUtils.getStackTrace(e));
            return false;
        }
        return true;
    }
}
