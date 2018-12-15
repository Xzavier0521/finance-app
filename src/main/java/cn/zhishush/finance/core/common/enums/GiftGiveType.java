package cn.zhishush.finance.core.common.enums;

/**
 * <p>礼物领取类型</p>
 *
 * @author lili
 * @version 1.0: GiftGiveType.java, v0.1 2018/11/14 2:09 PM PM lili Exp $
 */
public enum GiftGiveType {
                          unGiving(0, "待领取"), giving(1, "已领取");
    private Integer code;
    private String  msg;

    GiftGiveType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
