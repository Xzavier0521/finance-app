package cn.zhishush.finance.domainservice.repository.activity;

import java.util.List;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainConfig;

/**
 * <p>红包雨活动时间配置</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigRepository.java, v0.1 2018/11/16 11:43 AM lili Exp $
 */
public interface RedEnvelopeRainConfigRepository {

    /**
     * 查询红包雨活动时间配置
     * @param activityCode 活动代码
     * @return List<RedEnvelopeRainConfig>
     */
    List<RedEnvelopeRainConfig> queryByCode(String activityCode);

    /**
     * 查询红包雨活动时间配置
     * @param activityCode 活动代码
     * @param timeCode 时间代码
     * @return RedEnvelopeRainConfig
     */
    RedEnvelopeRainConfig queryByCode(String activityCode, RedEnvelopeRainTimeCodeEnum timeCode);
}
