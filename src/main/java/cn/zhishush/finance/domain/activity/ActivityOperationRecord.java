package cn.zhishush.finance.domain.activity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import cn.zhishush.finance.core.common.enums.ActivityRewardTypeEnum;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecord.java, v0.1 2018/11/26 1:58 PM lili Exp $
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivityOperationRecord implements Serializable {
    private static final long      serialVersionUID = 2382928359345752704L;
    /**
     * 主键
     */
    private Long                   id;

    /**
     * 活动代码
     */
    private String                 activityCode;

    /**
     * 用户id
     */
    private Long                   userId;

    /**
     * 手机号码
     */
    private String                 mobileNum;

    /**
     * 奖励类型
     */
    private ActivityRewardTypeEnum rewardType;

    /**
     * 创建时间
     */
    private Date                   createTime;

    /**
     * 更新时间
     */
    private Date                   updateTime;

    /**
     * 创建者
     */
    private String                 creator;

    /**
     * 更新者
     */
    private String                 updator;

    /**
     * 是否删除 0-否，1-是
     */
    private Integer                isDelete;

    /**
     * 版本号
     */
    private Long                   version;

}
