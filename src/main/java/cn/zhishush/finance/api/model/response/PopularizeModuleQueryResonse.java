package cn.zhishush.finance.api.model.response;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleQueryResonse.java, v0.1 2018/12/7 11:33 PM PM lili Exp $
 */
@Data
public class PopularizeModuleQueryResonse implements Serializable {

    private static final long serialVersionUID = 115850685106485194L;
    /**
     * 模块代码
     */
    private String            moduleCode;

    /**
     * 模块名称
     */
    private String            moduleName;

    /**
     * 模块类型
     */
    private String            moduleType;

    /**
     * 模块顺序
     */
    private Long              moduleOrder;

    /**
     * 模块状态 invalid-失效 valid-生效
     */
    private String            moduleStatus;

}
