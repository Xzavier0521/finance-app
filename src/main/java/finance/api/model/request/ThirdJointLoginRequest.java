package finance.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>第三方联合登陆</p>
 *
 * @author lili
 * @version 1.0: ThirdJointLoginRequest.java, v0.1 2018/11/28 6:22 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ThirdJointLoginRequest extends BasicRequest {
    private static final long serialVersionUID = 4460083157214329781L;
    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String            realName;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品名称
     */
    private String            productName;
}
