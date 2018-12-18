package cn.zhishush.finance.api.model.vo.userinfo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>用户微信信息</p>
 *
 * @author lili
 * @version 1.0: UserWeChatInfoVO.java, v0.1 2018/12/18 4:48 PM PM lili Exp $
 */
@Data
public class UserWeChatInfoVO implements Serializable {

    private static final long serialVersionUID = 7105330479159721920L;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobileNum;

    /**
     * 邀请码
     */
    private String            inviteCode;

    /**
     *  微信昵称
     */
    private String            nickName;

    /**
     * 微信头像
     */
    private String            headImgUrl;
}
