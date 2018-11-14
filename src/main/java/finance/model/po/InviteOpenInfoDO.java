package finance.model.po;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: InviteOpenInfoDO.java, v0.1 2018/10/31 11:03 PM lili Exp $
 */
@Data
public class InviteOpenInfoDO implements Serializable {
    private static final long serialVersionUID = -1511151720950815060L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * open_id
     */
    private String            openId;

    /**
     * 邀请码
     */
    private String            inviteCode;

    /**
     * 活动代码
     */
    private String            activityCode;

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
     * 是否删除 0-否，1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

    /**
     * 关注状态 0-取消关注,1-关注
     */
    private Integer           concernStatus;

}