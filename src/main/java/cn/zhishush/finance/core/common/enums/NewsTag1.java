package cn.zhishush.finance.core.common.enums;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: NewsTag1.java, v0.1 2018/11/13 2:33 PM PM lili Exp $
 */
public enum NewsTag1 {

                      /**
                       * 所有
                       */
                      all("all", "所有"),
                      /**
                       * 信用卡
                       */
                      creditCard("creditCard", "信用卡"),
                      /**
                       * 理财
                       */
                      financing("financing", "理财"),
                      /**
                       * 贷款
                       */
                      loan("loan", "贷款"),
                      /**
                       * 保险
                       */
                      insurance("insurance", "保险");

    private String code;
    private String msg;

    NewsTag1(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Boolean contains(String value) {
        NewsTag1[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            NewsTag1 tag1 = var1[var3];
            if (tag1.getCode().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
