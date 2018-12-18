package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>活动事件类型</p>
 *
 * @author lili
 * @version 1.0: ActivityEventTypeEnum.java, v0.1 2018/12/18 7:19 PM PM lili Exp $
 */
@Getter
public enum ActivityEventTypeEnum {

                                   /**
                                    * 参与活动
                                    */
                                   JOIN("join", "参与活动"),
                                   /**
                                    * 完成活动
                                    */
                                   DONE("done", "完成活动"),
                                   /**
                                    * 成为推广人员
                                    */
                                   OLDER_JOIN("older_join", "成为推广人员");
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String desc;

    ActivityEventTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ActivityEventTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            ActivityEventTypeEnum[] var1 = values();
            for (ActivityEventTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
