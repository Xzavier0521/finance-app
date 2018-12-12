package cn.zhishush.finance.domain.popularize;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>推广模块信息</p>
 * @author lili
 * @version 1.0: PopularizeModuleInfoDO.java, v0.1 2018/12/7 12:12 AM lili Exp $
 */
@Data
public class PopularizeModuleInfo implements Serializable {
    private static final long serialVersionUID = -8303296258943671500L;
    /**
     * 主键
     */
    private Long              id;

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
