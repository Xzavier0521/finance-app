package finance.model.po;

import java.io.Serializable;
import java.util.Date;

public class FinanceUserBankCardInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private String bankName;

    private String accountName;

    private String accountNo;

    private String accountMobile;

    private Integer isDefault;

    private Integer isDelete;

    private String creator;

    private String updator;

    private Integer version;

    private Date createTime;

    private Date updateTime;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile == null ? null : accountMobile.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

	@Override
	public String toString() {
		return "FinanceUserBankCardInfo:{“" + (id != null ? "id\":\"" + id + "\",\"" : "")
				+ (userId != null ? "userId\":\"" + userId + "\",\"" : "")
				+ (bankName != null ? "bankName\":\"" + bankName + "\",\"" : "")
				+ (accountName != null ? "accountName\":\"" + accountName + "\",\"" : "")
				+ (accountNo != null ? "accountNo\":\"" + accountNo + "\",\"" : "")
				+ (accountMobile != null ? "accountMobile\":\"" + accountMobile + "\",\"" : "")
				+ (isDefault != null ? "isDefault\":\"" + isDefault + "\",\"" : "")
				+ (isDelete != null ? "isDelete\":\"" + isDelete + "\",\"" : "")
				+ (creator != null ? "creator\":\"" + creator + "\",\"" : "")
				+ (updator != null ? "updator\":\"" + updator + "\",\"" : "")
				+ (version != null ? "version\":\"" + version + "\",\"" : "")
				+ (createTime != null ? "createTime\":\"" + createTime + "\",\"" : "")
				+ (updateTime != null ? "updateTime\":\"" + updateTime : "") + "\"}  ";
	}
	
}