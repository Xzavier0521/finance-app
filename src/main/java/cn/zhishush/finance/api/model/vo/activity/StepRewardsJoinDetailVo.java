package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

/**
 * <p>阶梯红包参加明细</p>
 * @author lili
 * @version 1.0: StepRewardsJoinDetailVo.java, v0.1 2018/11/26 7:11 PM lili Exp $
 */
@Data
public class StepRewardsJoinDetailVo implements Serializable {
    private static final long serialVersionUID = 8791517643576483834L;
    /**
     * 手机号码
     */
    private String            mobileNum;
    /**
     * 邀请人数
     */
    private Integer           inviteNum;
    /**
     * 奖励金额
     */
    private BigDecimal        rewardAmount;
}
