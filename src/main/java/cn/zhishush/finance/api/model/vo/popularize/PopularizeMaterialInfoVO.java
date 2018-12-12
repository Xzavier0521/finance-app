package cn.zhishush.finance.api.model.vo.popularize;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoVO.java, v0.1 2018/12/9 9:48 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PopularizeMaterialInfoVO extends PopularizeItemInfoVO {
    private static final long serialVersionUID = 8855530334334396196L;

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

}
