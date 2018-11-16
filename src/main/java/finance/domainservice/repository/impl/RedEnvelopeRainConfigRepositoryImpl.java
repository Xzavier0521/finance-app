package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.dal.dao.RedEnvelopeRainConfigDAO;
import finance.domain.activity.RedEnvelopeRainConfig;
import finance.domainservice.converter.RedEnvelopeRainConfigConverter;
import finance.domainservice.repository.RedEnvelopeRainConfigRepository;

/**
 * <p>红包雨活动时间配置</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigRepositoryImpl.java, v0.1 2018/11/16 11:47 AM PM lili Exp $
 */
@Repository("redEnvelopeRainConfigRepository")
public class RedEnvelopeRainConfigRepositoryImpl implements RedEnvelopeRainConfigRepository {

    @Resource
    private RedEnvelopeRainConfigDAO redEnvelopeRainConfigDAO;

    @Override
    public List<RedEnvelopeRainConfig> queryByCode(String activityCode,
                                                   List<String> timeCodes) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        return RedEnvelopeRainConfigConverter
            .convert2List(redEnvelopeRainConfigDAO.query(parameters));
    }
}
