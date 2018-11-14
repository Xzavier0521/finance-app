package finance.api.model.vo.redenvelope;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>红包活动参加者明细</p>
 * @author lili
 * @version $Id: ParticipantInfoVO.java, v0.1 2018/10/20 10:13 AM lili Exp $
 */
@Data
public class ParticipantInfoVO implements Serializable {

    private static final long serialVersionUID = 7315730443707121492L;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 真实姓名
     */
    private String            realName;

    /**
     * 手机号码
     */
    private String            mobilePhone;

    /**
     * 一级邀请人数
     */
    private Long              firstInviteNum;

    /**
     * 一级奖励金
     */
    private BigDecimal        firstRewardAmount;
    /**
     * 二级邀请人数
     */
    private Long              secondInviteNum;

    /**
     * 二级邀请单价
     */
    private BigDecimal        secondUnitPrice;

    /**
     * 二级奖励金额
     */
    private BigDecimal        secondRewardAmount;

    /**
     * 总邀请人数
     */
    private Long              totalInviteNum;

    /**
     * 总奖励金额
     */
    private BigDecimal        totalRewardAmount;
}
