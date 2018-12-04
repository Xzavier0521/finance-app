package finance.core.dal.dataobject;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>微信用户邀请关系</p>
 * @author lili
 * @version 1.0: WeiXinInviteInfoDO.java, v0.1 2018/12/3 5:13 PM lili Exp $
 */
@Data
public class WeiXinInviteInfoDO implements Serializable {
    private static final long serialVersionUID = 1958327748985862938L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 微信open_id
     */
    private String            openId;

    /**
     * 用户昵称
     */
    private String            nickName;

    /**
     * 用户id
     */
    private Long              userId;

    /**
     * 上级用户id
     */
    private Long              parentUserId;

    /**
     * 上级用户openId
     */
    private String            parentOpenId;

    /**
     * 上级用户昵称
     */
    private String            parentNickName;

    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 是否发送 N-否 Y-是
     */
    private String            isSend;

    /**
     * 发送时间
     */
    private Date              sendDate;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建者
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除 0-否 1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}