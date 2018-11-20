package finance.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>用户申请信用卡请求数据</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardRequest.java, v0.1 2018/11/20 上午10:10 PM user Exp $
 */
@Data
public class UserApplyCreditCardRequest extends BasicRequest {
    /**
     * 产品id
     */
    @NotNull
    private Long productId;
}