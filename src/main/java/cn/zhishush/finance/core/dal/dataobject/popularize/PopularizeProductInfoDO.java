package cn.zhishush.finance.core.dal.dataobject.popularize;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>推广产品信息</p>
 * @author lili
 * @version 1.0: PopularizeProductInfoDO.java, v0.1 2018/12/9 9:13 PM lili Exp $
 */
@Data
public class PopularizeProductInfoDO implements Serializable {
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
    /**
     * 推广文字
     */
    private String            promotionText;

    /**
     * 顺序
     */
    private Long           productOrder;
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