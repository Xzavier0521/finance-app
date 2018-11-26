package finance.ext.service.sms;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <p>动动客短信通道</p>
 *
 * @author lili
 * @version 1.0: DodoSmsService.java, v0.1 2018/11/25 7:12 PM PM lili Exp $
 */
public interface DodoSmsService {

    /**
     *
     * @param uid  用户编号,登录用户ID
     * @param auth 签权验证,MD5(企业代码+用户密码),32位加密小写
     * @param mobile 被叫用户，同时发送给多个用户，号码间用逗号分隔
     * Get:  最大支持300，
     * Post: 最大支持2000
     * @param msg  下行消息消息内容,默认GBK编码,使用其他编码,设encode=编码,服务器在收到消息时,取参数encode进行解码,内容长度不能超过603,
     *             (单个中文字符或英文字符或符号计算为1个长度)
     * @param expid 拓展码 允许用户自行拓展3位,1-999.expid=0表示不拓展.
     * @param encode  若使用非默认编码,需设置此参数.如encode=utf-8
     */
    @GET("hy")
    Call<Void> sendSms(@Query("uid") String uid, @Query("auth") String auth,
                       @Query("mobile") String mobile, @Query("msg") String msg,
                       @Query("expid") String expid, @Query("encode") String encode);
}
