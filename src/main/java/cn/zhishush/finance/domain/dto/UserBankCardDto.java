package cn.zhishush.finance.domain.dto;

import org.springframework.util.StringUtils;

/**
 * 用户银行卡信息接收类.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月10日 下午8:15:24 hewenbin
 */
public class UserBankCardDto {

	private Long userId;
	private String bankName;
	private String accountName;
	private String accountNo;
	private String idNum;
	private String mobileNum;

	public Boolean validateParam() {
		if (StringUtils.isEmpty(accountNo) || accountNo.length() > 19 || accountNo.length() < 16
				|| !this.matchLuhn(accountNo)) {
			return false;
		}
		return true;
	}

	/**
	 * 匹配Luhn算法：可用于检测银行卡卡号
	 * 
	 * @param accountNo
	 * @return
	 */
	private boolean matchLuhn(String accountNo) {
		int[] cardNoArr = new int[accountNo.length()];
		for (int i = 0; i < accountNo.length(); i++) {
			cardNoArr[i] = Integer.valueOf(String.valueOf(accountNo.charAt(i)));
		}
		for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
			cardNoArr[i] <<= 1;
			cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
		}
		int sum = 0;
		for (int i = 0; i < cardNoArr.length; i++) {
			sum += cardNoArr[i];
		}
		return sum % 10 == 0;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	@Override
	public String toString() {
		return "UserBankCardDto{" + "userId=" + userId + ", bankName='" + bankName + '\'' + ", accountName='"
				+ accountName + '\'' + ", accountNo='" + accountNo + '\'' + ", idNum='" + idNum + '\'' + ", mobileNum='"
				+ mobileNum + '\'' + '}';
	}
}
