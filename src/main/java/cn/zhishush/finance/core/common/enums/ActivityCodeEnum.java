package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>活动代码枚举</p>
 * 
 * @author lili
 * @version $Id: ActivityCodeEnum.java, v0.1 2018/10/11 1:40 PM lili Exp $
 */
@Getter
public enum ActivityCodeEnum {

                              /**
                               * 阶梯红包
                               */
                              STEP_RED_ENVELOPE("0001", "阶梯红包"),
                              /**
                               * 红包雨
                               */
                              RED_ENVELOPE_RAIN("1001", "红包雨"),
                              /**
                               * 奥买家注册活动
                               */
                              AO_MAI_JIA_REGISTER("2001", "奥买家注册活动");

    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    ActivityCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ActivityCodeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            ActivityCodeEnum[] var1 = values();
            for (ActivityCodeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
