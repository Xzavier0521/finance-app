package finance.api.model.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>用户邀请信息</p>
 *
 * @author lili
 * @version 1.0: UserInviteInfoQueryResponse.java, v0.1 2018/11/26 10:03 AM PM lili Exp $
 */
@Data
public class UserInviteInfoQueryResponse implements Serializable {

    private static final long serialVersionUID = -2074819849591097173L;
    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 手机号码
     */
    private String            mobileNum;
    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 一级邀请人数
     */
    private Long              firstInviteNum;

    /**
     * 二级邀请人数
     */
    private Long              secondInviteNum;

}
