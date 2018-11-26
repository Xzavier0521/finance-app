package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.api.model.condition.QueryCondition4Batch;

/**
 * <p>红包活动明细查询</p>
 * @author lili
 * @version 1.0: RedEnvelopeDetailQueryRequest.java, v0.1 2018/11/26 7:02 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedEnvelopeDetailQueryRequest extends QueryCondition4Batch {

	private static final long serialVersionUID = 7292536422954950380L;

}
