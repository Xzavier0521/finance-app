package finance.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>金币游戏</p>
 * @author lili
 * @version 1.0: ActivityCoinGameQueryRequest.java, v0.1 2018/11/26 7:01 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ActivityCoinGameQueryRequest extends BasicRequest {

	private static final long serialVersionUID = 9191599014181770279L;

	/**
	 * 活动代码
	 */
	@NotBlank(message = "活动代码不能为空")
	private String activityCode;

	/**
	 * 游戏代码
	 */
	@NotBlank(message = "游戏代码列表不能为空")
	private String gameCodes;

}
