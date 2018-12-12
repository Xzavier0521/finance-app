package cn.zhishush.finance.api.model.vo.popularize;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>子模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleInfoVO.java, v0.1 2018/12/9 10:36 PM PM lili Exp $
 */
@Data
public class PopularizeSubModuleInfoVO implements Serializable {

    private static final long                    serialVersionUID = 4055576751551327316L;
    /**
     * 模块代码
     */
    private String                               moduleCode;

    /**
     * 子模块代码
     */
    private String                               subModuleCode;

    /**
     * 子模块名称
     */
    private String                               subModuleName;

    /**
     * 子模块类型
     */
    private String                               subModuleType;

    /**
     * 子模块顺序
     */
    private Long                                 subModuleOrder;

    /**
     * 子模块状态 状态 invalid-失效 valid-生效
     */
    private String                               subModuleStatus;

    /**
     * 跳转url
     */
    private String                               redirectUrl;

    /**
     * banner url
     */
    private String                               bannerUrl;

    /**
     * 数据
     */
    private List<? extends PopularizeItemInfoVO> items;
}
