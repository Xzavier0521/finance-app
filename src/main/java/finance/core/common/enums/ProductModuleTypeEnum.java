package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>产品模块类型</p>
 * @author lili
 * @version $Id: ProductModuleTypeEnum.java, v0.1 2018/11/8 11:46 AM lili Exp $
 */
public enum ProductModuleTypeEnum {

    /**
     * 信用卡专区
     */
    CREDIT_CARD_MODULE("credit_card_module", "信用卡专区"),
    /**
     * 贷款专区
     */
    LOAN_MODULE("loan_module", "贷款专区"),

    /**
     * 投资专区
     */
    INVESTMENT_MODULE("investment_module", "投资专区"),

    /**
     * 保险专区
     */
    INSURANCE_MODULE("insurance_module", "保险专区");

    private String code;

    private String desc;

    ProductModuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ProductModuleTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            ProductModuleTypeEnum[] var1 = values();
            for (ProductModuleTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
