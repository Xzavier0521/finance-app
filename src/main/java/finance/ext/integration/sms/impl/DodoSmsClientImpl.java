package finance.ext.integration.sms.impl;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.MD5Util;
import finance.core.common.util.ResponseUtils;
import finance.ext.integration.config.RetrofitHttpClient;
import finance.ext.integration.sms.DodoSmsClient;
import finance.ext.service.sms.DodoSmsService;

/**
 * <p>动动客短信通道</p>
 *
 * @author lili
 * @version 1.0: DodoSmsClientImpl.java, v0.1 2018/11/25 7:11 PM PM lili Exp $
 */
@Slf4j
@Service("dodoSmsClient")
public class DodoSmsClientImpl implements DodoSmsClient {

    private static final String SMS_ENCODE = "utf-8";
    @Value("${sms.dodo.url}")
    private String              smsDodoUrl;
    @Value("${sms.dodo.code}")
    private String              smsDodoCode;
    @Value("${sms.dodo.uid}")
    private String              smsDodoUid;
    @Value("${sms.dodo.password}")
    private String              smsDodoPassword;
    @Resource
    private RetrofitHttpClient  retrofitHttpClient;

    /**
     * 计算签名
     */
    private static String calculateSign(String smsDodoCode, String smsDodoPassword) {
        return StringUtils.lowerCase(MD5Util.sign(smsDodoCode, smsDodoPassword, SMS_ENCODE));
    }

    private DodoSmsService create() {
        return retrofitHttpClient.build(smsDodoUrl).create(DodoSmsService.class);
    }

    @Override
    public BasicResponse sendSms(String mobilePhone, String message) {
        BasicResponse response;
        log.info("[开始发送短信],mobilePhone:{},message:{}", mobilePhone, message);
        try {
            String content = java.net.URLEncoder.encode(message, SMS_ENCODE);
            create().sendSms(smsDodoUid, calculateSign(smsDodoCode, smsDodoPassword), mobilePhone,
                message, "0", SMS_ENCODE).execute();
            response = ResponseUtils.buildResp(ReturnCode.SUCCESS);
        } catch (final Exception e) {
            response = ResponseUtils.buildResp(ReturnCode.SYS_ERROR);
            log.error("[短信发送异常]:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束发送短信],mobilePhone:{},message:{},返回结果:{}", mobilePhone, message, response);
        return response;
    }
}
