package cn.zhishush.finance.domain.popularize;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoVO.java, v0.1 2018/12/9 9:48 PM PM lili Exp $
 */
@Data
public class PopularizeMaterialInfo implements Serializable {
    private static final long serialVersionUID = 8855530334334396196L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 子模块代码
     */
    private String            subModuleCode;

    /**
     * 素材代码
     */
    private String            materialCode;

    /**
     * 素材类型
     */
    private String            materialType;

    /**
     * 标题
     */
    private String            materialTitle;

    /**
     * 正文
     */
    private String            content;

    /**
     * banner url
     */
    private String            firstBannerUrl;

    /**
     * banner url
     */
    private String            secondBannerUrl;

    /**
     * 顺序
     */
    private Long              materialOrder;

    /**
     * 状态 invalid-失效 valid-生效
     */
    private String            materialStatus;

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
