package finance.ext.api.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>云聚合返回码</p>
 *
 * @author lili
 * @version 1.0: YunJuHeReturnCode.java, v0.1 2018/11/28 7:31 PM PM lili Exp $
 */
public enum YunJuHeReturnCode {

                               /**
                                * 成功
                                */
                               SUCCESS("0", "成功"),
                               /**
                                * 失败
                                */
                               FAILURE("1", "失败");
    private String code;
    private String msg;

    YunJuHeReturnCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static YunJuHeReturnCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (YunJuHeReturnCode temp : YunJuHeReturnCode.values()) {
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
