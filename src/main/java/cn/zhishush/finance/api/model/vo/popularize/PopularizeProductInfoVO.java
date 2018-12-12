package cn.zhishush.finance.api.model.vo.popularize;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>推广产品信息</p>
 * @author lili
 * @version 1.0: PopularizeProductInfoVO.java, v0.1 2018/12/9 9:13 PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PopularizeProductInfoVO extends PopularizeItemInfoVO {
    private static final long serialVersionUID = -3384325560433724580L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 子模块代码
     */
    private String            subModuleCode;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品类型
     */
    private String            productType;

    /**
     * 状态 invalid-失效 valid-生效
     */
    private String            productStatus;

}