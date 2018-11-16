package finance.domainservice.service.activity.query;

import java.time.LocalDateTime;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨活动时间配置</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigQueryService.java, v0.1 2018/11/16 11:54 AM PM lili Exp $
 */
public interface RedEnvelopeRainConfigQueryService {

    /**
     *  返回请求时间属于哪个时间段的红包雨
     * @param activityCode 活动代码
     * @param localDateTime 请求时间
     * @return RedEnvelopeRainTimeCodeEnum
     */
    RedEnvelopeRainTimeCodeEnum queryTimeCode(String activityCode, LocalDateTime localDateTime);
}
