package cn.zhishush.finance.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: WithDrawOrderStatus.java, v0.1 2018/11/24 6:28 PM lili Exp $
 */
public enum WithDrawOrderStatus {
                                 init("01",
                                      "初始化"), withDrawDealing("02",
                                                              "处理中"), withDrawSuccess("00",
                                                                                      "提现成功"), withDrawFail("03",
                                                                                                            "提现失败");

    // 业务code
    private static final Map<String, String> param = new HashMap<>();

    static {
        for (WithDrawOrderStatus status : WithDrawOrderStatus.values()) {
            param.put(status.getCode(), status.getMsg());
        }
    }

    private String code;
    private String msg;

    WithDrawOrderStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Map<String, String> getParam() {
        return param;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "CodeEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
    }
}
