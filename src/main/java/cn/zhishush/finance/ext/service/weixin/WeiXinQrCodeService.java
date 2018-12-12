package cn.zhishush.finance.ext.service.weixin;

import cn.zhishush.finance.ext.api.response.WeiXinLongUrlToShortResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import cn.zhishush.finance.ext.api.request.WeiXinLongUrlToShortRequest;
import cn.zhishush.finance.ext.api.request.WeiXinTempQrCreateRequest;
import cn.zhishush.finance.ext.api.response.WeiXinTempQrCreateResponse;

/**
 * <p>
 * 微信公众号二维码
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQrCodeService.java, v0.1 2018/10/29 3:25 PM lili Exp $
 */
public interface WeiXinQrCodeService {

	/**
	 * 生产临时二维码
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param request
	 *            请求参数
	 * @return Call<WeiXinTempQrCreateResponse>
	 */
	@POST("cgi-bin/qrcode/create")
	Call<WeiXinTempQrCreateResponse> createTempQr(@Query("access_token") String accessToken,
			@Body WeiXinTempQrCreateRequest request);

	/**
	 * 长链接转短链接接口
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param request
	 *            请求参数
	 * @return Call<WeiXinLongUrlToShortRequest>
	 */
	@POST("cgi-bin/shorturl")
	Call<WeiXinLongUrlToShortResponse> longUrl2Short(@Query("access_token") String accessToken,
			@Body WeiXinLongUrlToShortRequest request);
}
