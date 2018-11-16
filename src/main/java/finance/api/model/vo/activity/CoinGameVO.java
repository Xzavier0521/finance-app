package finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>金币游戏</p>
 *
 * @author lili
 * @version 1.0: CoinGameVO.java, v0.1 2018/11/15 4:17 PM PM lili Exp $
 */
@Data
public class CoinGameVO implements Serializable {
    private static final long serialVersionUID = -6678310964729977983L;

    /**
     * 游戏代码
     */
    private String            gameCode;
    /**
     * 游戏名称
     */
    private String            gameName;

    /**
     * 是否支付金币
     */
    private Boolean           isPayCoin;
}
