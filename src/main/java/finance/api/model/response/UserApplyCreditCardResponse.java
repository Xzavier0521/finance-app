package finance.api.model.response;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>注释</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardResponse.java, v0.1 2018/11/20 上午10:15 PM user Exp $
 */
@Data
public class UserApplyCreditCardResponse extends BasicResponse {
    /**
     * 申请状态(0: 失败 1:成功)
     */
    private String applyStatus;
    /**
     * 申请信息
     */
    private String message;
}
