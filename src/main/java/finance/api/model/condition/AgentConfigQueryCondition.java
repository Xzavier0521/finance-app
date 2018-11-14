package finance.api.model.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.core.common.enums.ActivityCodeEnum;
import finance.core.common.enums.AgentLevelEnum;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: AgentConfigQueryCondition.java, v0.1 2018/10/11 11:25 AM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AgentConfigQueryCondition extends QueryCondition4Batch {

    private static final long serialVersionUID = 4627868562517803468L;

    /**
     * 代理的用户id
     */
    private Long              agentId;

    /**
     * 代理的级别
     */
    private AgentLevelEnum    agentLevel;

    /**
     * 推广活动代码
     */
    private ActivityCodeEnum  activityCode;
}
