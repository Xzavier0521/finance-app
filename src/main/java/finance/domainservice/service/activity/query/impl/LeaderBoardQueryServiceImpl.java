package finance.domainservice.service.activity.query.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.core.common.constant.RedEnvelopConstant;
import finance.domain.activity.LeaderBoard;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.RedEnvelopeRepository;
import finance.domainservice.service.activity.query.LeaderBoardQueryService;
import finance.core.common.enums.LeaderBoardTypeEnum;
import finance.core.common.util.CommonUtils;

/**
 * <p>排行榜查询</p>
 *
 * @author lili
 * @version 1.0: LeaderBoardQueryServiceImpl.java, v0.1 2018/10/19 5:39 PM lili Exp $
 */
@Slf4j
@Service("leaderBoardQueryService")
public class LeaderBoardQueryServiceImpl implements LeaderBoardQueryService {

    @Resource
    private RedEnvelopeRepository         redEnvelopeRepository;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     *   查询排行榜
     * @param leaderBoardType 排行榜类型
     * @param activityCode  活动代码
     * @param num  展示记录数
     * @return List<LeaderBoardVO>
     */
    @Override
    public List<LeaderBoard> queryByType(LeaderBoardTypeEnum leaderBoardType, String activityCode,
                                         int num) {
        List<LeaderBoard> leaderBoards = Lists.newArrayList();
        String key = MessageFormat.format("{0}:{1}:{2}", RedEnvelopConstant.LEADER_BOARD,
            activityCode, leaderBoardType.getCode());
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        Set<Object> leadBoardSet = zSetOperations.range(key, 0, num - 1);
        if (CollectionUtils.isEmpty(leadBoardSet)) {
            // 缓存中没有从数据库查询
            return redEnvelopeRepository.queryByType(leaderBoardType, activityCode, num);
        }
        // 排行榜的key
        Set<String> leaderBoardKeys = leadBoardSet.stream().map(v -> (String) v)
            .collect(Collectors.toSet());
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        for (String leadBoardKey : leaderBoardKeys) {
            Map<String, Object> fieldMap = Maps.newHashMap();
            LeaderBoard.fieldSet()
                .forEach(field -> fieldMap.put(field, hashOperations.get(leadBoardKey, field)));
            leaderBoards.add(LeaderBoard.mapToObject(fieldMap));
        }
        if (CollectionUtils.isEmpty(leaderBoards)) {
            leaderBoards = redEnvelopeRepository.queryByType(leaderBoardType, activityCode, num);
        }
        return leaderBoards;
    }

    /**
     * 查询用户当前排行榜
     * @param userInfo 用户信息
     * @param leaderBoardType 排行榜类型
     * @return  LeaderBoard
     */
    @Override
    public LeaderBoard queryCurrentLeaderBoard(UserInfo userInfo,
                                               LeaderBoardTypeEnum leaderBoardType) {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard.setUserId(userInfo.getId());
        leaderBoard.setMobilePhone(CommonUtils.mobileEncrypt(userInfo.getMobileNum()));
        String leaderBoardKey = MessageFormat.format("{0}:{1}:{2}", leaderBoardType.getCode(),
            RedEnvelopConstant.LEADER_BOARD, RedEnvelopConstant.ACTIVITY_CODE);
        log.info("排行榜key:{}", leaderBoardKey);
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        // 邀请人数保存的hash key
        String inviteNumbersKey = MessageFormat.format("{0}:{1}:{2}:{3}",
            RedEnvelopConstant.LEADER_BOARD, RedEnvelopConstant.ACTIVITY_CODE,
            RedEnvelopConstant.INVITE_NUMBERS, leaderBoardType.getCode());
        //  从缓存出查询用户的排名，没有则为1000+
        // first:leader_board:0001
        // second:leader_board:activity_code
        // all:leader_board:activity_code
        switch (leaderBoardType) {
            case ALL_LEVEL:
            case FIRST_LEVEL:
            case SECOND_LEVEL:
                Integer ranking = (Integer) hashOperations.get(leaderBoardKey, userInfo.getId());
                if (Objects.nonNull(ranking)) {
                    leaderBoard.setRanking(Long.valueOf(ranking));
                } else {
                    leaderBoard.setRanking(1001L);
                }
                Integer inviteNumbers = (Integer) hashOperations.get(inviteNumbersKey,
                    userInfo.getId());
                if (Objects.nonNull(inviteNumbers)) {
                    leaderBoard.setInviteNumber(inviteNumbers.longValue());
                } else {
                    leaderBoard.setInviteNumber(0L);
                }
                break;
            default:
                leaderBoard.setRanking(1001L);
        }
        return leaderBoard;
    }

}
