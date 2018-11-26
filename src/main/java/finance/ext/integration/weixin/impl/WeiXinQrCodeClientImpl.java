package finance.ext.integration.weixin.impl;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.ext.api.model.ActionInfo;
import finance.ext.api.model.Scene;
import finance.ext.api.request.WeiXinLongUrlToShortRequest;
import finance.ext.api.request.WeiXinTempQrCreateRequest;
import finance.ext.api.response.WeiXinLongUrlToShortResponse;
import finance.ext.api.response.WeiXinTempQrCreateResponse;
import finance.ext.integration.config.RetrofitHttpClient;
import finance.ext.integration.weixin.WeiXinQrCodeClient;
import finance.ext.service.weixin.WeiXinQrCodeService;
import finance.core.common.enums.CodeEnum;

/**
 * <p>
 * 微信公众号二维码
 * </p>
 *
 * @author lili
 * @version $Id: WeiXinQrCodeClientImpl.java, v0.1 2018/10/29 3:39 PM lili Exp $
 */
@Slf4j
@Service("weiXinQrCodeClient")
public class WeiXinQrCodeClientImpl implements WeiXinQrCodeClient {

	@Value("${wechat.api.host}")
	private String weiXinApiHost;

	@Resource
	private RetrofitHttpClient retrofitHttpClient;

	private WeiXinQrCodeService create() {
		return retrofitHttpClient.build(weiXinApiHost).create(WeiXinQrCodeService.class);
	}

	/**
	 * 创建临时带参数二维码
	 *
	 * @param accessToken
	 *            接口调用凭证
	 * @param expireSeconds
	 *            该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒。
	 * @param sceneStr
	 *            场景Id
	 * @return String
	 */
	@Override
	public WeiXinTempQrCreateResponse createTempQr(String accessToken, Long expireSeconds, String sceneStr) {
		WeiXinTempQrCreateResponse response;
		WeiXinTempQrCreateRequest request = new WeiXinTempQrCreateRequest();
		request.setAccessToken(accessToken);
		request.setExpire_seconds(Objects.nonNull(expireSeconds) ? expireSeconds : 2592000L);
		request.setAction_name("QR_STR_SCENE");
		request.setAction_info(ActionInfo.builder().scene(Scene.builder().scene_str(sceneStr).build()).build());
		log.info("[开始生成微信临时二维码],请求参数:{}", request);
		try {
			response = create().createTempQr(accessToken, request).execute().body();
			log.info("[生成微信临时二维码],返回结果{}", response);
		} catch (final Exception e) {
			response = new WeiXinTempQrCreateResponse();
			response.setErrcode(CodeEnum.systemError.getCode());
			response.setErrmsg("[获取微信access_token],异常:" + e.getMessage());
			log.error("[获取微信access_token],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束生成微信临时二维码],请求参数:{},返回结果:{}", request, response);
		return response;
	}

	/**
	 * 创建永久二维码(字符串)
	 *
	 * @param sceneId
	 *            场景id
	 * @param accessToken
	 *            接口调用凭证
	 * @return Map
	 */
	@Override
	public Map createForeverQr(Integer sceneId, String accessToken) {
		return null;
	}

	/**
	 * 创建永久二维码(字符串)
	 *
	 * @param sceneStr
	 *            场景值
	 * @param accessToken
	 *            接口调用凭证
	 * @return Map
	 */
	@Override
	public Map createForeverStrQr(String sceneStr, String accessToken) {
		return null;
	}

	/**
	 * 长链接转短链接
	 *
	 * @param accessToken
	 *            接口调用凭证
	 * @param longUrl
	 *            长链接
	 * @return String
	 */
	@Override
	public WeiXinLongUrlToShortResponse longUrl2Short(String accessToken, String longUrl) {
		WeiXinLongUrlToShortResponse response;
		WeiXinLongUrlToShortRequest request = new WeiXinLongUrlToShortRequest();
		request.setAccessToken(accessToken);
		request.setAction("long2short");
		request.setLong_url(longUrl);
		log.info("[开始长链接转短链接],请求参数:{}", request);
		try {
			response = create().longUrl2Short(accessToken, request).execute().body();
			log.info("[长链接转短链接],返回结果{}", response);
		} catch (final Exception e) {
			response = new WeiXinLongUrlToShortResponse();
			response.setErrcode(CodeEnum.systemError.getCode());
			response.setErrmsg("[获取微信access_token],异常:" + e.getMessage());
			log.error("[长链接转短链接],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束长链接转短链接],请求参数:{},返回结果:{}", request, response);
		return response;
	}
}
