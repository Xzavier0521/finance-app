package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import finance.api.model.condition.QueryCondition4Batch;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeDetailQueryRequest.java, v0.1 2018/10/20 1:33 PM
 *          lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RedEnvelopeDetailQueryRequest extends QueryCondition4Batch {

	private static final long serialVersionUID = 7292536422954950380L;

}
