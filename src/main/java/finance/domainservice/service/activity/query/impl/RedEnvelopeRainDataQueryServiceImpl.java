package finance.domainservice.service.activity.query.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.DateUtils;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.activity.UserRedEnvelopeRainSummaryData;
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
    private RedEnvelopeRainDataRepository redEnvelopeRainDataRepository;

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
        return null;
    }

    private UserRedEnvelopeRainSummaryData buildData(String activityCode,
                                                     RedEnvelopeRainData todayRedEnvelopeRainData,
                                                     RedEnvelopeRainData hisRedEnvelopeRainData) {
        // 当前活动时间 1-进行中返回 活动的开始时间 2-已经结束，下个活动还未开始，返回下个活动的开始时间

        return UserRedEnvelopeRainSummaryData.builder().userId(todayRedEnvelopeRainData.getUserId())
            .currentSystemDate(
                DateUtils.getFormatDateStr(LocalDateTime.now(), DateUtils.LONG_WEB_FORMAT))
            .activityCode(activityCode).activityDay(todayRedEnvelopeRainData.getActivityDay())
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
}
