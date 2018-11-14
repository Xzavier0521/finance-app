package finance.model.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FinanceUserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private BigDecimal canWithdrawMoney;

    private BigDecimal withdrawedMoney;

    private BigDecimal incomeMoney;

    private String userName;

    private String status;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private String isFlag;

    private BigDecimal sumChargeMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getCanWithdrawMoney() {
        return canWithdrawMoney;
    }

    public void setCanWithdrawMoney(BigDecimal canWithdrawMoney) {
        this.canWithdrawMoney = canWithdrawMoney;
    }

    public BigDecimal getWithdrawedMoney() {
        return withdrawedMoney;
    }

    public void setWithdrawedMoney(BigDecimal withdrawedMoney) {
        this.withdrawedMoney = withdrawedMoney;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getIsFlag() {
        return isFlag;
    }

    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag == null ? null : isFlag.trim();
    }

    public BigDecimal getSumChargeMoney() {
        return sumChargeMoney;
    }

    public void setSumChargeMoney(BigDecimal sumChargeMoney) {
        this.sumChargeMoney = sumChargeMoney;
    }

	@Override
	public String toString() {
		return "FinanceUserAccount:{“id\":\"" + id + "\",\"userId\":\"" + userId + "\",\"canWithdrawMoney\":\""
				+ canWithdrawMoney + "\",\"withdrawedMoney\":\"" + withdrawedMoney + "\",\"incomeMoney\":\""
				+ incomeMoney + "\",\"userName\":\"" + userName + "\",\"status\":\"" + status + "\",\"createTime\":\""
				+ createTime + "\",\"updateTime\":\"" + updateTime + "\",\"version\":\"" + version + "\",\"isFlag\":\""
				+ isFlag + "\",\"sumChargeMoney\":\"" + sumChargeMoney + "\"}  ";
	}
    
}