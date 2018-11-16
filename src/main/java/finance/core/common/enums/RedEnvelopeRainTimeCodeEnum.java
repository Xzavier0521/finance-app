package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>红包雨活动时间枚举¬</p>
 * @author lili
 * @version 1.0: RedEnvelopeRainTimeCodeEnum.java, v0.1 2018/11/14 5:38 PM lili Exp $
 */
public enum RedEnvelopeRainTimeCodeEnum {

                                         /**
                                          * 第一次红包雨
                                          */
                                         FIRST("first", "第一次"),
                                         /**
                                          * 第二次红包雨
                                          */
                                         SECOND("second", "第二次"),
                                         /**
                                          * 第三次红包雨
                                          */
                                         THIRD("third", "第三次");

    private String code;
    private String desc;

    RedEnvelopeRainTimeCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RedEnvelopeRainTimeCodeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (RedEnvelopeRainTimeCodeEnum temp : RedEnvelopeRainTimeCodeEnum.values()) {
            if (temp.getCode().equals(code)) {
                return temp;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
