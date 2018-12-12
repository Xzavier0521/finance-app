package cn.zhishush.finance.domainservice.service.validate.impl;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import cn.zhishush.finance.core.common.enums.LoginType;
import cn.zhishush.finance.core.common.enums.SmsUseType;
import cn.zhishush.finance.domainservice.service.validate.SmsValidateService;

/**
 * <p>
 * 校验手机验证码
 * </p>
 * 
 * @author lili
 * @version 1.0: SmsValidateServiceImpl.java, v0.1 2018/11/24 8:43 PM lili Exp $
 */
@Slf4j
@Service("smsValidateService")
public class SmsValidateServiceImpl implements SmsValidateService {

	@Value("${smscode.cache.key.prefix}")
	private String cacheKeyPrefix;

	@Value("${smscode.cache.minute}")
	private Long cacheMinute;

	@Value("${smscode.test.switch}")
	private String testSwitch;

	@Value("${sms.test.mobiles}")
	private String testMobiles;

	@Resource
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public Boolean cacheSmsValidateCode(String mobileNum, String validateCode, String useType) {
		stringRedisTemplate.opsForValue().set(cacheKeyPrefix + mobileNum + useType, validateCode, cacheMinute,
				TimeUnit.MINUTES);
		RedisAtomicLong entityIdCounter = new RedisAtomicLong(cacheKeyPrefix + "count" + mobileNum + useType,
				stringRedisTemplate.getConnectionFactory());
		entityIdCounter.expire(0, TimeUnit.MILLISECONDS);
		return true;
	}

	@Override
	public Boolean validateSmsCode(String mobileNum, String smsCode, String type) {

		Set<String> whitelist = Sets.newHashSet();
		whitelist.add("17192197807");
		log.info("白名单:{}", whitelist);
		if (whitelist.contains(mobileNum)) {
			log.info("手机号码:{},白名单不校验短信验证码", mobileNum);
			return true;
		}
		if ("1".equals(testSwitch) || testMobiles.contains(mobileNum)) {
			// 如果测试开关打开，或者白名单手机号，则直接验证通过
			return true;
		}
		String userType;
		if (LoginType.MOBILE == LoginType.getByCode(type)) {
			userType = SmsUseType.simpleRegist.name();
		} else if (SmsUseType.changePayPwd.name().equals(type)) {
			userType = SmsUseType.changePayPwd.name();
		} else if (LoginType.IMG_MOBILE == LoginType.getByCode(type) || LoginType.WE_CHAT == LoginType.getByCode(type)
				|| LoginType.QQ == LoginType.getByCode(type)) {
			userType = SmsUseType.login.name();
		} else {
			// 目前没有其他情况，如有另加判断
			return false;
		}
		String smsCodeValue = stringRedisTemplate.opsForValue().get(cacheKeyPrefix + mobileNum + userType);
		RedisAtomicLong counter = new RedisAtomicLong(cacheKeyPrefix + "count" + mobileNum + userType,
				stringRedisTemplate.getConnectionFactory());
		counter.getAndAdd(1);
		counter.expire(cacheMinute, TimeUnit.MINUTES);
		if (counter.get() > 3) {
			return false;
		}
		return smsCode.equals(smsCodeValue);
	}

}
