package cn.zhishush.finance.domainservice.service;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import cn.zhishush.finance.domain.dto.RedisLockDto;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version $Id: AbstractCoinDealMulti.java, v0.1 2018/11/15 10:01 PM lili Exp $
 */
public abstract class AbstractCoinDealMulti {

	private static final Logger logger = LoggerFactory.getLogger(AbstractCoinDealMulti.class);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Value("${user.coin.key.prefix}")
	private String userCoinKeyPrefix;

	// 获取锁
	private Boolean getLock(String coinLockKey) {
		// 是否有正在执行的线程
		boolean hasLock = false;
		try {
			hasLock = stringRedisTemplate.opsForValue().setIfAbsent(coinLockKey, "ing");
			if (hasLock) {
				stringRedisTemplate.expire(coinLockKey, 1, TimeUnit.MINUTES);
			}
		} catch (Exception e) {
			logger.error("redis.lock.setnx is error", e);
			// 避免成功获取到锁，但是未成功设置过期时间
			stringRedisTemplate.expire(coinLockKey, 1, TimeUnit.MINUTES);
		}
		return hasLock;
	}

	/**
	 * 释放锁
	 */
	private void releaseLock(String coinLockKey) {
		stringRedisTemplate.delete(coinLockKey);
	}

	/**
	 * 调用入口
	 */
	@Transactional(rollbackFor = Exception.class)
	public void handle(RedisLockDto redisLockDto) {
		String coinLockKey = userCoinKeyPrefix + redisLockDto.getLockId();
		if (!getLock(coinLockKey)) {
			// 未获取到锁
			return;
		}
		// 获取到锁，开始处理
		try {
			// 表示获取到锁
			redisLockDto.setHasLock(true);
			// 业务逻辑执行
			this.dealCoinTask(redisLockDto);

		} finally {
			// 只要获取到锁，则在业务逻辑结束之后，必须释放锁
			releaseLock(coinLockKey);
		}
	}

	public abstract void dealCoinTask(RedisLockDto redisLockDto);

}
