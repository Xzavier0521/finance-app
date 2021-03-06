package cn.zhishush.finance.domainservice.service.activity.impl;

import static cn.zhishush.finance.core.common.constants.RedEnvelopConstant.ACTIVITY_CODE;

import java.text.MessageFormat;
import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.core.common.constants.RedEnvelopConstant;
import cn.zhishush.finance.domain.activity.LeaderBoard;
import cn.zhishush.finance.domainservice.service.activity.LeaderBoardSynchronizeService;
import cn.zhishush.finance.core.common.enums.LeaderBoardTypeEnum;

/**
 * <p>排行榜数据更新</p>
 * @author lili
 * @version $Id: LeaderBoardSynchronizeServiceImpl.java, v0.1 2018/11/26 9:29 AM lili Exp $
 */
@Slf4j
@Service
public class LeaderBoardSynchronizeServiceImpl implements LeaderBoardSynchronizeService {

	@Resource
	private RedEnvelopeRepository redEnvelopeRepository;
	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void process() {
		for (LeaderBoardTypeEnum leaderBoardType : LeaderBoardTypeEnum.values()) {
			ladingToCache(leaderBoardType, ACTIVITY_CODE);
		}
	}

	private void ladingToCache(LeaderBoardTypeEnum leaderBoardType, String activityCode) {
		List<LeaderBoard> leaderBoards = redEnvelopeRepository.queryByType(leaderBoardType, ACTIVITY_CODE,
				RedEnvelopConstant.LEADER_BOARD_NUM);
		if (CollectionUtils.isEmpty(leaderBoards)) {
			log.info("活动代码:{},{}数据为空", activityCode, leaderBoardType.getDesc());
			return;
		}
		HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
		ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
		String leaderBoardKey = MessageFormat.format("{0}:{1}:{2}", RedEnvelopConstant.LEADER_BOARD, activityCode,
				leaderBoardType.getCode());
		leaderBoards.forEach(leaderBoard -> {
			// leader_board:activity_code:fist:1 userId 1
			// leader_board:activity_code:fist:1 mobilePhone 18101625436
			// leader_board:activity_code:first:1 inviteNumber 20
			// leader_board:activity_code:first:1 realName lily
			// leader_board:activity_code:first:1 rewardAmount 19.0
			//
			String key = MessageFormat.format("{0}:{1}:{2}:{3}", RedEnvelopConstant.LEADER_BOARD, activityCode,
					leaderBoardType.getCode(), leaderBoard.getRanking());
			zSetOperations.add(leaderBoardKey, key, leaderBoard.getRanking());
			hashOperations.put(key, "ranking", leaderBoard.getRanking());
			hashOperations.put(key, "userId", leaderBoard.getUserId());
			hashOperations.put(key, "mobilePhone", leaderBoard.getMobilePhone());
			hashOperations.put(key, "inviteNumber", leaderBoard.getInviteNumber());
			hashOperations.put(key, "realName", leaderBoard.getRealName());
			hashOperations.put(key, "rewardAmount", leaderBoard.getRewardAmount());
			// first:leader_board:activity_code 保存排行榜的前1_000 的用户id和排名
			// second:leader_board:activity_code
			// all:leader_board:activity_code
			String rankingKey = MessageFormat.format("{0}:{1}:{2}", leaderBoardType.getCode(),
					RedEnvelopConstant.LEADER_BOARD, activityCode);
			hashOperations.put(rankingKey, leaderBoard.getUserId(), leaderBoard.getRanking());
		});
	}

}
