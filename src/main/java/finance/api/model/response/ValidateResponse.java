package finance.api.model.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 参数验证结果
 * </p>
 * 
 * @author lili
 * @version $Id: ValidateResponse.java, v0.1 2018/11/14 4:04 PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResponse implements Serializable {

	private static final long serialVersionUID = 6197787778124934101L;
	/**
	 * 状态
	 */
	private boolean status;

	/**
	 * 错误原因
	 */
	private String errorMsg;

}
