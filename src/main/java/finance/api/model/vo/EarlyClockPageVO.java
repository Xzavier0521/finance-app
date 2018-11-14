package finance.api.model.vo;
/**
 * @program: finance-server
 *
 * @description: 早起打卡页面VO
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-22 10:26
 **/
public class EarlyClockPageVO {
	private Integer amountReward;
    private Integer totalJoinNum;
    private Integer totalCoinNum;
    private Integer signNum;
    private Integer noSignNum;
    private String earliestMobile;
    private String earliestTime;
    private String maxCoinMobile;
    private String maxCoinNum;
    private String longestMobile;
    private String longestNum;
    private Boolean yesterdayJoinTask;
    private Boolean todayJoinTask;
    private Integer yesterdayTotalJoinCoinNum;

    private Integer totalCanUserCoin;
    private Integer earlyCardUseCoinNum;
    private Integer yesterdayTotalJoinPersonNum;

    public Integer getYesterdayTotalJoinPersonNum() {
        return yesterdayTotalJoinPersonNum;
    }

    public void setYesterdayTotalJoinPersonNum(Integer yesterdayTotalJoinPersonNum) {
        this.yesterdayTotalJoinPersonNum = yesterdayTotalJoinPersonNum;
    }

    public Integer getEarlyCardUseCoinNum() {
        return earlyCardUseCoinNum;
    }

    public void setEarlyCardUseCoinNum(Integer earlyCardUseCoinNum) {
        this.earlyCardUseCoinNum = earlyCardUseCoinNum;
    }

    public Integer getTotalCanUserCoin() {
        return totalCanUserCoin;
    }

    public void setTotalCanUserCoin(Integer totalCanUserCoin) {
        this.totalCanUserCoin = totalCanUserCoin;
    }

    public Integer getYesterdayTotalJoinCoinNum() {
        return yesterdayTotalJoinCoinNum;
    }

    public void setYesterdayTotalJoinCoinNum(Integer yesterdayTotalJoinCoinNum) {
        this.yesterdayTotalJoinCoinNum = yesterdayTotalJoinCoinNum;
    }

    private Boolean clockTask;

    public Boolean getYesterdayJoinTask() {
        return yesterdayJoinTask;
    }

    public void setYesterdayJoinTask(Boolean yesterdayJoinTask) {
        this.yesterdayJoinTask = yesterdayJoinTask;
    }

    public Boolean getTodayJoinTask() {
        return todayJoinTask;
    }

    public void setTodayJoinTask(Boolean todayJoinTask) {
        this.todayJoinTask = todayJoinTask;
    }

    public Boolean getClockTask() {
        return clockTask;
    }

    public void setClockTask(Boolean clockTask) {
        this.clockTask = clockTask;
    }

    public Integer getTotalJoinNum() {
        return totalJoinNum;
    }

    public void setTotalJoinNum(Integer totalJoinNum) {
        this.totalJoinNum = totalJoinNum;
    }

    public Integer getTotalCoinNum() {
        return totalCoinNum;
    }

    public void setTotalCoinNum(Integer totalCoinNum) {
        this.totalCoinNum = totalCoinNum;
    }

    public Integer getSignNum() {
        return signNum;
    }

    public void setSignNum(Integer signNum) {
        this.signNum = signNum;
    }

    public Integer getNoSignNum() {
        return noSignNum;
    }

    public void setNoSignNum(Integer noSignNum) {
        this.noSignNum = noSignNum;
    }

    public String getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(String earliestTime) {
        this.earliestTime = earliestTime;
    }

    public String getMaxCoinNum() {
        return maxCoinNum;
    }

    public void setMaxCoinNum(String maxCoinNum) {
        this.maxCoinNum = maxCoinNum;
    }

    public String getLongestNum() {
        return longestNum;
    }

    public void setLongestNum(String longestNum) {
        this.longestNum = longestNum;
    }

    public String getEarliestMobile() {
        return earliestMobile;
    }

    public void setEarliestMobile(String earliestMobile) {
        this.earliestMobile = earliestMobile;
    }

    public String getMaxCoinMobile() {
        return maxCoinMobile;
    }

    public void setMaxCoinMobile(String maxCoinMobile) {
        this.maxCoinMobile = maxCoinMobile;
    }

    public String getLongestMobile() {
        return longestMobile;
    }

    public void setLongestMobile(String longestMobile) {
        this.longestMobile = longestMobile;
    }

    /**
	 * @return the amountReward
	 */
	public Integer getAmountReward() {
		return amountReward;
	}

	/**
	 * @param amountReward the amountReward to set
	 */
	public void setAmountReward(Integer amountReward) {
		this.amountReward = amountReward;
	}

	@Override
    public String toString() {
        return "EarlyClockPageVO{" +
                "totalJoinNum=" + totalJoinNum +
                ", totalCoinNum=" + totalCoinNum +
                ", signNum=" + signNum +
                ", noSignNum=" + noSignNum +
                ", earliestMobile='" + earliestMobile + '\'' +
                ", earliestTime='" + earliestTime + '\'' +
                ", maxCoinMobile='" + maxCoinMobile + '\'' +
                ", maxCoinNum='" + maxCoinNum + '\'' +
                ", longestMobile='" + longestMobile + '\'' +
                ", longestNum='" + longestNum + '\'' +
                ", yesterdayJoinTask=" + yesterdayJoinTask +
                ", todayJoinTask=" + todayJoinTask +
                ", clockTask=" + clockTask +
                '}';
    }
}
