package cn.zhishush.finance.domain.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>活动每日邀请数据</p>
 *
 * @author lili
 * @version 1.0: ActivityDailyInviteInfo.java, v0.1 2018/12/19 10:06 AM PM lili Exp $
 */
@Data
public class ActivityDailyInviteInfo implements Serializable {

    private static final long serialVersionUID = 3654580460861764818L;
    /**
     * 邀请人数
     */
    private int               inviteNum;

    /**
     * 邀请日期
     */
    private String            inviteDate;
}
