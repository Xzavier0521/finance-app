package finance.ext.integration.weixin;

import finance.ext.api.request.WeiXinQueryTokenRequest;
import finance.ext.api.response.WeiXinQueryTokenResponse;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryTokenClient.java, v0.1 2018/10/21 8:46 PM lili Exp $
 */
public interface WeiXinQueryTokenClient {
	/**
	 * 获取access_token
	 * 
	 * @param request
	 *            请求参数
	 * @return WeiXinQueryTokenResponse
	 */
	WeiXinQueryTokenResponse getAccessToken(WeiXinQueryTokenRequest request);
}
