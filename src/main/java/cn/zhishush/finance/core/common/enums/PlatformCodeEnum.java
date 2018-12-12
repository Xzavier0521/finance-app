package cn.zhishush.finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>平台编码</p>
 * 
 * @author lili
 * @version 1.0: PlatformCodeEnum.java, v0.1 2018/11/13 6:35 PM lili Exp $
 */
public enum PlatformCodeEnum {

                              /**
                               * 注册宝
                               */
                              REGISTER_HOME("register_home", "注册宝"),
                              /**
                               * 金融家
                               */
                              FINANCE_HOME("finance_home", "金融家"),
                              /**
                               * 金融家集合页
                               */
                              FINANCE_HOME_LIST("finance_home_list", "金融家集合页");
    private String code;
    private String desc;

    PlatformCodeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static PlatformCodeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (PlatformCodeEnum temp : PlatformCodeEnum.values()) {
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
