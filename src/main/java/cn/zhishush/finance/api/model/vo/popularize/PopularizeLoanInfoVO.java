package cn.zhishush.finance.api.model.vo.popularize;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>推广产品-贷款</p>
 *
 * @author lili
 * @version 1.0: PopularizeLoanInfoVO.java, v0.1 2018/12/11 8:08 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PopularizeLoanInfoVO extends PopularizeItemInfoVO {
    private static final long serialVersionUID = 4858850453544293738L;

    /**
     * 产品代码
     */
    private String            productCode;

    /**
     * 产品代码
     */
    private String            productName;

    /**
     * 产品简述
     */
    private String            productDesc;

    /**
     * 产品logo
     */
    private String            productLogoUrl;

    /**
     * 产品特点 以竖线进行分割 额度高|放款快|利息低
     */
    private String            productFeatures;

    /**
     * 标签
     */
    private String            productTags;

    /**
     * 放款时长
    
     */
    private String            lendingTime;

    /**
     * 贷款额度
    
     */
    private String            loanAmount;

    /**
     * 利息
     */
    private String            loanInterest;

    /**
     * 贷款期限
    
     */
    private String            loanTerm;

    /**
     * 热度
     */
    private String            hotPercent;

    /**
     * 头部标签
    
     */
    private String            productTopTag;

    /**
     * 顺序 
     */
    private Long              productOrder;

    /**
     * 推广文字
     */
    private String            promotionText;
    /**
     * 终端奖励
     */
    private String            terminalBonus;

    /**
     * 直推奖金
     */
    private String            directBonus;

    /**
     * 间推奖金
     */
    private String            indirectBonus;
}
