package cn.zhishush.finance.domain.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>活动执行记录</p>
 *
 * @author lili
 * @version 1.0: ActivityProcessInfo.java, v0.1 2018/12/18 4:00 PM PM lili Exp $
 */
@Data
public class ActivityProcessInfo implements Serializable {

    private static final long serialVersionUID = 3337785152384641108L;
    /**
     * 主键
     */
    private Long              id;

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
     * 是否参与活动
     */
    private String            isParticipate;

    /**
     * 是否完成活动
     */
    private String            isFinished;

    /**
     * 是否成为推广人员
     */
    private String            isPromoter;

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
    private Integer           version;

}
