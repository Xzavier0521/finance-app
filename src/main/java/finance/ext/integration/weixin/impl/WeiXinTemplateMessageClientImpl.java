package finance.ext.integration.weixin.impl;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import finance.ext.api.request.WeiXinQueryTemplateIdRequest;
import finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import finance.ext.api.response.QueryTemplateIdResponse;
import finance.ext.api.response.WeiXinTemplateMessageSendResponse;
import finance.ext.integration.config.RetrofitHttpClient;
import finance.ext.integration.weixin.WeiXinTemplateMessageClient;
import finance.ext.service.weixin.WeiXinTemplateMessageService;
import finance.core.common.enums.CodeEnum;

/**
 * <p>
 * 微信模版消息相关接口服务
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTemplateMessageClientImpl.java, v0.1 2018/10/21 8:04 PM
 *          lili Exp $
 */
@Slf4j
@Service("weiXinTemplateMessageClient")
public class WeiXinTemplateMessageClientImpl implements WeiXinTemplateMessageClient {

	@Value("${wechat.api.host}")
	private String weiXinApiHost;

	@Resource
	private RetrofitHttpClient retrofitHttpClient;

	private WeiXinTemplateMessageService create() {
		return retrofitHttpClient.build(weiXinApiHost).create(WeiXinTemplateMessageService.class);
	}

	@Override
	public QueryTemplateIdResponse getTemplateId(WeiXinQueryTemplateIdRequest request) {
		QueryTemplateIdResponse response;
		log.info("[开始获取微信消息模版id],请求参数:{}", request);
		try {
			response = create().getTemplateId(request.getAccessToken(), request).execute().body();
			log.info("[获取微信消息模版id],返回结果{}", response);
		} catch (final Exception e) {
			response = new QueryTemplateIdResponse();
			response.setErrcode(CodeEnum.systemError.getCode());
			response.setErrmsg("[获取微信消息模版id],异常:" + e.getMessage());
			log.error("[获取微信消息模版id],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束获取微信消息模版id],请求参数:{},返回结果:{}", request, response);
		return response;
	}

	@Override
	public WeiXinTemplateMessageSendResponse sendMessage(WeiXinTemplateMessageSendRequest request) {
		WeiXinTemplateMessageSendResponse response;
		log.info("[开始发送微信模版信息],请求参数:{}", request);
		try {
			response = create().sendMessage(request.getAccessToken(), request).execute().body();
			log.info("[发送微信模版信息],返回结果{}", response);
		} catch (final Exception e) {
			response = new WeiXinTemplateMessageSendResponse();
			response.setErrcode(CodeEnum.systemError.getCode());
			response.setErrmsg("[发送微信模版信息],异常:" + e.getMessage());
			log.error("[发送微信模版信息],请求参数:{},异常信息:{}", request, ExceptionUtils.getStackTrace(e));
		}
		log.info("[结束发送微信模版信息],请求参数:{},返回结果:{}", request, response);
		return response;
	}
}
