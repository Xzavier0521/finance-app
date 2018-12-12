package cn.zhishush.finance.domainservice.repository.activity.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.api.model.condition.AgentConfigQueryCondition;
import cn.zhishush.finance.core.dal.dao.activity.AgentConfigDAO;
import cn.zhishush.finance.domain.activity.AgentConfig;
import cn.zhishush.finance.domainservice.converter.activity.AgentConfigConverter;
import cn.zhishush.finance.domainservice.repository.activity.AgentConfigRepository;

/**
 * <p></p>
 * 
 * @author lili
 * @version 1.0: AgentConfigRepositoryImpl.java, v0.1 2018/10/11 11:04 AM lili Exp $
 */
@Repository("agentConfigRepository")
public class AgentConfigRepositoryImpl implements AgentConfigRepository {

	@Resource
	private AgentConfigDAO agentConfigDAO;

	@Override
	public int save(AgentConfig agentConfig) {
		return agentConfigDAO.updateByPrimaryKeySelective(AgentConfigConverter.convert(agentConfig));
	}

	@Override
	public int update(AgentConfig agentConfig) {
		return agentConfigDAO.updateByPrimaryKeySelective(AgentConfigConverter.convert(agentConfig));
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
