package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>返现类型</p>
 *
 * @author lili
 * @version 1.0: CashBackTypeEnum.java, v0.1 2018/12/12 9:50 AM PM lili Exp $
 */
@Getter
public enum CashBackTypeEnum {

                              /**
                               * 金额
                               */
                              AMOUNT("amount", "金额"),

                              /**
                               * 金额
                               */
                              PERCENTAGE("percentage", "百分比");
    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    CashBackTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CashBackTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            CashBackTypeEnum[] var1 = values();
            for (CashBackTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
