package finance.ext.service.weixin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import finance.ext.api.model.WeiXinUserInfoDetail;
import finance.ext.api.request.WeiXinBatchQueryUserInfoRequest;
import finance.ext.api.response.UserInfoQueryResponse;
import finance.ext.api.response.WeiXinBatchQueryUserInfoResponse;

/**
 * <p>
 * 微信公众号用户信息
 * </p>
 *
 * @author lili
 * @version $Id: WeiXinUserInfoQueryService.java, v0.1 2018/10/22 11:33 AM lili
 *          Exp $
 * @apiNote https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839
 */
public interface WeiXinUserInfoQueryService {

	/**
	 * 获取用户列表 http请求方式: GET（请使用https协议）
	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
	 * 返回结果: {"total":2, "count":2, "data":{ "openid":["OPENID1","OPENID2"]},
	 * "next_openid":"NEXT_OPENID" } 异常返回: {"errcode":40013,"errmsg":"invalid
	 * appid"}
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param nextOpenId
	 *            下一个用户open_id
	 * @return UserInfoQueryResponse
	 */
	@GET("cgi-bin/user/get")
	Call<UserInfoQueryResponse> queryUserList(@Query("access_token") String accessToken,
			@Query("next_openid") String nextOpenId);

	/**
	 * 获取用户基本信息（包括UnionID机制）
	 *
	 * 接口调用请求说明 http请求方式: GET
	 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
	 *
	 * 正常情况下，微信会返回下述JSON数据包给公众号： { "subscribe": 1, "openid":
	 * "o6_bmjrPTlm6_2sgVt7hMZOPfL2M", "nickname": "Band", "sex": 1, "language":
	 * "zh_CN", "city": "广州", "province": "广东", "country": "中国",
	 * "headimgurl":"http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
	 * "subscribe_time": 1382694957, "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
	 * "remark": "", "groupid": 0, "tagid_list":[128,2], "subscribe_scene":
	 * "ADD_SCENE_QR_CODE", "qr_scene": 98765, "qr_scene_str": "" }
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）: {"errcode":40013,"errmsg":"invalid
	 * appid"}
	 *
	 * @param accessToken
	 *            获取用户基本信息（包括UnionID机制）
	 * @param openId
	 *            普通用户的标识，对当前公众号唯一
	 * @param lang
	 *            返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
	 * @return Call<WeiXinUserInfoDetail>
	 */
	@GET("cgi-bin/user/info?lang=zh_CN")
	Call<WeiXinUserInfoDetail> queryUserInfo(@Query("access_token") String accessToken, @Query("openid") String openId,
			@Query("lang") String lang);

	/**
	 * 批量获取用户基本信息 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。 接口调用请求说明
	 *
	 * http请求方式: POST
	 * https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN
	 * POST数据示例
	 *
	 * { "user_list": [ { "openid": "otvxTs4dckWG7imySrJd6jSi0CWE", "lang": "zh_CN"
	 * }, { "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg", "lang": "zh_CN" } ] } 返回说明
	 *
	 * 正常情况下，微信会返回下述JSON数据包给公众号（示例中为一次性拉取了2个openid的用户基本信息，第一个是已关注的，第二个是未关注的）：
	 *
	 * { "user_info_list": [ { "subscribe": 1, "openid":
	 * "otvxTs4dckWG7imySrJd6jSi0CWE", "nickname": "iWithery", "sex": 1, "language":
	 * "zh_CN", "city": "揭阳", "province": "广东", "country": "中国",
	 *
	 * "headimgurl":
	 * "http://thirdwx.qlogo.cn/mmopen/xbIQx1GRqdvyqkMMhEaGOX802l1CyqMJNgUzKP8MeAeHFicRDSnZH7FY4XB7p8XHXIf6uJA2SCunTPicGKezDC4saKISzRj3nz/0",
	 *
	 * "subscribe_time": 1434093047, "unionid": "oR5GjjgEhCMJFyzaVZdrxZ2zRRF4",
	 * "remark": "",
	 *
	 * "groupid": 0, "tagid_list":[128,2], "subscribe_scene": "ADD_SCENE_QR_CODE",
	 * "qr_scene": 98765, "qr_scene_str": ""
	 *
	 * }, { "subscribe": 0, "openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg" } ] }
	 * 错误时微信会返回错误码等信息，JSON数据包示例如下（该示例为AppID无效错误）:
	 *
	 * {"errcode":40013,"errmsg":"invalid appid"}
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param request
	 *            请求参数
	 * @return List<WeiXinUserInfoDetail>
	 */
	@POST("cgi-bin/user/info/batchget")
	Call<WeiXinBatchQueryUserInfoResponse> batchQueryUserInfo(@Query("access_token") String accessToken,
			@Body WeiXinBatchQueryUserInfoRequest request);

}
