package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户操作记录</p>
 * @author lili
 * @version 1.0: OperationRecordSaveRequest.java, v0.1 2018/11/26 7:01 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationRecordSaveRequest extends BasicRequest {
	private static final long serialVersionUID = 3102385229087960993L;

	/**
	 * 产品id
	 */
	private Long productId;

	/**
	 * 产品名称
	 */
	private String productName;
}
