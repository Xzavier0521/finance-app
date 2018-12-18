package cn.zhishush.finance.api.model.vo.userinfo;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>用户的上级用户信息</p>
 *
 * @author lili
 * @version 1.0: UserParentInfoDetailVO.java, v0.1 2018/12/18 1:41 PM PM lili Exp $
 */
@Data
public class UserParentInfoDetailVO implements Serializable {
    private static final long serialVersionUID = -5131044668190644258L;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobileNum;
    /**
     * 上级用户信息
     */
    private UserWeChatInfoVO  parentUserInfo;

}
