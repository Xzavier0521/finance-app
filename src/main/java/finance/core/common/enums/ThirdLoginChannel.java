package finance.core.common.enums;

/**
 * <p>第三方登录渠道</p>
 *
 * @author lili
 * @version 1.0: ThirdLoginChannel.java, v0.1 2018/11/14 2:07 PM PM lili Exp $
 */
public enum ThirdLoginChannel {
                               qq(), wechat();

    ThirdLoginChannel() {

    }

    public static Boolean contains(String value) {
        for (ThirdLoginChannel type : ThirdLoginChannel.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}