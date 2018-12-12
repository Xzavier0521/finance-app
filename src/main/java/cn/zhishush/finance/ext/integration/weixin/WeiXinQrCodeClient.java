package cn.zhishush.finance.ext.integration.weixin;

import java.util.Map;

import cn.zhishush.finance.ext.api.response.WeiXinLongUrlToShortResponse;
import cn.zhishush.finance.ext.api.response.WeiXinTempQrCreateResponse;

/**
 * <p>
 * 微信公众号二维码
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQRCodeService.java, v0.1 2018/10/29 3:20 PM lili Exp $
 */
public interface WeiXinQrCodeClient {

	/**
	 * 创建临时带参数二维码
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param expireSeconds
	 *            该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param sceneStr
	 *            场景Id
	 * @return WeiXinTempQrCreateResponse
	 */
	WeiXinTempQrCreateResponse createTempQr(String accessToken, Long expireSeconds, String sceneStr);

	/**
	 * 创建永久二维码(字符串)
	 * 
	 * @param sceneId
	 *            场景id
	 * @param accessToken
	 *            接口调用凭证
	 * @return Map
	 */
	Map createForeverQr(Integer sceneId, String accessToken);

	/**
	 * 创建永久二维码(字符串)
	 * 
	 * @param sceneStr
	 *            场景值
	 * @param accessToken
	 *            接口调用凭证
	 * @return Map
	 */
	Map createForeverStrQr(String sceneStr, String accessToken);

	/**
	 * 长链接转短链接
	 * 
	 * @param accessToken
	 *            接口调用凭证
	 * @param longUrl
	 *            长链接
	 * @return String
	 */
	WeiXinLongUrlToShortResponse longUrl2Short(String accessToken, String longUrl);
}
