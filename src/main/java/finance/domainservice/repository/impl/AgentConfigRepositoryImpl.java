package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.domain.AgentConfig;
import finance.domainservice.converter.AgentConfigConverter;
import finance.domainservice.repository.AgentConfigRepository;
import finance.mapper.AgentConfigDAO;
import finance.api.model.condition.AgentConfigQueryCondition;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: AgentConfigRepositoryImpl.java, v0.1 2018/10/11 11:04 AM lili Exp $
 */
@Repository("agentConfigRepository")
public class AgentConfigRepositoryImpl implements AgentConfigRepository {

    @Resource
    private AgentConfigDAO agentConfigDAO;

    @Override
    public int save(AgentConfig agentConfig) {
        return agentConfigDAO
            .updateByPrimaryKeySelective(AgentConfigConverter.convert(agentConfig));
    }

    @Override
    public int update(AgentConfig agentConfig) {
        return agentConfigDAO
            .updateByPrimaryKeySelective(AgentConfigConverter.convert(agentConfig));
    }

    @Override
    public List<AgentConfig> queryByCondition(AgentConfigQueryCondition condition) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("agentId", condition.getAgentId());
        parameters.put("agentLevel", condition.getAgentLevel().getCode());
        parameters.put("activityCode", condition.getActivityCode().getCode());
        return AgentConfigConverter.convert2List(agentConfigDAO.query(parameters));
    }
}
