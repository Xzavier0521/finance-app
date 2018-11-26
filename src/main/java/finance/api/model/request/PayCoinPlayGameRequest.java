package finance.api.model.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 支付金币玩游戏
 * </p>
 *
 * @author lili
 * @version 1.0: PayCoinPlayGameRequest.java, v0.1 2018/11/15 9:30 AM PM lili
 *          Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayCoinPlayGameRequest extends BasicRequest {
	private static final long serialVersionUID = 7518170030524062841L;

	/**
	 * 活动代码
	 */
	@NotBlank(message = "活动代码不能为空")
	private String activityCode;

	/**
	 * 游戏代码
	 */
	@NotBlank(message = "游戏代码不能为空")
	private String gameCode;

	/**
	 * 支付的金币数
	 */
	@DecimalMin(value = "1", message = "支付的金币数不合法")
	@DecimalMax(value = "999999999", message = "支付的金币数不合法")
	private Long coinNum;
}
