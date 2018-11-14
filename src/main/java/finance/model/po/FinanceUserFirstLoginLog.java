package finance.model.po;

import java.io.Serializable;
import java.util.Date;

public class FinanceUserFirstLoginLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

    private Long userId;

    private String platformCode;

    private String platformDetail;

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

    public String getPlatformCode() {
        return platformCode;
    }

    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode == null ? null : platformCode.trim();
    }

    public String getPlatformDetail() {
        return platformDetail;
    }

    public void setPlatformDetail(String platformDetail) {
        this.platformDetail = platformDetail == null ? null : platformDetail.trim();
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
		return "FinanceUserFirstLoginLog:{“" + (id != null ? "id\":\"" + id + "\",\"" : "")
				+ (userId != null ? "userId\":\"" + userId + "\",\"" : "")
				+ (platformCode != null ? "platformCode\":\"" + platformCode + "\",\"" : "")
				+ (platformDetail != null ? "platformDetail\":\"" + platformDetail + "\",\"" : "")
				+ (isDelete != null ? "isDelete\":\"" + isDelete + "\",\"" : "")
				+ (creator != null ? "creator\":\"" + creator + "\",\"" : "")
				+ (updator != null ? "updator\":\"" + updator + "\",\"" : "")
				+ (version != null ? "version\":\"" + version + "\",\"" : "")
				+ (createTime != null ? "createTime\":\"" + createTime + "\",\"" : "")
				+ (updateTime != null ? "updateTime\":\"" + updateTime : "") + "\"}  ";
	}
    
    
}