package finance.domainservice.service.activity.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import finance.domainservice.service.activity.KeyGeneratorService;

/**
 * <p>key生成</p>
 * @author lili
 * @version $Id: KeyGeneratorServiceImpl.java, v0.1 2018/11/26 9:29 AM lili Exp $
 */
@Service("keyGeneratorService")
public class KeyGeneratorServiceImpl implements KeyGeneratorService {

	@Resource
	private RedisTemplate<String, Long> redisTemplate;

	@Override
	public Long generatorKeyByCode(String code) {
		return redisTemplate.opsForValue().increment(MessageFormat.format("app:key:{0}", code), 1);
	}
}
