package finance.model.po;

import java.util.Date;

public class FinanceUserRegisterChannelInfo {
    private Long id;

    private Long userId;

    private String channelCode;

    private String channelDetail;

    private String platformCode;

    private String platformDetail;

    private String approach1;

    private String approach2;

    private String approach3;

    private String approach4;

    private String approach5;

    private String approach6;

    private String approach7;

    private String approach8;

    private String approach9;

    private String approach10;

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

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode == null ? null : channelCode.trim();
    }

    public String getChannelDetail() {
        return channelDetail;
    }

    public void setChannelDetail(String channelDetail) {
        this.channelDetail = channelDetail == null ? null : channelDetail.trim();
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

    public String getApproach1() {
        return approach1;
    }

    public void setApproach1(String approach1) {
        this.approach1 = approach1 == null ? null : approach1.trim();
    }

    public String getApproach2() {
        return approach2;
    }

    public void setApproach2(String approach2) {
        this.approach2 = approach2 == null ? null : approach2.trim();
    }

    public String getApproach3() {
        return approach3;
    }

    public void setApproach3(String approach3) {
        this.approach3 = approach3 == null ? null : approach3.trim();
    }

    public String getApproach4() {
        return approach4;
    }

    public void setApproach4(String approach4) {
        this.approach4 = approach4 == null ? null : approach4.trim();
    }

    public String getApproach5() {
        return approach5;
    }

    public void setApproach5(String approach5) {
        this.approach5 = approach5 == null ? null : approach5.trim();
    }

    public String getApproach6() {
        return approach6;
    }

    public void setApproach6(String approach6) {
        this.approach6 = approach6 == null ? null : approach6.trim();
    }

    public String getApproach7() {
        return approach7;
    }

    public void setApproach7(String approach7) {
        this.approach7 = approach7 == null ? null : approach7.trim();
    }

    public String getApproach8() {
        return approach8;
    }

    public void setApproach8(String approach8) {
        this.approach8 = approach8 == null ? null : approach8.trim();
    }

    public String getApproach9() {
        return approach9;
    }

    public void setApproach9(String approach9) {
        this.approach9 = approach9 == null ? null : approach9.trim();
    }

    public String getApproach10() {
        return approach10;
    }

    public void setApproach10(String approach10) {
        this.approach10 = approach10 == null ? null : approach10.trim();
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
		return "FinanceUserRegisterChannelInfo:{“" + (id != null ? "id\":\"" + id + "\",\"" : "")
				+ (userId != null ? "userId\":\"" + userId + "\",\"" : "")
				+ (channelCode != null ? "channelCode\":\"" + channelCode + "\",\"" : "")
				+ (channelDetail != null ? "channelDetail\":\"" + channelDetail + "\",\"" : "")
				+ (platformCode != null ? "platformCode\":\"" + platformCode + "\",\"" : "")
				+ (platformDetail != null ? "platformDetail\":\"" + platformDetail + "\",\"" : "")
				+ (approach1 != null ? "approach1\":\"" + approach1 + "\",\"" : "")
				+ (approach2 != null ? "approach2\":\"" + approach2 + "\",\"" : "")
				+ (approach3 != null ? "approach3\":\"" + approach3 + "\",\"" : "")
				+ (approach4 != null ? "approach4\":\"" + approach4 + "\",\"" : "")
				+ (approach5 != null ? "approach5\":\"" + approach5 + "\",\"" : "")
				+ (approach6 != null ? "approach6\":\"" + approach6 + "\",\"" : "")
				+ (approach7 != null ? "approach7\":\"" + approach7 + "\",\"" : "")
				+ (approach8 != null ? "approach8\":\"" + approach8 + "\",\"" : "")
				+ (approach9 != null ? "approach9\":\"" + approach9 + "\",\"" : "")
				+ (approach10 != null ? "approach10\":\"" + approach10 + "\",\"" : "")
				+ (isDelete != null ? "isDelete\":\"" + isDelete + "\",\"" : "")
				+ (creator != null ? "creator\":\"" + creator + "\",\"" : "")
				+ (updator != null ? "updator\":\"" + updator + "\",\"" : "")
				+ (version != null ? "version\":\"" + version + "\",\"" : "")
				+ (createTime != null ? "createTime\":\"" + createTime + "\",\"" : "")
				+ (updateTime != null ? "updateTime\":\"" + updateTime : "") + "\"}  ";
	}
    
}