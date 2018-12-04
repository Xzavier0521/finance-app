package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import finance.core.dal.dao.ActivityConfigDAO;
import finance.domain.activity.ActivityConfig;
import finance.domainservice.converter.ActivityConfigConverter;
import finance.domainservice.repository.ActivityConfigRepository;

/**
 * <p>活动推广配置</p>
 * 
 * @author lili
 * @version $Id: ActivityConfigRepositoryImpl.java, v0.1 2018/10/11 10:16 AM lili Exp $
 */
@Repository("activityConfigRepository")
public class ActivityConfigRepositoryImpl implements ActivityConfigRepository {

    @Resource
    private ActivityConfigDAO activityConfigDAO;

    @Override
    public int save(ActivityConfig activityConfig) {
        return activityConfigDAO.insertSelective(ActivityConfigConverter.convert(activityConfig));
    }

    @Override
    public int update(ActivityConfig activityConfig) {
        return activityConfigDAO
            .updateByPrimaryKeySelective(ActivityConfigConverter.convert(activityConfig));
    }

    @Override
    public List<ActivityConfig> queryByCodes(Long agentId, List<String> activityCodes) {
        if (CollectionUtils.isNotEmpty(activityCodes)) {
            Map<String, Object> parameters = Maps.newHashMap();
            parameters.put("agentId", agentId);
            parameters.put("activityCodes", activityCodes);
            return ActivityConfigConverter.convert2List(activityConfigDAO.query(parameters));
        } else {
            return Lists.newArrayList();
        }

    }
}
