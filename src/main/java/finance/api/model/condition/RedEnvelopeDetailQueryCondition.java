package finance.api.model.condition;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeDetailQueryCondition.java, v0.1 2018/10/20 10:33 AM
 *          lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedEnvelopeDetailQueryCondition extends QueryCondition4Batch {
	private static final long serialVersionUID = 8859744183719274777L;

	/**
	 * 活动代码
	 */
	private String activityCode;

	/**
	 * 用户id
	 */
	private Long userId;
}
