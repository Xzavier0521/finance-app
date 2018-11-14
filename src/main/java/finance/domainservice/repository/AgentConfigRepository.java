package finance.domainservice.repository;

import java.util.List;

import finance.domain.AgentConfig;
import finance.api.model.condition.AgentConfigQueryCondition;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: AgentConfigRepository.java, v0.1 2018/10/11 11:04 AM lili Exp $
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
