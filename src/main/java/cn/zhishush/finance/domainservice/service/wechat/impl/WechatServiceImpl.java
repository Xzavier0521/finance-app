package cn.zhishush.finance.domainservice.service.wechat.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.zhishush.finance.domain.dto.WechatPubAccessTokenDto;
import cn.zhishush.finance.domain.dto.WechatPubJsapiTicketDto;
import cn.zhishush.finance.ext.integration.weixin.WechatClient;
import cn.zhishush.finance.domainservice.service.wechat.WechatService;
import cn.zhishush.finance.core.common.util.LogUtil;
/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: WechatServiceImpl.java, v0.1 2018/11/26 6:54 PM lili Exp $
 */
@Service("wechatService")
public class WechatServiceImpl implements WechatService {
	private static final Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

	/**
	 * 微信公众号
	 */
	@Value("${wechat.pub.app.appId}")
	private String wechatPubAppId;
	@Value("${wechat.pub.access_token.cache.timeout.minute}")
	private Long wechatPubAccessTokenTimeoutMinutes;
	@Value("${wechat.pub.access_token.cacke.key.prefix}")
	private String wechatPubAccessTokenCacheKeyPrefix;
	@Value("${wechat.pub.jsapi_ticket.cacke.key.prefix}")
	private String wechatPubJsapiTicketCacheKeyPrefix;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Resource
	private WechatClient wechatClient;

	@Override
	public String getWechatPubAccessToken() {
		Long expireTime = stringRedisTemplate.getExpire(wechatPubAccessTokenCacheKeyPrefix + wechatPubAppId,
				TimeUnit.MINUTES);
		String accessToken = null;
		if (expireTime < 30) {
			logger.info(LogUtil.getFormatLog(null, "开始获取access_token"));
			WechatPubAccessTokenDto accessTokenDto = wechatClient.getWechatPubAccessToken();
			if (accessTokenDto != null && StringUtils.isEmpty(accessTokenDto.getErrcode())) {
				accessToken = accessTokenDto.getAccess_token();
				stringRedisTemplate.opsForValue().set(wechatPubAccessTokenCacheKeyPrefix + wechatPubAppId, accessToken,
						wechatPubAccessTokenTimeoutMinutes, TimeUnit.MINUTES);
				stringRedisTemplate.delete(wechatPubJsapiTicketCacheKeyPrefix + wechatPubAppId);
				logger.info(LogUtil.getFormatLog(null, "获取access_token成功"));
			} else {
				logger.error(LogUtil.getFormatLog(null, "获取access_token失败"));
			}
		} else {
			accessToken = stringRedisTemplate.opsForValue().get(wechatPubAccessTokenCacheKeyPrefix + wechatPubAppId);
		}
		return accessToken;
	}

	@Override
	public String getWechatPubJsapiTicket() {
		String jsapi_ticket;
		jsapi_ticket = stringRedisTemplate.opsForValue().get(wechatPubJsapiTicketCacheKeyPrefix + wechatPubAppId);
		if (!StringUtils.isEmpty(jsapi_ticket)) {
			return jsapi_ticket;
		}
		// 取access_token
		String accessToken = this.getWechatPubAccessToken();
		// 获取jsapi_ticket
		WechatPubJsapiTicketDto jsapiTicketDto = wechatClient.getWechatPubJsapiTicket(accessToken);
		if (jsapiTicketDto != null && "0".equals(jsapiTicketDto.getErrcode())) {
			jsapi_ticket = jsapiTicketDto.getTicket();
			stringRedisTemplate.opsForValue().set(wechatPubJsapiTicketCacheKeyPrefix + wechatPubAppId, jsapi_ticket,
					wechatPubAccessTokenTimeoutMinutes, TimeUnit.MINUTES);
		} else {
			logger.warn(LogUtil.getFormatLog(null, "获取jsapi_ticket失败"));
		}
		return jsapi_ticket;
	}
}
