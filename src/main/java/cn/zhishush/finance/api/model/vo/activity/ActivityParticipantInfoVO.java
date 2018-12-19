package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * <p>活动参与者信息</p>
 *
 * @author lili
 * @version 1.0: ActivityParticipantInfoVO.java, v0.1 2018/12/18 4:04 PM PM lili Exp $
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityParticipantInfoVO implements Serializable {

    private static final long serialVersionUID = 5188690321276693905L;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobileNum;

    /**
     * 是否上下级关系
     */
    @JsonProperty("isSubordinate")
    private boolean           isSubordinate;

    /**
     * 是否参与活动
     */
    @JsonProperty("isParticipate")
    private boolean           isParticipate;

    /**
     * 是否完成活动
     */
    @JsonProperty("isFinished")
    private boolean           isFinished;
    /**
     * 是否成为推广人员
     */
    @JsonProperty("isPromoter")
    private boolean           isPromoter;
}
