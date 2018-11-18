package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>红包雨活动金币奖励日志</p>
 * @author lili
 * @version $Id: RedEnvelopeRainRewardDO.java, v0.1 2018/11/18 3:02 PM lili Exp $
 */
@Data
public class RedEnvelopeRainRewardDO implements Serializable {
    private static final long serialVersionUID = -5133483990786000447L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 活动日期
     */
    private String            activityDay;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 用户手机号码
     */
    private String            mobilePhone;

    /**
     * 奖励类别 红包雨-RED_ENVELOPE_RAIN,红包雨排行榜-RED_ENVELOPE_RAIN_RANKING
     */
    private String            rewardType;

    /**
     * 红包个数
     */
    private Long           totalNum;

    /**
     * 金币个数
     */
    private Long           totalAmount;

    /**
     * 排行榜排名，类型为排行榜奖励才有值
     */
    private Long              ranking;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除 0-否，1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Integer           version;
}