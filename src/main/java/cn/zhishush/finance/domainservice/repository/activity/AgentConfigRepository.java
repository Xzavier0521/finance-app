package cn.zhishush.finance.domainservice.repository.activity;

import java.util.List;

import cn.zhishush.finance.api.model.condition.AgentConfigQueryCondition;
import cn.zhishush.finance.domain.activity.AgentConfig;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: AgentConfigRepository.java, v0.1 2018/10/11 11:04 AM lili Exp $
 */
public interface AgentConfigRepository {

    /**
     * 保存记录
     * @param agentConfig 记录
     * @return int
     */
    int save(AgentConfig agentConfig);

    /**
     * 更新记录
     * @param agentConfig 记录
     * @return int
     */
    int update(AgentConfig agentConfig);

    /**
     * 查询支持分页
     * @param condition 查询参数
     * @return List<AgentConfig>
     */
    List<AgentConfig> queryByCondition(AgentConfigQueryCondition condition);
}
