package finance.domainservice.service.activity.query.impl;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.api.model.base.Page;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.DateUtils;
import finance.domain.activity.RedEnvelopeRainConfig;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.activity.UserRedEnvelopeRainSummaryData;
import finance.domainservice.repository.RedEnvelopeRainConfigRepository;
import finance.domainservice.repository.RedEnvelopeRainDataRepository;
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
    private RedEnvelopeRainDataRepository   redEnvelopeRainDataRepository;
    @Resource
    private RedEnvelopeRainConfigRepository redEnvelopeRainConfigRepository;

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
        if (Objects.isNull(activityDay)) {
            activityDay = DateUtils.getCurrentDay(LocalDate.now());
        }
        return redEnvelopeRainDataRepository.query4Page(pageSize, pageNum, activityCode,
            activityDay, timeCode);
    }

    @Override
    public String queryUserCurrentRanking(String activityCode, Long userId) {

        RedEnvelopeRainData redEnvelopeRainData = redEnvelopeRainDataRepository.query(activityCode,
            DateUtils.getCurrentDay(LocalDate.now()), userId);
        // 未参加红包雨活动
        if (Objects.isNull(redEnvelopeRainData)) {
            return "?";
        }
        if (Objects.isNull(redEnvelopeRainData.getRanking())) {
            return "1000+";
        }
        return String.valueOf(redEnvelopeRainData.getRanking());
    }

    @Override
    public List<RedEnvelopeRainData> queryRankingList(String activityCode, Integer activityDay,
                                                      int pageSize, int pageNum) {
        return redEnvelopeRainDataRepository.queryRankingList(activityCode,
            DateUtils.getCurrentDay(LocalDate.now()), pageNum, pageNum);
    }

    private UserRedEnvelopeRainSummaryData buildData(String activityCode,
                                                     RedEnvelopeRainData todayRedEnvelopeRainData,
                                                     RedEnvelopeRainData hisRedEnvelopeRainData) {

        return UserRedEnvelopeRainSummaryData.builder().userId(todayRedEnvelopeRainData.getUserId())
            .currentSystemDate(
                DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.LONG_WEB_FORMAT))
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
        // 当前活动时间 1-进行中返回 活动的开始时间 2-已经结束，下个活动还未开始，返回下个活动的开始时间
        Integer requestTime = Integer
            .valueOf(DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.HOUR_FORMAT));
        List<RedEnvelopeRainConfig> redEnvelopeRainConfigList = redEnvelopeRainConfigRepository
            .queryByCode(activityCode, Lists.newArrayList());
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
        return timeStr.substring(0, 1) + ":" + timeStr.substring(2, 3) + ":"
               + timeStr.substring(4, 5);
    }
}
