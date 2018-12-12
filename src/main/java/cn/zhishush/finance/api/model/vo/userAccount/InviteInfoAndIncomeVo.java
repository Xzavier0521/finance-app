package cn.zhishush.finance.api.model.vo.userAccount;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * 用户邀请信息带收益
 *
 * @author lili
 * @version v1.0 2018年9月27日 下午10:22:07 lili
 */
@Data
public class InviteInfoAndIncomeVo implements Serializable {

	private static final long serialVersionUID = -4679852080699855955L;
	private Long parentUserId;
	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 手机号码
	 */
	private String phoneNumber;

	/**
	 * 注册时间
	 */
	private String registerDate;

	/**
	 * 总收益
	 */
	private BigDecimal totalIncome;

	/**
	 * 预计收益
	 */
	private BigDecimal predictIncome;

}
