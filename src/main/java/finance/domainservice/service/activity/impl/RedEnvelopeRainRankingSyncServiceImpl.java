package finance.domainservice.service.activity.impl;

import static finance.core.common.constants.RedEnvelopConstant.RED_ENVELOPE_RAIN_CODE;
import static finance.core.common.constants.RedEnvelopConstant.RED_ENVELOPE_RAIN_LEADER_BOARD;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import finance.core.common.constants.RedEnvelopConstant;
import finance.core.common.util.DateUtils;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domainservice.repository.RedEnvelopeRainDataRepository;
import finance.domainservice.service.activity.RedEnvelopeRainRankingSyncService;

/**
 * <p>红包雨活动-排行榜同步</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRankingSyncServiceImpl.java, v0.1 2018/11/17 2:19 AM PM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainRankingSyncService")
public class RedEnvelopeRainRankingSyncServiceImpl implements RedEnvelopeRainRankingSyncService {

    @Resource
    private RedEnvelopeRainDataRepository redEnvelopeRainDataRepository;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void process() {

        Integer activityDay = DateUtils.getCurrentDay(LocalDate.now());
        log.info("[开始同步{}日红包雨活动数据排行榜", activityDay);
        List<RedEnvelopeRainData> redEnvelopeRainDataList = redEnvelopeRainDataRepository
            .queryRankingList(RED_ENVELOPE_RAIN_CODE, activityDay, 0, 1000);
        if (CollectionUtils.isEmpty(redEnvelopeRainDataList)) {
            log.info("日期:{},排行榜数据为空", activityDay);
            return;
        }
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        ZSetOperations<String, Object> zSetOperations = redisTemplate.opsForZSet();
        // 日排行榜
        // leader_board:1001:20181117
        String leaderBoardKey = MessageFormat.format("{0}:{1}:{2}", RedEnvelopConstant.LEADER_BOARD,
            RED_ENVELOPE_RAIN_CODE, String.valueOf(activityDay));
        String redEnvelopeRainKey = MessageFormat.format("{0}:{1}", RED_ENVELOPE_RAIN_LEADER_BOARD,
            String.valueOf(activityDay));
        String key;
        for (RedEnvelopeRainData redEnvelopeRainData : redEnvelopeRainDataList) {
            // key: ranking-排名 score：user_id
            key = MessageFormat.format("{0}:{1}", leaderBoardKey,
                redEnvelopeRainData.getMobilePhone());
            // 排行榜 value:排名,score:手机号码 用户查询用户当前排名
            redisTemplate.delete(leaderBoardKey);
            zSetOperations.add(leaderBoardKey, redEnvelopeRainData.getRanking(),
                Long.valueOf(redEnvelopeRainData.getMobilePhone()));
            redisTemplate.expire(leaderBoardKey, 2880, TimeUnit.MINUTES);
            // 排行榜的集合  value:排行榜的明细的key score:排名
            redisTemplate.delete(redEnvelopeRainKey);
            zSetOperations.add(redEnvelopeRainKey, key, redEnvelopeRainData.getRanking());
            redisTemplate.expire(redEnvelopeRainKey, 2880, TimeUnit.MINUTES);
            // 排行榜的明细
            redisTemplate.delete(key);
            hashOperations.put(key, "ranking", redEnvelopeRainData.getRanking());
            hashOperations.put(key, "mobilePhone", redEnvelopeRainData.getMobilePhone());
            hashOperations.put(key, "totalNum", redEnvelopeRainData.getTotalNum());
            hashOperations.put(key, "totalAmount", redEnvelopeRainData.getTotalAmount());
            redisTemplate.expire(key, 2880, TimeUnit.MINUTES);
        }
        log.info("[结束同步{}日红包雨活动数据排行榜", activityDay);
    }
}
