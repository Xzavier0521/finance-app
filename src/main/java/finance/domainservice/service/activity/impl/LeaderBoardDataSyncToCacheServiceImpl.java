package finance.domainservice.service.activity.impl;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import finance.core.common.constants.RedEnvelopConstant;
import finance.domainservice.service.activity.LeaderBoardDataSyncToCacheService;
import finance.core.common.enums.LeaderBoardTypeEnum;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: LeaderBoardDataSyncToCacheServiceImpl.java, v0.1 2018/11/26 9:29 AM lili Exp $
 */
@Slf4j
@Service("leaderBoardDataSyncToCacheService")
public class LeaderBoardDataSyncToCacheServiceImpl implements LeaderBoardDataSyncToCacheService {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 一级邀请人数排行榜 key field value leader_board:0001:invite_numbers:first user_id 256
	 * 
	 * @param parentUserId
	 *            上级用户ID
	 */
	@Override
	public void fistLeaderBoardSync(Long parentUserId) {
		process(parentUserId, LeaderBoardTypeEnum.FIRST_LEVEL);
	}

	/**
	 * 二级邀请人数排行榜
	 *
	 * @param grandUserId
	 *            上级的上级用户id
	 */
	@Override
	public void secondLeaderBoardSync(Long grandUserId) {
		process(grandUserId, LeaderBoardTypeEnum.SECOND_LEVEL);
	}

	/**
	 * 邀请人数总榜
	 *
	 * @param userId
	 *            用户ID
	 */
	@Override
	public void allLeaderBoardSync(Long userId) {
		process(userId, LeaderBoardTypeEnum.ALL_LEVEL);
	}

	private void process(Long userId, LeaderBoardTypeEnum leaderBoardType) {

		CompletableFuture.supplyAsync(() -> {
			HashOperations<String, Long, Long> hashOperations = redisTemplate.opsForHash();
			ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
			// 邀请人数
			String inviteNumbersKey = MessageFormat.format("{0}:{1}:{2}:{3}", RedEnvelopConstant.LEADER_BOARD,
					RedEnvelopConstant.ACTIVITY_CODE, RedEnvelopConstant.INVITE_NUMBERS, leaderBoardType.getCode());
			hashOperations.increment(inviteNumbersKey, userId, 1);
			// 排名
			String rankingKey = MessageFormat.format("{0}:{1}:{2}:{3}", RedEnvelopConstant.LEADER_BOARD,
					RedEnvelopConstant.ACTIVITY_CODE, RedEnvelopConstant.RANKING, leaderBoardType.getCode());
			zSetOperations.incrementScore(rankingKey, userId, 1);
			return true;
		});

	}
}
