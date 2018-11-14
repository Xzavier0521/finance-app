package finance.model.po;

import java.util.Date;

public class FinanceUserRegisterLog {
    private Long id;

    private Long userId;

    private String mobileNum;

    private String ip;

    private String userAgent;

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

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent == null ? null : userAgent.trim();
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
		return "FinanceUserRegisterLog:{“" + (id != null ? "id\":\"" + id + "\",\"" : "")
				+ (userId != null ? "userId\":\"" + userId + "\",\"" : "")
				+ (mobileNum != null ? "mobileNum\":\"" + mobileNum + "\",\"" : "")
				+ (ip != null ? "ip\":\"" + ip + "\",\"" : "")
				+ (userAgent != null ? "userAgent\":\"" + userAgent + "\",\"" : "")
				+ (isDelete != null ? "isDelete\":\"" + isDelete + "\",\"" : "")
				+ (creator != null ? "creator\":\"" + creator + "\",\"" : "")
				+ (updator != null ? "updator\":\"" + updator + "\",\"" : "")
				+ (version != null ? "version\":\"" + version + "\",\"" : "")
				+ (createTime != null ? "createTime\":\"" + createTime + "\",\"" : "")
				+ (updateTime != null ? "updateTime\":\"" + updateTime : "") + "\"}  ";
	}
    
}