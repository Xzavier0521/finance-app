package cn.zhishush.finance.domain.popularize;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>推广子模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleInfo.java, v0.1 2018/12/8 12:00 AM lili Exp $
 */
@Data
public class PopularizeSubModuleInfo implements Serializable {
    private static final long serialVersionUID = -2430712155046776181L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 模块代码
     */
    private String            moduleCode;

    /**
     * 子模块代码
     */
    private String            subModuleCode;

    /**
     * 子模块名称
     */
    private String            subModuleName;

    /**
     * 子模块类型
     */
    private String            subModuleType;

    /**
     * 子模块顺序
     */
    private Long              subModuleOrder;

    /**
     * 子模块状态 状态 invalid-失效 valid-生效
     */
    private String            subModuleStatus;

    /**
     * 跳转url
     */
    private String            redirectUrl;

    /**
     * banner url
     */
    private String            bannerUrl;

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