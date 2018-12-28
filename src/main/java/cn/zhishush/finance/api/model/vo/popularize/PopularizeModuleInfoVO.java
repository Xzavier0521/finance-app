package cn.zhishush.finance.api.model.vo.popularize;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p>推广模块信息</p>
 * @author lili
 * @version 1.0: PopularizeModuleInfoVO.java, v0.1 2018/12/7 12:12 AM lili Exp $
 */
@Data
public class PopularizeModuleInfoVO implements Serializable {
    private static final long               serialVersionUID = -8303296258943671500L;

    /**
     * 主键
     */
    private Long                            id;
    /**
     * 模块代码
     */
    private String                          info;
    /**
     * 模块名称
     */
    private String                          moduleName;
    /**
     * 模块类型
     */
    private String                          moduleType;
    /**
     * 模块顺序
     */
    private Long                            moduleOrder;
    /**
     * 模块状态 invalid-失效 valid-生效
     */
    private String                          moduleStatus;
    /**
     * 子模块信息
     */
    private List<PopularizeSubModuleInfoVO> subModules;
}
