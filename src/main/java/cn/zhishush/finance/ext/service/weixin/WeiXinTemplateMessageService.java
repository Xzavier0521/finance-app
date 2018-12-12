package cn.zhishush.finance.ext.service.weixin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import cn.zhishush.finance.ext.api.request.WeiXinPrivateTemplateDelRequest;
import cn.zhishush.finance.ext.api.request.WeiXinQueryTemplateIdRequest;
import cn.zhishush.finance.ext.api.request.WeiXinSetIndustryRequest;
import cn.zhishush.finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import cn.zhishush.finance.ext.api.response.*;

/**
 * <p>微信模版消息相关接口服务</p>
 *
 * @author lili
 * @version $Id: WeiXinTemplateMessageService.java, v0.1 2018/10/21 7:59 PM lili Exp $
 */
public interface WeiXinTemplateMessageService {

    /**
     * 设置所属行业 http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN
     * 请求参数： { "industry_id1":"1", "industry_id2":"4" }
     * 
     * @param accessToken 接口调用凭证
     * @param request  请求参数
     * @return WeiXinBasicResponse
     */
    @POST("cgi-bin/template/api_set_industry")
    Call<WeiXinBasicResponse> setIndustry(@Query("access_token") String accessToken,
                                          WeiXinSetIndustryRequest request);

    /**
     * 获取设置的行业信息
     *
     * http请求方式：GET
     * https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN
     * 返回结果: { "primary_industry":{"first_class":"运输与仓储","second_class":"快递"},
     * "secondary_industry":{"first_class":"IT科技","second_class":"互联网|电子商务"} }
     * 
     * @param accessToken 接口调用凭证
     * @return WeiXinQueryIndustryResponse
     */
    @GET("cgi-bin/template/get_industry")
    Call<WeiXinQueryIndustryResponse> getIndustry(@Query("access_token") String accessToken);

    /**
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN
     * 请求参数: { "template_id_short":"TM00015" } 返回码: { "errcode":0, "errmsg":"ok",
     * "template_id":"Doclyl5uP7Aciu-qZ7mJNPtWkbkYnWBWVja26EGbNyk" } 获取模版id
     *
     * @param accessToken 接口调用凭证 	
     * @param request 请求参数
     * @return String
     */

    @POST("cgi-bin/template/api_add_template")
    Call<QueryTemplateIdResponse> getTemplateId(@Query("access_token") String accessToken,
                                                @Body WeiXinQueryTemplateIdRequest request);

    /**
     * 获取模板列表 http请求方式：GET
     * https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN
     * 返回结果: { "template_list": [{ "template_id":
     * "iPk5sOIt5X_flOVKn5GrTFpncEYTojx6ddbt8WYoV5s", "title": "领取奖金提醒",
     * "primary_industry": "IT科技", "deputy_industry": "互联网|电子商务", "content": "{
     * {result.DATA} }\n\n领奖金额:{ {withdrawMoney.DATA} }\n领奖 时间:{ {withdrawTime.DATA}
     * }\n银行信息:{ {cardInfo.DATA} }\n到账时间: { {arrivedTime.DATA} }\n{ {remark.DATA}
     * }", "example": "您已提交领奖申请\n\n领奖金额：xxxx元\n领奖时间：2013-10-10
     * 12:22:22\n银行信息：xx银行(尾号xxxx)\n到账时间：预计xxxxxxx\n\n预计将于xxxx到达您的银行卡" }] }
     * 
     * @param accessToken 获取模板列表
     * @return WeiXinPrivateTemplateResponse
     */
    @GET("cgi-bin/template/get_all_private_template")
    Call<WeiXinPrivateTemplateResponse> getAllPrivateTemplate(@Query("access_token") String accessToken);

    /**
     * 删除模版 接口调用请求说明
     *
     * http请求方式：POST
     * https://api.weixin.qq.com/cgi-bin/template/del_private_template?access_token=ACCESS_TOKEN
     * POST数据说明如下：
     *
     * { "template_id" : "Dyvp3-Ff0cnail_CDSzk1fIc6-9lOkxsQE7exTJbwUE" } 返回说明
     *
     * 在调用接口后，会返回JSON数据包。正常时的返回JSON数据包示例：
     *
     * { "errcode" : 0, "errmsg" : "ok" }
     * 
     * @param accessToken 接口调用凭证
     * @param request 请求参数
     * @return WeiXinBasicResponse
     */
    @POST("cgi-bin/template/del_private_template")
    Call<WeiXinBasicResponse> delPrivateTemplate(@Query("access_token") String accessToken,
                                                 @Body WeiXinPrivateTemplateDelRequest request);

    /**
     * http请求方式: POST
     * https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
     * 请求参数： { "touser":"OPENID",
     * "template_id":"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY",
     * "url":"http://weixin.qq.com/download", "miniprogram":{
     * "appid":"xiaochengxuappid12345", "pagepath":"index?foo=bar" }, "data":{
     * "first": { "value":"恭喜你购买成功！", "color":"#173177" }, "keyword1":{
     * "value":"巧克力", "color":"#173177" }, "keyword2": { "value":"39.8元",
     * "color":"#173177" }, "keyword3": { "value":"2014年9月22日", "color":"#173177" },
     * "remark":{ "value":"欢迎再次购买！", "color":"#173177" } } } 返回结果: { "errcode":0,
     * "errmsg":"ok", "msgid":200228332 } 发送模板消息
     * 
     * @param accessToken 接口调用凭证 	
     * @param request 请求参数
     * @return WeiXinTemplateMessageSendResponse
     */
    @POST("cgi-bin/message/template/send")
    Call<WeiXinTemplateMessageSendResponse> sendMessage(@Query("access_token") String accessToken,
                                                        @Body WeiXinTemplateMessageSendRequest request);
}
