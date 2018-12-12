package cn.zhishush.finance.api.model.condition;

import cn.zhishush.finance.core.common.enums.ActivityCodeEnum;
import cn.zhishush.finance.core.common.enums.AgentLevelEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: AgentConfigQueryCondition.java, v0.1 2018/11/26 7:00 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AgentConfigQueryCondition extends QueryCondition4Batch {

	private static final long serialVersionUID = 4627868562517803468L;

	/**
	 * 代理的用户id
	 */
	private Long agentId;

	/**
	 * 代理的级别
	 */
	private AgentLevelEnum agentLevel;

	/**
	 * 推广活动代码
	 */
	private ActivityCodeEnum activityCode;
}
