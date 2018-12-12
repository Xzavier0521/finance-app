package cn.zhishush.finance.ext.integration.weixin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.util.LogUtil;
import cn.zhishush.finance.domain.dto.WechatOpenInfoDto;
import cn.zhishush.finance.domain.dto.WechatPubAccessTokenDto;
import cn.zhishush.finance.domain.dto.WechatPubJsapiTicketDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * 调用微信服务.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月16日 下午2:31:05 hewenbin
 */
@Service("wechatClient")
public class WechatClient {
	private final static Logger logger = LoggerFactory.getLogger(WechatClient.class);
	/**
	 * 微信
	 */
	@Value("${wechat.app.appId}")
	private String wechatAppId;
	@Value("${wechat.app.secret}")
	private String wechatSecret;
	@Value("${wechat.api.host}")
	private String apiHost;
	/**
	 * 微信公众号
	 */
	@Value("${wechat.pub.app.appId}")
	private String wechatPubAppId;
	@Value("${wechat.pub.app.secret}")
	private String wechatPubSecret;

	@Resource
	private RestTemplate restClient;

	/**
	 * 获取用户的 openId 等信息.
	 * 
	 * @param code
	 * @param type
	 * @return
	 * @author hewenbin
	 * @version WechatClient.java, v1.0 2018年8月16日 下午3:15:59 hewenbin
	 * @see https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419317851&token=&lang=zh_CN
	 */
	public WechatOpenInfoDto getOpenInfo(String code, String type) {
		String url = apiHost
				+ "/sns/oauth2/access_token?code={code}&grant_type={grant_type}&appid={appid}&secret={secret}";
		ResponseEntity<String> res = null;
		WechatOpenInfoDto openInfo = null;
		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("code", code);
			paramMap.put("grant_type", "authorization_code");
			if (Constant.THIRD_PARD_WECHAT.equals(type)) {
				paramMap.put("appid", wechatAppId);
				paramMap.put("secret", wechatSecret);
			} else if (Constant.THIRD_PARD_WECHATPUB.equals(type)) {
				paramMap.put("appid", wechatPubAppId);
				paramMap.put("secret", wechatPubSecret);
			}

			res = restClient.getForEntity(url, String.class, paramMap);
			openInfo = JSON.parseObject(res.getBody(), WechatOpenInfoDto.class);
		} finally {
			logger.info(LogUtil.getFormatLog("url:" + url + ";code:" + code + " ;res:" + res, "获取wechatOpenInfo"));
		}
		return openInfo;
	}

	/**
	 * 获取微信公众号access_token
	 * 
	 * @author panzhongkang
	 * @date 2018/9/14 9:07
	 */
	public WechatPubAccessTokenDto getWechatPubAccessToken() {
		String url = apiHost + "/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
		ResponseEntity<String> res = null;
		WechatPubAccessTokenDto accessTokenDto = null;
		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("grant_type", "client_credential");
			paramMap.put("appid", wechatPubAppId);
			paramMap.put("secret", wechatPubSecret);
			res = restClient.getForEntity(url, String.class, paramMap);
			accessTokenDto = JSON.parseObject(res.getBody(), WechatPubAccessTokenDto.class);
		} finally {
			logger.info(LogUtil.getFormatLog("url:" + url + " ;res:" + res, "获取微信公众号access_token"));
		}
		return accessTokenDto;
	}

	/**
	 * 获取微信公众号jsapi_ticket
	 * 
	 * @author panzhongkang
	 * @date 2018/9/14 9:08
	 */
	public WechatPubJsapiTicketDto getWechatPubJsapiTicket(String accessToken) {
		String url = apiHost + "/cgi-bin/ticket/getticket?access_token={access_token}&type={type}";
		ResponseEntity<String> res = null;
		WechatPubJsapiTicketDto jsapiTicketDto;
		try {
			Map<String, String> paramMap = new HashMap<>();
			paramMap.put("access_token", accessToken);
			paramMap.put("type", "jsapi");
			res = restClient.getForEntity(url, String.class, paramMap);
			jsapiTicketDto = JSON.parseObject(res.getBody(), WechatPubJsapiTicketDto.class);
		} finally {
			logger.info(LogUtil.getFormatLog("res:" + res, "获取微信公众号jsapi_ticket"));
		}
		return jsapiTicketDto;
	}
}
