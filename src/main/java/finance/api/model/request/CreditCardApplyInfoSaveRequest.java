package finance.api.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CreditCardApplyInfoSaveRequest.java, v0.1 2018/11/29 5:37 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreditCardApplyInfoSaveRequest extends BasicRequest {
    private static final long serialVersionUID = 5611452989114605418L;

    /**
     * 产品代码
     */
    private String            productCode;
}
