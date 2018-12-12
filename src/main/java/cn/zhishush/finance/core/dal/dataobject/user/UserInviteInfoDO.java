package cn.zhishush.finance.core.dal.dataobject.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserInviteInfoDO.java, v0.1 2018/11/26 2:53 PM lili Exp $
 */
@Data
public class UserInviteInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long              id;

    private Long              userId;

    private Long              parentUserId;

    private Integer           inviteType;

    /**
     * 活动代码，默认-0000 注册的方式
     */
    private String            activityCode;

    private Integer           isDelete;

    private String            creator;

    private String            updator;

    private Integer           version;

    private Date              createTime;

    private Date              updateTime;

    private String            isPayCoin;

}