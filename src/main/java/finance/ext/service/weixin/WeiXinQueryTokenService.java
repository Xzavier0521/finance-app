package finance.ext.service.weixin;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import finance.ext.api.response.WeiXinQueryTokenResponse;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryTokenService.java, v0.1 2018/10/21 8:44 PM lili Exp
 *          $
 */
public interface WeiXinQueryTokenService {

	/**
	 * 获取access_token
	 * 
	 * @param appId
	 *            appID
	 * @param secret
	 *            appSecret
	 * @return WeiXinQueryTokenResponse
	 */
	@POST("cgi-bin/token?grant_type=client_credential")
	Call<WeiXinQueryTokenResponse> getAccessToken(@Query("appid") String appId, @Query("secret") String secret);
}
