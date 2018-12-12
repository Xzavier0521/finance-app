package cn.zhishush.finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>活动奖励类型</p>
 * @author lili
 * @version 1.0: ActivityRewardTypeEnum.java, v0.1 2018/11/26 11:34 AM lili Exp $
 */
public enum ActivityRewardTypeEnum {

                                    /**
                                     * 一级奖励
                                     */
                                    FIRST_REWARD("first_reward", "一级奖励"),
                                    /**
                                     * 二级奖励
                                     */
                                    SECOND_REWARD("second_reward", "二级奖励"),
                                    /**
                                     * 三级奖励
                                     */
                                    THIRD_REWARD("third_reward", "三级奖励");
    private String code;
    private String msg;

    ActivityRewardTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ActivityRewardTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (ActivityRewardTypeEnum temp : ActivityRewardTypeEnum.values()) {
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
