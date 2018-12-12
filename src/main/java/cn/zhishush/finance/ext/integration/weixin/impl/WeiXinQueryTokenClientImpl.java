package cn.zhishush.finance.ext.integration.weixin.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.ext.api.request.WeiXinQueryTokenRequest;
import cn.zhishush.finance.ext.api.response.WeiXinQueryTokenResponse;
import cn.zhishush.finance.ext.integration.config.RetrofitHttpClient;
import cn.zhishush.finance.ext.integration.weixin.WeiXinQueryTokenClient;
import cn.zhishush.finance.ext.service.weixin.WeiXinQueryTokenService;

/**
 * <p>
 * 微信接口调用获取
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryTokenClientImpl.java, v0.1 2018/10/21 8:49 PM lili
 *          Exp $
 */
@Slf4j
@Service("weiXinQueryTokenClient")
public class WeiXinQueryTokenClientImpl implements WeiXinQueryTokenClient {

	@Value("${wechat.api.host}")
	private String weiXinApiHost;

	@Resource
	private RetrofitHttpClient retrofitHttpClient;

	private WeiXinQueryTokenService create() {
		return retrofitHttpClient.build(weiXinApiHost).create(WeiXinQueryTokenService.class);
	}

	/**
	 * 获取access_token
	 *
	 * @param request
	 *            请求参数
	 * @return WeiXinQueryTokenResponse
	 */
	@Override
	public WeiXinQueryTokenResponse getAccessToken(WeiXinQueryTokenRequest request) {
		WeiXinQueryTokenResponse response;
		log.info("[开始获取微信access_token],请求参数:{}", request);
		try {
			response = create().getAccessToken(request.getAppid(), request.getAppsecret()).execute().body();
			log.info("[获取微信access_token],返回结果{}", response);
		} catch (final Exception e) {
			response = new WeiXinQueryTokenResponse();
			response.setErrcode(CodeEnum.systemError.getCode());
			response.setErrmsg("[获取微信access_token],异常:" + e.getMessage());
			log.error("[获取微信access_token],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束获取微信access_token],请求参数:{},返回结果:{}", request, response);
		return response;
	}
}
