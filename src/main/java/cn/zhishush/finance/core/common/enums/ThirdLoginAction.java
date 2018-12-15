package cn.zhishush.finance.core.common.enums;

/**
 * <p>第三方登录操作类型</p>
 *
 * @author lili
 * @version 1.0: ThirdLoginAction.java, v0.1 2018/11/14 2:07 PM PM lili Exp $
 */
public enum ThirdLoginAction {
                              bind(), unbind();

    ThirdLoginAction() {

    }

    public static Boolean contains(String value) {
        for (ThirdLoginAction type : ThirdLoginAction.values()) {
            if (type.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}