package finance.domainservice.service.jwt.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import finance.model.po.FinanceUserInfo;
import finance.domainservice.service.jwt.JwtService;

/**
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午2:24:53 hewenbin
 */
@Service
public class JwtServiceImpl implements JwtService {
	// 当前请求的用户jwt标识
	private static ThreadLocal<String> localJwtKey = new ThreadLocal<String>();
	
	@Value("${jwt.cache.timeouthours}")
	private Long jwtCacheTimeoutHours;
	
	@Value("${jwt.cache.key.prefix}")
	private String jwtCacheKeyPrefix;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	/**
	 * @see https://m.nonobank.com/doc/feserver-intro.html
	 */
	@Override
	public String saveJwt(FinanceUserInfo userInfo) {
		
		userInfo.setId(Long.valueOf(userInfo.getId()));
		
		String jwtKey = UUID.randomUUID().toString();
		String jwtValue = JSON.toJSONString(userInfo);
		
		stringRedisTemplate.opsForValue().set(jwtCacheKeyPrefix + jwtKey, jwtValue, jwtCacheTimeoutHours,TimeUnit.HOURS);
		
		// 接口调用时需要获取到当前请求的用户标识
		localJwtKey.set(jwtKey);
		return jwtKey;
	}



	@Override
	public Boolean refreshJwt(String jwtKey) {
		localJwtKey.set(jwtKey);
		return stringRedisTemplate.expire(jwtCacheKeyPrefix + jwtKey, jwtCacheTimeoutHours, TimeUnit.HOURS);
	}

	@Override
	public Boolean hasJwt(String jwtKey) {
		Boolean has = stringRedisTemplate.hasKey(jwtCacheKeyPrefix + jwtKey);
		return has;
	}
	
	@Override
	public FinanceUserInfo getUserInfo() {
		String jwtKey = localJwtKey.get();
		if (StringUtils.isEmpty(jwtKey)) {
			return null;
		}
		
		String jwtValue = stringRedisTemplate.opsForValue().get(jwtCacheKeyPrefix + jwtKey);
		if (StringUtils.isEmpty(jwtValue)) {
			return null;
		}
		FinanceUserInfo userInfo = JSON.parseObject(jwtValue, FinanceUserInfo.class);
		return userInfo;
	}

	@Override
	public void removeUserInfo() {
		localJwtKey.remove();		
	}

}
