package cn.zhishush.finance.domain.team;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 邀请用户信息带收益信息
 * 
 * @author user
 */
@Data
public class InviteInfoAndIncome implements Serializable {

	private static final long serialVersionUID = 7641203687616243041L;
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
