package finance.model.po;

import java.io.Serializable;
import java.util.Date;

public class FinanceUserInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

    private String mobileNum;

    private String inviteCode;

    private String status;

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

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
		return "FinanceUserInfo [" + (id != null ? "id=" + id + ", " : "")
				+ (mobileNum != null ? "mobileNum=" + mobileNum + ", " : "")
				+ (inviteCode != null ? "inviteCode=" + inviteCode + ", " : "")
				+ (status != null ? "status=" + status + ", " : "")
				+ (isDelete != null ? "isDelete=" + isDelete + ", " : "")
				+ (creator != null ? "creator=" + creator + ", " : "")
				+ (updator != null ? "updator=" + updator + ", " : "")
				+ (version != null ? "version=" + version + ", " : "")
				+ (createTime != null ? "createTime=" + createTime + ", " : "")
				+ (updateTime != null ? "updateTime=" + updateTime : "") + "]";
	}
    
    
}