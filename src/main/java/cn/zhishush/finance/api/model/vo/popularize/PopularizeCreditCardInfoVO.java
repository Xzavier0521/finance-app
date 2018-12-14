package cn.zhishush.finance.api.model.vo.popularize;

import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>推广产品-信用卡</p>
 *
 * @author lili
 * @version 1.0: PopularizeCreditCardInfoVO.java, v0.1 2018/12/11 8:26 PM PM lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PopularizeCreditCardInfoVO extends PopularizeItemInfoVO {

    private static final long serialVersionUID = -8106641061272019797L;
    /**
     * 银行代码
     */
    private String            bankCode;

    /**
     * 信用卡代码
     */
    private String            cardCode;

    /**
     * 信用卡名称
     */
    private String            cardName;

    /**
     * logo url
     */
    private String            cardLogoUrl;
    /**
     * 标签
     */
    private String            cardTag;
    /**
     * 起始额度
     */
    private BigDecimal        cardLimitMin;

    /**
     * 结束额度
     */
    private BigDecimal        cardLimitMax;

    /**
     * 额度
     */
    private String            cardLimitStr;

    /**
     * 预计通过率
     */
    private String            predictPassingRateStr;

    /**
     * 顺序
     */
    private Long              productOrder;

    /**
     * 卡类型:1-热门,2-白金卡,3-双币卡
     */
    private String            cardTypes;

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
