package finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>活动类型</p>
 *
 * @author lili
 * @version 1.0: ActivityType.java, v0.1 2018/11/13 2:26 PM PM lili Exp $
 */
public enum ActivityType {
                          /**
                           * 无活动
                           */
                          normal("0", "无活动"),
                          /**
                           * 阶梯红包
                           */
                          step_red_envelope("1", "阶梯红包"),
                          /**
                           * 固定红包
                           */
                          fixed_red_envelope("2", "固定红包"),
                          /**
                           * 红包雨
                           */
                          red_envelope_rain("3", "红包雨");
    private String code;
    private String msg;

    ActivityType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ActivityType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityType temp : ActivityType.values()) {
            if (temp.getCode().equals(code)) {
                return temp;
            }
        }
        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
