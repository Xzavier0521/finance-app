package cn.zhishush.finance.core.dal.dataobject.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>用户信息</p>
 * 
 * @author lili
 * @version $Id: UserInfoDO.java, v0.1 2018/11/14 7:14 PM lili Exp $
 */
@Data
public class UserInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private Long              id;

    /**
     * 手机号
     */
    private String            mobileNum;

    /**
     * 邀请码
     */
    private String            inviteCode;

    /**
     * 活动代码，默认-0000 注册的方式
     */
    private String            activityCode;

    /**
     * 用户状态（1：正常）
     */
    private String            status;

    /**
     * 是否已删除(0:否；1:是)
     */
    private Integer           isDelete;

    /**
     * 创建人
     */
    private String            creator;

    /**
     * 更新人
     */
    private String            updator;

    /**
     * 版本号
     */
    private Integer           version;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

}