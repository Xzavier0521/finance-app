package cn.zhishush.finance.api.model.vo.creditCard;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>信用卡银行信息</p>
 *
 * @author lili
 * @version 1.0: BankInfo.java, v0.1 2018/11/27 9:59 AM PM lili Exp $
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BankInfoVO implements Serializable {

    private static final long serialVersionUID = -6354675823214124195L;
    /**
     * 银行代码
     */
    private String            bankCode;

    /**
     * 银行名称
     */
    private String            bankName;

    /**
     * 银行logo
     */
    private String            bankLogoUrl;

    /**
     * 标签
     */
    private String            bankTag;

    /**
     * 简介
     */
    private String            bankIntroduction;

    /**
     * 额度
     */
    private String            bankLimitStr;

    /**
     * 预计通过率
     */
    private String            predictPassingRateStr;

    /**
     * 顺序
     */
    private Long            order;

}
