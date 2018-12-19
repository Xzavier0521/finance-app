package cn.zhishush.finance.core.dal.dataobject.activity;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>活动每日邀请数据</p>
 *
 * @author lili
 * @version 1.0: ActivityDailyInviteInfoDO.java, v0.1 2018/12/19 10:08 AM PM lili Exp $
 */
@Data
public class ActivityDailyInviteInfoDO implements Serializable {

    private static final long serialVersionUID = -2388360000044300266L;
    /**
     * 邀请人数
     */
    private int               inviteNum;

    /**
     * 邀请日期
     */
    private String            inviteDate;
}
