package finance.api.model.vo.activity;

import java.math.BigDecimal;

/**
 * @program: finance-server
 *
 * @description:
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-10 18:02
 **/
public class FixedAmountOpenedPacketInfo {
	private String mobileNum;

	private BigDecimal openedAmount;

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum.substring(0, 3) + "****" + mobileNum.substring(7, 11);
	}

	public BigDecimal getOpenedAmount() {
		return openedAmount;
	}

	public void setOpenedAmount(BigDecimal openedAmount) {
		this.openedAmount = openedAmount;
	}

	@Override
	public String toString() {
		return "FixedAmountOpenedPacketInfo{" + "mobileNum='" + mobileNum + '\'' + ", openedAmount=" + openedAmount
				+ '}';
	}
}
