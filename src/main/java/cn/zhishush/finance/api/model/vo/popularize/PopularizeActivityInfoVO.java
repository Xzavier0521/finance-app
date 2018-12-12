package cn.zhishush.finance.api.model.vo.popularize;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoVO.java, v0.1 2018/12/8 12:09 AM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PopularizeActivityInfoVO extends PopularizeItemInfoVO {
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

}
