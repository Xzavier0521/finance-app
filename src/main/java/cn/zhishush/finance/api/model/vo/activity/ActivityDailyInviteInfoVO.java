package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>活动每日邀请数据</p>
 *
 * @author lili
 * @version 1.0: ActivityDailyInviteInfoVO.java, v0.1 2018/12/19 9:49 AM PM lili Exp $
 */
@Data
public class ActivityDailyInviteInfoVO implements Serializable {

    private static final long serialVersionUID = -4628800913598528172L;
    /**
     * 邀请人数
     */
    private int               inviteNum;

    /**
     * 邀请日期
     */
    private String            inviteDate;
}
