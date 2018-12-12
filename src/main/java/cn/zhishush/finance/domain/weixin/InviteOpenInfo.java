package cn.zhishush.finance.domain.weixin;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.zhishush.finance.core.common.enums.ConcernStatusEnum;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: InviteOpenInfo.java, v0.1 2018/10/31 11:07 PM lili Exp $
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InviteOpenInfo implements Serializable {
    private static final long serialVersionUID = 6240222683360442683L;

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
     * 关注状态
     */
    private ConcernStatusEnum concernStatus;

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
}
