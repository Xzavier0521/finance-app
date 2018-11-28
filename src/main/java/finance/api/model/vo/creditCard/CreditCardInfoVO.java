package finance.api.model.vo.creditCard;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>信用卡信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardInfoVO.java, v0.1 2018/11/27 11:13 AM PM lili Exp $
 */
@Data
public class CreditCardInfoVO implements Serializable {
    private static final long serialVersionUID = 7615627703771834728L;

    /**
     * 信用卡代码 
     */
    private String            cardCode;

    /**
     * 信用卡名称
     */
    private String            cardName;

    /**
     * 信用卡logo
     */
    private String            cardLogoUrl;

    /**
     * 标签
     */
    private String            cardTag;

    /**
     * 额度
     */
    private String            cardLimitStr;

    /**
     * 预计通过率
     */
    private String            predictPassingRateStr;

    /**
     * 卡类型
     */
    private String            cardTypes;

    /**
     * 顺序
     */
    private Long           order;

}
