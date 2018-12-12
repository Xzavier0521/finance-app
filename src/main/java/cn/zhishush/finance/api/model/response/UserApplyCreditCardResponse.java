package cn.zhishush.finance.api.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: UserApplyCreditCardResponse.java, v0.1 2018/11/26 7:05 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserApplyCreditCardResponse extends BasicResponse {
    private static final long serialVersionUID = -4240562711390776070L;
    /**
     * 申请状态(0: 失败 1:成功)
     */
    private String            applyStatus;
    /**
     * 申请信息
     */
    private String            message;
}
