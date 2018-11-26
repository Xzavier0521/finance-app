package finance.api.model.vo.coin;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 打卡签到所得金币
 * </p >
 *
 * @author liwei
 * @version $Id: SignCoinVO.java, v0.1 2018/11/20 下午3:18 PM user Exp $
 */
@Data
public class SignCoinVO implements Serializable {
	private static final long serialVersionUID = 5377904050978983222L;
	private Integer signCoin;
}
