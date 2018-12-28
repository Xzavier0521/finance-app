package cn.zhishush.finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @program: finance-app
 * @description:
 * @author: Mr.Zhang
 * @create: 2018-12-25 19:03
 **/
public enum  UserInviteInfoEnum {

    /**
     * 第一次红包雨
     */
    FIRST("first", "一级好友"),
    /**
     * 第二次红包雨
     */
    SECOND("second", "二级好友"),
    /**
     * 第三次红包雨
     */
    SELF("self", "本人");

    private String code;
    private String desc;

    UserInviteInfoEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserInviteInfoEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (UserInviteInfoEnum temp : UserInviteInfoEnum.values()) {
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
