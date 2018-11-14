package finance.model.po;

import java.util.Date;

public class FinanceSmsSendLog {
    private Long id;

    private String smsType;

    private String mobileNum;

    private String header;

    private String body;

    private Integer sendSuccess;

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

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType == null ? null : smsType.trim();
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public Integer getSendSuccess() {
        return sendSuccess;
    }

    public void setSendSuccess(Integer sendSuccess) {
        this.sendSuccess = sendSuccess;
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
		return "FinanceSmsSendLog:{“" + (id != null ? "id\":\"" + id + "\",\"" : "")
				+ (smsType != null ? "smsType\":\"" + smsType + "\",\"" : "")
				+ (mobileNum != null ? "mobileNum\":\"" + mobileNum + "\",\"" : "")
				+ (header != null ? "header\":\"" + header + "\",\"" : "")
				+ (body != null ? "body\":\"" + body + "\",\"" : "")
				+ (sendSuccess != null ? "sendSuccess\":\"" + sendSuccess + "\",\"" : "")
				+ (isDelete != null ? "isDelete\":\"" + isDelete + "\",\"" : "")
				+ (creator != null ? "creator\":\"" + creator + "\",\"" : "")
				+ (updator != null ? "updator\":\"" + updator + "\",\"" : "")
				+ (version != null ? "version\":\"" + version + "\",\"" : "")
				+ (createTime != null ? "createTime\":\"" + createTime + "\",\"" : "")
				+ (updateTime != null ? "updateTime\":\"" + updateTime : "") + "\"}  ";
	}
    
}