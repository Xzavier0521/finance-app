package cn.zhishush.finance.api.model.vo.activity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>活动邀请数据</p>
 *
 * @author lili
 * @version 1.0: ActivityInviteInfoVO.java, v0.1 2018/12/18 4:18 PM PM lili Exp $
 */
@Data
public class ActivityInviteInfoVO implements Serializable {

    private static final long               serialVersionUID = -9217610347427503017L;
    private Long                            userId;

    /**
     * 手机号码
     */
    private String                          mobileNum;

    /**
     * 微信昵称
     */
    private String                          nickName;

    /**
     * 微信头像
     */
    private String                          headImgUrl;
    /**
     * 邀请人数
     */
    private int                             inviteNum;
    /**
     * 邀请码
     */
    private String                          inviteCode;

    /**
     * 每日邀请明细
     */
    private List<ActivityDailyInviteInfoVO> items;

}
