package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>子模块</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleTypeEnum.java, v0.1 2018/12/11 3:34 PM PM lili Exp $
 */
@Getter
public enum PopularizeSubModuleTypeEnum {

                                         /**
                                          * 信用卡
                                          */
                                         CREDIT_CARD("credit_card", "信用卡"),
                                         /**
                                          * 贷款
                                          */
                                         LOAN("loan", "贷款");

    private String code;

    private String desc;

    PopularizeSubModuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PopularizeSubModuleTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            PopularizeSubModuleTypeEnum[] var1 = values();
            for (PopularizeSubModuleTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

}
