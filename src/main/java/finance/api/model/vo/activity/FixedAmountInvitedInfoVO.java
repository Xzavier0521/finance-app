package finance.api.model.vo.activity;

import java.math.BigDecimal;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-10 16:29
 **/
public class FixedAmountInvitedInfoVO {
	private String mobileNum;

	private Integer inviteNum;

	private BigDecimal getAmount;

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum.substring(0, 3) + "****" + mobileNum.substring(7, 11);
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public BigDecimal getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(BigDecimal getAmount) {
		this.getAmount = getAmount;
	}

	@Override
	public String toString() {
		return "FixedAmountInvitedInfoVO{" + "mobileNum='" + mobileNum + '\'' + ", inviteNum=" + inviteNum
				+ ", getAmount=" + getAmount + '}';
	}
}
