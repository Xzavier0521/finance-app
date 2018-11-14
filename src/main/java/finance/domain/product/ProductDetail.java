package finance.domain.product;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>产品明细</p>
 *
 * @author lili
 * @version 1.0: ProductDetail.java, v0.1 2018/11/8 1:54 PM PM lili Exp $
 */
@Data
public class ProductDetail implements Serializable {

    private static final long serialVersionUID = 8535828645121092035L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 产品模块代码
     */
    private String            moduleCode;

    /**
     *  产品代码
     */
    private String            productCode;

    /**
     * 产品名称
     */
    private String            productName;

    /**
     * 产品图片url
     */
    private String            productBannerUrl;

    /**
     *  产品跳转url
     */
    private String            productDirectUrl;
    /**
     *  产品logo url
     */
    private String            productLogoUrl;
    /**
     * 产品顺序
     */
    private Integer           productOrder;

    /**
     *  产品状态 0-无效 1-有效
     */
    private String            productStatus;

    /**
     * 创建时间
     */
    private Date              createTime;

    /**
     * 更新时间
     */
    private Date              updateTime;

    /**
     * 创建时间
     */
    private String            creator;

    /**
     * 更新者
     */
    private String            updator;

    /**
     * 是否删除 0-否，1-是
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;

}
