package finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>用户红包雨数据明细</p>
 *
 * @author lili
 * @version 1.0: UserRedEnvelopeRainSummaryDataVO.java, v0.1 2018/11/14 11:44 PM PM lili Exp $
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRedEnvelopeRainSummaryDataVO implements Serializable {

    private static final long serialVersionUID = -3170449055644166772L;
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
     * 总拆红包数量
     */
    private Long              totalNum;
    /**
     * 历史总收入
     */
    private Long              historyAmount;
    /**
     * 当前活动时间
     */
    private String            currentActivityDate;
    /**
     * 系统当前时间
     */
    private String            currentSystemDate;
}