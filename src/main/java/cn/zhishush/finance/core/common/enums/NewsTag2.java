package cn.zhishush.finance.core.common.enums;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: NewsTag2.java, v0.1 2018/11/13 2:32 PM PM lili Exp $
 */
public enum NewsTag2 {
                      information("information", "资讯"), knowledge("knowledge", "科普");

    private String code;
    private String msg;

    private NewsTag2(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
