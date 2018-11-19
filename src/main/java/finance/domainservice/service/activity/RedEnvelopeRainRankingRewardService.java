package finance.domainservice.service.activity;

import java.time.LocalDate;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨活动-排行榜奖励</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRankingRewardService.java, v0.1 2018/11/17 3:13 PM PM lili Exp $
 */
public interface RedEnvelopeRainRankingRewardService {

    /**
     *  处理指定日期排行榜数据，发放金币奖励
     * @param localDate 日期
     * @param activityCode  活动代码
     */
    void process(LocalDate localDate, String activityCode, RedEnvelopeRainTimeCodeEnum timeCod);
}
