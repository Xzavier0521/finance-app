package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户操作记录</p>
 * @author lili
 * @version $Id: OperationRecordSaveRequest.java, v0.1 2018/11/6 1:36 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OperationRecordSaveRequest extends BasicRequest {
    private static final long serialVersionUID = 3102385229087960993L;

    /**
     * 产品id
     */
    private Long              productId;

    /**
     * 产品名称
     */
    private String            productName;
}
