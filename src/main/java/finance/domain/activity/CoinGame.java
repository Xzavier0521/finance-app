package finance.domain.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 金币游戏
 * </p>
 *
 * @author lili
 * @version 1.0: CoinGame.java, v0.1 2018/11/15 9:03 PM PM lili Exp $
 */
@Data
public class CoinGame implements Serializable {

	private static final long serialVersionUID = 8757046652235826452L;
	/**
	 * 游戏代码
	 */
	private String gameCode;
	/**
	 * 游戏名称
	 */
	private String gameName;

	/**
	 * 是否支付金币
	 */
	private Boolean isPayCoin;
}
