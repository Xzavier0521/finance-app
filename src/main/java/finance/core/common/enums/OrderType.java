package finance.core.common.enums;

/**
 * <p>订单状态</p>
 *
 * @author lili
 * @version 1.0: OrderType.java, v0.1 2018/11/14 2:13 PM PM lili Exp $
 */
public enum OrderType {

                       /**
                        * 提现
                        */
                       debit("debit", "提现"),
                       /**
                        * 充值
                        */
                       charge("charge", "充值");
    private String code;
    private String msg;

    OrderType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
