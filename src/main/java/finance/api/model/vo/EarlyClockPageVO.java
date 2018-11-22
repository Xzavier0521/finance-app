package finance.api.model.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>早起打卡页面</p>
 *
 * @author lili
 * @version $Id: EarlyClockPageVO.java, v0.1 2018/11/22 9:24 AM lili Exp $
 */
@Data
public class EarlyClockPageVO implements Serializable {
    private static final long serialVersionUID = -8295938243645360477L;
    private Integer           amountReward;
    private Integer           totalJoinNum;
    private Integer           totalCoinNum;
    private Integer           signNum;
    private Integer           noSignNum;
    private String            earliestMobile;
    private String            earliestTime;
    private String            maxCoinMobile;
    private String            maxCoinNum;
    private String            longestMobile;
    private String            longestNum;
    private Boolean           yesterdayJoinTask;
    private Boolean           todayJoinTask;
    private Integer           yesterdayTotalJoinCoinNum;

    private Integer           totalCanUserCoin;
    private Integer           earlyCardUseCoinNum;
    private Integer           yesterdayTotalJoinPersonNum;

    /**
     * 参加早起打卡开始时间
     */
    private String            joinBeginTime;
    /**
     * 参加早起打卡结束时间
     */
    private String            joinEndTime;
    /**
     * 早起打卡开始时间
     */
    private String            signBeginTime;
    /**
     * 早起打卡结束时间
     */
    private String            signEndTime;

}
