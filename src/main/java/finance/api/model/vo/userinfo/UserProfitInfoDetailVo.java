package finance.api.model.vo.userinfo;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>
 * 用户收益详细信息
 * </p>
 * 
 * @author lili
 * @version $Id: UserProfitInfoDetailVo.java, v0.1 2018/11/14 3:37 PM lili Exp $
 */
@Data
public class UserProfitInfoDetailVo implements Serializable {

	private static final long serialVersionUID = -3582951148516123009L;
	/** 累计金币数 */
	private Integer availableCoin;
	/** 今日邀请人数 */
	private Long todayInviteCount;
	/** 累计邀请人数 */
	private Long totalInviteCount;
	/** 可提现金额 */
	private BigDecimal canWithdrawMoney;
	/** 累计收益 */
	private BigDecimal totalIncome;

}
