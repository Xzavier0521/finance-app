package finance.domain.creditcard;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * <p>信用卡明细</p>
 *
 * @author lili
 * @version 1.0: CreditCardDetails.java, v0.1 2018/11/29 1:30 AM PM lili Exp $
 */
@Data
public class CreditCardDetails implements Serializable {
    private static final long serialVersionUID = -4932352983511149108L;
    /**
     * 主键
     */
    private Long              id;

    /**
     * 信用卡代码
     */
    private String            cardCode;

    /**
     * 信用卡名称
     */
    private String            cardName;

    /**
     * banner url
     */
    private String            cardBannerUrl;

    /**
     * 跳转第三方url
     */
    private String            redirectUrl;

    /**
     * 信用卡简述
     */
    private String            cardDetailDesc;

    /**
     * 标签
     */
    private String            cardDetailTag;

    /**
     * 推广返佣配置id
     */
    private String              cashbackConfigId;

    /**
     * 办卡福利
     */
    private String            cardWelfare;

    /**
     * 办卡条件
     */
    private String            cardConditions;

    /**
     * 办卡基本信息
     */
    private String            cardBasicInfo;

    /**
     * 办卡流程
     */
    private String            cardProcessUrl;

    /**
     * 分享图片url
     */
    private String            shareImgUrl;

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
     * 是否删除
     */
    private String            isDelete;

    /**
     * 版本号
     */
    private Long              version;
}
