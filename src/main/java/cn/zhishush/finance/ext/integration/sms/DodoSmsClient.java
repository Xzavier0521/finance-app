package cn.zhishush.finance.ext.integration.sms;

import cn.zhishush.finance.api.model.response.BasicResponse;

/**
 * <p>动动客短信通道</p>
 *
 * @author lili
 * @version 1.0: DodoSmsClient.java, v0.1 2018/11/25 7:09 PM PM lili Exp $
 */
public interface DodoSmsClient {

    /**
     * 发送短信
     * @param mobilePhone 手机号码
     * @param message 短信内容
     * @return BasicResponse
     */
    BasicResponse sendSms(String mobilePhone, String message);

}
