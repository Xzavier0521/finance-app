package finance.api.model.vo;

import finance.core.dal.dataobject.FinanceUserInfo;

/**
 * 用户信息展示实体类.
 * @author hewenbin
 * @version v1.0 2018年7月21日 下午5:03:56 hewenbin
 */
public class FinanceUserInfoVo extends FinanceUserInfo {
	
    private String channelCode;
    private String channelDetail;
    private String platformCode;
    private String platformDetail;
    private String realName;
    private String accountNo;
    private String inviteMobileNum;
    private String registerTime;
    
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public String getChannelDetail() {
		return channelDetail;
	}
	public void setChannelDetail(String channelDetail) {
		this.channelDetail = channelDetail;
	}
	public String getPlatformCode() {
		return platformCode;
	}
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}
	public String getPlatformDetail() {
		return platformDetail;
	}
	public void setPlatformDetail(String platformDetail) {
		this.platformDetail = platformDetail;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getInviteMobileNum() {
		return inviteMobileNum;
	}
	public void setInviteMobileNum(String inviteMobileNum) {
		this.inviteMobileNum = inviteMobileNum;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "FinanceUserInfoVo{" +
				"channelCode='" + channelCode + '\'' +
				", channelDetail='" + channelDetail + '\'' +
				", platformCode='" + platformCode + '\'' +
				", platformDetail='" + platformDetail + '\'' +
				", realName='" + realName + '\'' +
				", accountNo='" + accountNo + '\'' +
				", inviteMobileNum='" + inviteMobileNum + '\'' +
				", registerTime='" + registerTime + '\'' +
				'}';
	}
}
