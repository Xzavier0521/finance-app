package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>产品类型</p>
 * @author lili
 * @version 1.0: ProductTypeEnum.java, v0.1 2018/12/14 5:21 PM lili Exp $
 */
@Getter
public enum ProductTypeEnum {

                             /**
                              * 信用卡专区
                              */
                             CREDIT_CARD("credit_card", "信用卡"),
                             /**
                              * 贷款专区
                              */
                             LOAN("loan", "贷款"),

                             /**
                              * 投资专区
                              */
                             INVESTMENT("investment", "投资"),

                             /**
                              * 保险专区
                              */
                             INSURANCE("insurance", "保险");

    private String code;

    private String desc;

    ProductTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ProductTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            ProductTypeEnum[] var1 = values();
            for (ProductTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
