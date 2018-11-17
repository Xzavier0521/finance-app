package finance.domain.activity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>用户红包雨数据明细</p>
 *
 * @author lili
 * @version 1.0: UserRedEnvelopeRainSummaryData.java, v0.1 2018/11/14 11:01 PM PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRedEnvelopeRainSummaryData implements Serializable {

    private static final long serialVersionUID = 1999428997095345270L;

    /**
     * 用户id
     */
    private Long              userId;
    /**
     * 活动代码
     */
    private String            activityCode;
    /**
     * 活动日期
     */
    private Integer           activityDay;
    /**
     * 今日拆红包个数
     */
    private Long              todayNum;

    /**
     * 今日总收入
     */
    private Long              todayAmount;

    /**
     *  总拆红包数量
     */
    private Long              totalNum;
    /**
     *  历史总收入
     */
    private Long              historyAmount;

    /**
     * 当前活动时间
     */
    private String            currentActivityDate;
    /**
     * 下一场活动时间
     */
    private String            nextActivityDate;
    /**
     * 系统当前时间
     */
    private String            currentSystemDate;

}
