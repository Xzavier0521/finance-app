package cn.zhishush.finance.domain.popularize;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoVO.java, v0.1 2018/12/8 12:09 AM PM lili Exp $
 */
@Data
public class PopularizeActivityInfo implements Serializable {
    private static final long serialVersionUID = -2847993411415110300L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 子模块代码
     */
    private String            subModuleCode;

    /**
     * 活动代码
     */
    private String            activityCode;

    /**
     * 活动名称
     */
    private String            activityName;

    /**
     * banner url
     */
    private String            bannerUrl;

    /**
     * 跳转url
     */
    private String            redirectUrl;

    /**
     * 顺序
     */
    private Long              activityOrder;

    /**
     * 状态 run-进行中 end-已经结束
     */
    private String            activityStatus;

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
