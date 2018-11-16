package finance.domainservice.service.activity.query.impl;

import static finance.core.common.constants.RedEnvelopConstant.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.common.constants.RedEnvelopConstant;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.DateUtils;
import finance.domain.activity.*;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.RedEnvelopeRainConfigRepository;
import finance.domainservice.repository.RedEnvelopeRainDataRepository;
import finance.domainservice.service.activity.query.RedEnvelopeRainConfigQueryService;
import finance.domainservice.service.activity.query.RedEnvelopeRainDataQueryService;

/**
 * <p>红包雨活动数据查询</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataQueryServiceImpl.java, v0.1 2018/11/14 11:07 PM PM lili Exp $
 */
@Slf4j
@Service("redEnvelopeRainDataQueryService")
public class RedEnvelopeRainDataQueryServiceImpl implements RedEnvelopeRainDataQueryService {
    @Resource
    private RedisTemplate<String, String>     redisTemplate;
    @Resource
    private RedEnvelopeRainConfigQueryService redEnvelopeRainConfigQueryService;
    @Resource
    private RedEnvelopeRainDataRepository     redEnvelopeRainDataRepository;
    @Resource
    private RedEnvelopeRainConfigRepository   redEnvelopeRainConfigRepository;

    @Override
    public UserRedEnvelopeRainSummaryData querySummaryData(Long userId, String activityCode,
                                                           Integer activityDay) {

        // 用户当日红包雨活动数据
        RedEnvelopeRainData todayRedEnvelopeRainData = redEnvelopeRainDataRepository
            .queryTodayData(userId, activityCode, activityDay);
        // 用户历史红包雨活动数据
        RedEnvelopeRainData hisRedEnvelopeRainData = redEnvelopeRainDataRepository
            .queryHistoryData(userId, activityCode);
        return buildData(activityCode, todayRedEnvelopeRainData, hisRedEnvelopeRainData);
    }

    /**
     * @param pageSize    每页记录数
     * @param pageNum     第几页
     * @param activityDay 活动日期
     * @param timeCode    时间代码
     * @return Page<RedEnvelopeRainData>
     */
    @Override
    public Page<RedEnvelopeRainData> query4Page(Integer pageSize, Long pageNum, String activityCode,
                                                Integer activityDay,
                                                RedEnvelopeRainTimeCodeEnum timeCode) {
        return redEnvelopeRainDataRepository.query4Page(pageSize, pageNum, activityCode,
            activityDay, timeCode);
    }

    @Override
    public Page<RedEnvelopeRainData> queryDailyRainData4Page(Integer pageSize, Long pageNum,
                                                             String activityCode,
                                                             Integer activityDay) {
        return redEnvelopeRainDataRepository.queryDailyRainData4Page(pageSize, pageNum,
            activityCode, activityDay);
    }

    @Override
    public String queryUserCurrentRanking(String activityCode, UserInfo userInfo) {
        Integer activityDay = DateUtils.getCurrentDay(LocalDate.now());
        // 参加活动的手机号码列表
        String key = MessageFormat.format("{}:{}", RED_ENVELOPE_RAIN_PHONE_NUMBERS, activityDay);
        boolean isJoin = redisTemplate.opsForSet().isMember(key, userInfo.getMobileNum());
        // 未参加红包雨活动
        if (!isJoin) {
            return "?";
        }
        String leaderBoardKey = MessageFormat.format("{0}:{1}:{2}", RedEnvelopConstant.LEADER_BOARD,
            RED_ENVELOPE_RAIN_CODE, activityDay);
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> keys = zSetOperations.reverseRangeByScore(leaderBoardKey,
            Long.valueOf(userInfo.getMobileNum()), Long.valueOf(userInfo.getMobileNum()));
        if (CollectionUtils.isEmpty(keys)) {
            return "1000+";
        }
        return String.valueOf(keys.toArray()[0]);
    }

    @Override
    public List<RedEnvelopeRainData> queryRankingList(String activityCode, Integer activityDay,
                                                      int pageSize, int pageNum) {
        List<RedEnvelopeRainData> redEnvelopeRainDataList = Lists.newArrayList();
        RedEnvelopeRainConfig redEnvelopeRainConfig = redEnvelopeRainConfigRepository
            .queryByCode(activityCode, RedEnvelopeRainTimeCodeEnum.FIRST);
        Integer now = Integer
            .valueOf(DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.HOUR_FORMAT));
        if (now < Integer.valueOf(redEnvelopeRainConfig.getStartTime())) {
            activityDay = DateUtils.getCurrentDay(LocalDate.now().plusDays(-1));
        }
        String redEnvelopeRainKey = MessageFormat.format("{0}:{1}", RED_ENVELOPE_RAIN_LEADER_BOARD,
            String.valueOf(activityDay));
        ZSetOperations<String, String> zSetOperations = redisTemplate.opsForZSet();
        Set<String> leaderBoardKeys = zSetOperations.rangeByScore(redEnvelopeRainKey, 1,
            pageSize * pageNum);
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        for (String leadBoardKey : leaderBoardKeys) {
            Map<String, Object> fieldMap = Maps.newHashMap();
            LeaderBoard.fieldSet()
                .forEach(field -> fieldMap.put(field, hashOperations.get(leadBoardKey, field)));
            redEnvelopeRainDataList.add(RedEnvelopeRainData.mapToObject(fieldMap));
        }
        if (CollectionUtils.isEmpty(redEnvelopeRainDataList)) {
            redEnvelopeRainDataList = redEnvelopeRainDataRepository.queryRankingList(activityCode,
                activityDay, pageSize, pageNum);
        }
        return redEnvelopeRainDataList;
    }

    @Override
    public UserRedEnvelopeRainInfo queryUserRedEnvelopeRainInfo(UserInfo userInfo,
                                                                String activityCode,
                                                                Integer activityDay) {

        UserRedEnvelopeRainInfo userRedEnvelopeRainInfo = new UserRedEnvelopeRainInfo();
        userRedEnvelopeRainInfo.setUserId(userInfo.getId());
        userRedEnvelopeRainInfo.setActivityCode(activityCode);
        userRedEnvelopeRainInfo.setActivityDay(activityDay);
        userRedEnvelopeRainInfo.setIsJoin(false);
        RedEnvelopeRainTimeCodeEnum timeCode = redEnvelopeRainConfigQueryService
            .queryTimeCode(activityCode, LocalDateTime.now());
        if (Objects.isNull(timeCode)) {
            return userRedEnvelopeRainInfo;
        }
        RedEnvelopeRainData redEnvelopeRainData = redEnvelopeRainDataRepository.query(activityCode,
            activityDay, userInfo.getId(), timeCode);
        if (Objects.nonNull(redEnvelopeRainData)) {
            userRedEnvelopeRainInfo.setIsJoin(true);
        }
        return userRedEnvelopeRainInfo;
    }

    private UserRedEnvelopeRainSummaryData buildData(String activityCode,
                                                     RedEnvelopeRainData todayRedEnvelopeRainData,
                                                     RedEnvelopeRainData hisRedEnvelopeRainData) {

        return UserRedEnvelopeRainSummaryData.builder().userId(todayRedEnvelopeRainData.getUserId())
            .currentSystemDate(
                DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.LONG_APP_FORMAT))
            .currentActivityDate(getCurrentActivityDate(activityCode)).activityCode(activityCode)
            .activityDay(todayRedEnvelopeRainData.getActivityDay())
            .todayNum(todayRedEnvelopeRainData.getTotalNum())
            .todayAmount(Objects.nonNull(todayRedEnvelopeRainData.getTotalAmount())
                ? todayRedEnvelopeRainData.getTotalAmount().longValue()
                : 0L)
            .totalNum(hisRedEnvelopeRainData.getTotalNum())
            .historyAmount(Objects.nonNull(hisRedEnvelopeRainData.getTotalAmount())
                ? hisRedEnvelopeRainData.getTotalAmount().longValue()
                : 0)
            .build();
    }

    private String getCurrentActivityDate(String activityCode) {
        // 当前活动时间 1-进行中 返回活动的开始时间 2-已经结束，下个活动还未开始，返回下个活动的开始时间
        Integer requestTime = Integer
            .valueOf(DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.HOUR_FORMAT));
        List<RedEnvelopeRainConfig> redEnvelopeRainConfigList = redEnvelopeRainConfigRepository
            .queryByCode(activityCode);
        String currentActivityDate = StringUtils.EMPTY;
        String currentDay = DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.WEB_FORMAT);
        String nextDay = DateUtils.getFormatDateStr(LocalDateTime.now().plusDays(1),
            DateUtils.WEB_FORMAT);
        RedEnvelopeRainConfig first = getConfig(redEnvelopeRainConfigList,
            RedEnvelopeRainTimeCodeEnum.FIRST);
        if (requestTime <= Integer.valueOf(first.getEndTime())) {
            currentActivityDate = MessageFormat.format("{0} {1}", currentDay,
                getWebFormatTime(first.getStartTime()));
        }
        RedEnvelopeRainConfig second = getConfig(redEnvelopeRainConfigList,
            RedEnvelopeRainTimeCodeEnum.SECOND);
        if (requestTime > Integer.valueOf(first.getEndTime())
            && requestTime <= Integer.valueOf(second.getEndTime())) {
            currentActivityDate = MessageFormat.format("{0} {1}", currentDay,
                getWebFormatTime(second.getStartTime()));
        }
        RedEnvelopeRainConfig third = getConfig(redEnvelopeRainConfigList,
            RedEnvelopeRainTimeCodeEnum.THIRD);
        if (requestTime > Integer.valueOf(second.getEndTime())
            && requestTime <= Integer.valueOf(third.getEndTime())) {
            currentActivityDate = MessageFormat.format("{0} {1}", currentDay,
                getWebFormatTime(third.getStartTime()));
        }
        if (requestTime > Integer.valueOf(third.getEndTime())) {
            currentActivityDate = MessageFormat.format("{0} {1}", nextDay,
                getWebFormatTime(first.getStartTime()));
        }
        return currentActivityDate;
    }

    private RedEnvelopeRainConfig getConfig(List<RedEnvelopeRainConfig> redEnvelopeRainConfigList,
                                            RedEnvelopeRainTimeCodeEnum timeCodeEnum) {

        return redEnvelopeRainConfigList.stream()
            .filter(redEnvelopeRainConfig -> timeCodeEnum == RedEnvelopeRainTimeCodeEnum
                .getByCode(redEnvelopeRainConfig.getTimeCode()))
            .collect(Collectors.toList()).get(0);
    }

    private String getWebFormatTime(String timeStr) {
        return timeStr.substring(0, 2) + ":" + timeStr.substring(2, 4) + ":"
               + timeStr.substring(4, 6);
    }
}
