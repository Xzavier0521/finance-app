package finance.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>用户申请信用卡请求数据</p>
 * @author lili
 * @version 1.0: UserApplyCreditCardRequest.java, v0.1 2018/11/26 7:03 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserApplyCreditCardRequest extends BasicRequest {
    private static final long serialVersionUID = 8317642028633509055L;
    /**
     * 产品id
     */
    @NotNull
    private Long              productId;
}