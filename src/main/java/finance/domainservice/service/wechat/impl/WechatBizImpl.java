package finance.domainservice.service.wechat.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import finance.domainservice.service.wechat.WechatBiz;
import finance.domainservice.service.wechat.WechatService;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: WechatBizImpl.java, v0.1 2018/11/26 6:54 PM lili Exp $
 */
@Service("wechatBiz")
public class WechatBizImpl implements WechatBiz {
    @Resource
    private WechatService wechatService;

    @Override
    public Map<String, String> getSignature(String url) {
        Map<String, String> resMap = new HashMap<>();
        // 随机字符串
        String nonceStr = UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        // 时间戳
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String jsapi_ticket = wechatService.getWechatPubJsapiTicket();
        String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonceStr + "&timestamp="
                         + timestamp + "&url=" + url;
        String signature = DigestUtils.sha1Hex(string1);

        resMap.put("nonceStr", nonceStr);
        resMap.put("timestamp", timestamp);
        resMap.put("signature", signature);
        return resMap;
    }
}
