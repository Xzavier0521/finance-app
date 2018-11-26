package finance.api.model.vo.activity;

import java.math.BigDecimal;

public class StepRewardsJoinDetailVo {
	private String mobileNum;
	private Integer inviteNum;
	private BigDecimal rewardAmount;

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public BigDecimal getRewardAmount() {
		return rewardAmount;
	}

	public void setRewardAmount(BigDecimal rewardAmount) {
		this.rewardAmount = rewardAmount;
	}

	@Override
	public String toString() {
		return "StepRewardsJoinDetailVo{" + "mobileNum='" + mobileNum + '\'' + ", inviteNum=" + inviteNum
				+ ", rewardAmount=" + rewardAmount + '}';
	}
}
