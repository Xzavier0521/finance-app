package cn.zhishush.finance.domainservice.repository.activity.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.dal.dao.activity.RedEnvelopeRainConfigDAO;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainConfig;
import cn.zhishush.finance.domainservice.converter.activity.RedEnvelopeRainConfigConverter;
import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRainConfigRepository;

/**
 * <p>红包雨活动时间配置</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigRepositoryImpl.java, v0.1 2018/11/16 11:47 AM  lili Exp $
 */
@Repository("redEnvelopeRainConfigRepository")
public class RedEnvelopeRainConfigRepositoryImpl implements RedEnvelopeRainConfigRepository {

    @Resource
    private RedEnvelopeRainConfigDAO redEnvelopeRainConfigDAO;

    @Override
    public List<RedEnvelopeRainConfig> queryByCode(String activityCode) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        return RedEnvelopeRainConfigConverter
            .convert2List(redEnvelopeRainConfigDAO.query(parameters));
    }

    /**
     * 查询红包雨活动时间配置
     *
     * @param activityCode
     *            RedEnvelopeRainConfig> queryByCode(String activityCode);
     * @param timeCode
     *            时间代码
     * @return RedEnvelopeRainConfig
     */
    @Override
    public RedEnvelopeRainConfig queryByCode(String activityCode,
                                             RedEnvelopeRainTimeCodeEnum timeCode) {
        RedEnvelopeRainConfig redEnvelopeRainConfig = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("timeCode", timeCode.getCode());
        List<RedEnvelopeRainConfig> redEnvelopeRainConfigList = RedEnvelopeRainConfigConverter
            .convert2List(redEnvelopeRainConfigDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(redEnvelopeRainConfigList)) {
            redEnvelopeRainConfig = redEnvelopeRainConfigList.get(0);
        }
        return redEnvelopeRainConfig;
    }
}
