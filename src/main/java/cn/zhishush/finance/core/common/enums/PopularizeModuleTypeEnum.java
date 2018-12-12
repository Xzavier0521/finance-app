package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>推广模块类型</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleTypeEnum.java, v0.1 2018/12/11 3:24 PM PM lili Exp $
 */
@Getter
public enum PopularizeModuleTypeEnum {

                                      /**
                                       * 活动
                                       */
                                      ACTIVITY("activity", "活动"),
                                      /**
                                       * 产品
                                       */
                                      PRODUCT("product", "产品"),
                                      /**
                                       * 素材
                                       */
                                      MATERIAL("material", "素材");

    private String code;

    private String desc;

    PopularizeModuleTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PopularizeModuleTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            PopularizeModuleTypeEnum[] var1 = values();
            for (PopularizeModuleTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

}
