package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: WeChatSendStatusEnum.java, v0.1 2018/12/4 10:52 AM PM lili Exp $
 */
@Getter
public enum WeChatSendStatusEnum {

                                  /**
                                   * 未发送
                                   */
                                  UN_SEND("N", "未发送"),
                                  /**
                                   * 已经发送
                                   */
                                  HAS_SEND("Y", "已经发送");
    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    WeChatSendStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WeChatSendStatusEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            WeChatSendStatusEnum[] values = values();
            for (WeChatSendStatusEnum value : values) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
