package finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.Data;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;

/**
 * <p>红包雨活动用户信息</p>
 *
 * @author lili
 * @version 1.0: UserRedEnvelopeRainInfoVO.java, v0.1 2018/11/26 7:12 PM lili Exp $
 */
@Data
public class UserRedEnvelopeRainInfoVO implements Serializable {
    private static final long           serialVersionUID = -4656041360953999282L;

    /**
     * 用户id
     */
    private Long                        userId;
    /**
     * 是否已经参加红包雨活动
     */
    private Boolean                     isJoin;
    /**
     * 活动日期
     */
    private Integer                     activityDay;
    /**
     * 活动代码
     */
    private String                      activityCode;
    /**
     * 时间编码
     */
    private RedEnvelopeRainTimeCodeEnum timeCode;
}
