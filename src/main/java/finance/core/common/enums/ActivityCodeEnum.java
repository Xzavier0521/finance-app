package finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityCodeEnum.java, v0.1 2018/10/11 1:40 PM lili Exp $
 */
@Getter
public enum ActivityCodeEnum {

                              /**
                               * ebay 一美分
                               */
                              EBAY_ONE_CENT("0001", "ebay 一美分推广活动");
    /**
     *  编码
     */
    private String code;

    /**
     *  描述
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
