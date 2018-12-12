package cn.zhishush.finance.core.dal.dataobject.activity;

import java.util.Date;

public class RedEnvelopeRainRewardDO {
	/**
	 * 主键
	 */
	private Long id;

	/**
	 * 活动代码
	 */
	private String activityCode;

	/**
	 * 时间编码
	 */
	private String timeCode;

	/**
	 * 活动日期
	 */
	private String activityDay;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 用户手机号码
	 */
	private String mobilePhone;

	/**
	 * 奖励类别 红包雨-RED_ENVELOPE_RAIN,红包雨排行榜-RED_ENVELOPE_RAIN_RANKING
	 */
	private String rewardType;

	/**
	 * 红包个数
	 */
	private Long totalNum;

	/**
	 * 金币个数
	 */
	private Long totalAmount;

	/**
	 * 排行榜排名，类型为排行榜奖励才有值
	 */
	private Long ranking;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 创建者
	 */
	private String creator;

	/**
	 * 更新者
	 */
	private String updator;

	/**
	 * 是否删除 0-否，1-是
	 */
	private String isDelete;

	/**
	 * 版本号
	 */
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode == null ? null : activityCode.trim();
	}

	public String getTimeCode() {
		return timeCode;
	}

	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode == null ? null : timeCode.trim();
	}

	public String getActivityDay() {
		return activityDay;
	}

	public void setActivityDay(String activityDay) {
		this.activityDay = activityDay == null ? null : activityDay.trim();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
	}

	public String getRewardType() {
		return rewardType;
	}

	public void setRewardType(String rewardType) {
		this.rewardType = rewardType == null ? null : rewardType.trim();
	}

	public Long getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Long totalNum) {
		this.totalNum = totalNum;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getRanking() {
		return ranking;
	}

	public void setRanking(Long ranking) {
		this.ranking = ranking;
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete == null ? null : isDelete.trim();
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}