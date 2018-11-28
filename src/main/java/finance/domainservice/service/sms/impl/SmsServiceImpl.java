package finance.domainservice.service.sms.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.http.client.fluent.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.LogUtil;
import finance.domainservice.service.sms.SmsService;

/**
 * <p>发送短信</p>
 * 
 * @author lili
 * @version 1.0: SmsServiceImpl.java, v0.1 2018/11/24 8:40 PM lili Exp $
 */
@Service("smsService")
public class SmsServiceImpl implements SmsService {
    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${sms.send.switch}")
    private String              sendSwitch;

    @Value("${sms.url}")
    private String              sendMsgUrl;

    @Value("${sms.username}")
    private String              userName;

    @Value("${sms.password}")
    private String              password;

    @Value("${sms.extendCode}")
    private String              extendCode;

    /**
     * 计算签名(摘要)
     */
    private static String calculateSign(String username, String password, String timestamp,
                                        byte[] message) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(username.getBytes(StandardCharsets.UTF_8));
        md.update(password.getBytes(StandardCharsets.UTF_8));
        md.update(timestamp.getBytes(StandardCharsets.UTF_8));
        md.update(message);
        byte[] md5result = md.digest();
        return Base64.encodeBase64String(md5result);
    }

    @Override
    public ResponseResult<String> sendMsg(String mobile, String message) {
        if (!"1".equals(sendSwitch)) {
            logger.info(LogUtil.getFormatLog("sms.send.switch=" + sendSwitch, "短信发送开关关闭，不发送短信"));
            return ResponseResult.error(CodeEnum.succ);
        }

        try {
            byte[] messageBytes = message.getBytes("GB18030");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String timestamp = sdf.format(new java.util.Date());
            String sign = calculateSign(userName, password, timestamp, messageBytes);

            // 装配GET所需的参数
            StringBuilder sb = new StringBuilder(2000);
            sb.append(sendMsgUrl);
            sb.append("?dc=15"); // 表明发送的是中文dc=15
            sb.append("&sm=").append(Hex.encodeHexString(messageBytes)); // HEX方式
            sb.append("&da=").append(mobile);
            sb.append("&sa=").append(extendCode);
            sb.append("&un=").append(userName);
            sb.append("&pw=").append(URLEncoder.encode(sign, "utf8")); // 这里使用签名,不是密码
            sb.append("&ts=").append(timestamp); // 指示服务器使用签名(数字摘要)验证方式
            sb.append("&tf=0"); // 表示短信内容为 HEX
            sb.append("&rd=1"); // 需要状态报告
            /**
             * 成功 id=<消息编号> 或者 r=0&id=<消息编号> 失败 r=<错误码>
             */
            String returnRes = Request.Get(sb.toString()).execute().returnContent().asString();
            if (!StringUtils.isEmpty(returnRes)) {
                String[] returnArray = returnRes.split("&");
                Map<String, String> resMap = new HashMap<>();
                for (String string : returnArray) {
                    String[] valueArray = string.split("=");
                    if (valueArray != null && valueArray.length == 2) {
                        resMap.put(valueArray[0], valueArray[1]);
                    }
                }
                if ("0".equals(resMap.get("r")) || resMap.get("id") != null) {
                    // 表示成功
                    return ResponseResult.success(resMap.get("id"));
                }
            }
            logger.warn(
                LogUtil.getFormatLog("req:" + sb.toString() + ";resp:" + returnRes, "短信发送失败"));

            ResponseResult<String> res = ResponseResult.error(CodeEnum.sendMsgError);
            res.setData(returnRes);// 特殊处理
            return res;
        } catch (Exception e) {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("mobile", mobile);
            paramMap.put("message", message);
            logger.error(LogUtil.getFormatLog(paramMap, "短信发送异常"), e);
            return ResponseResult.error(CodeEnum.sendMsgError);
        }
    }

}
