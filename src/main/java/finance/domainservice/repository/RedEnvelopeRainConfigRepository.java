package finance.domainservice.repository;

import java.util.List;

import finance.domain.activity.RedEnvelopeRainConfig;

/**
 * <p>红包雨活动时间配置</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigRepository.java, v0.1 2018/11/16 11:43 AM PM lili Exp $
 */
public interface RedEnvelopeRainConfigRepository {

    /**
     * 查询红包雨活动时间配置
     * @param activityCode 活动代码
     * @return   List<RedEnvelopeRainConfig>
     */
    List<RedEnvelopeRainConfig> queryByCode(String activityCode);
}
