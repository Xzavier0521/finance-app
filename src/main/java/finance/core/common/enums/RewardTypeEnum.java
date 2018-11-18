package finance.core.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>奖励类型</p>
 *
 * @author lili
 * @version 1.0: RewardTypeEnum.java, v0.1 2018/11/18 3:09 PM PM lili Exp $
 */
@Getter
public enum RewardTypeEnum {

                            /**
                             * 红包雨
                             */
                            RED_ENVELOPE_RAIN("red_envelope_rain", "红包雨"),
                            /**
                             * 红包雨排行榜
                             */
                            RED_ENVELOPE_RAIN_RANKING("red_envelope_rain_ranking", "红包雨排行榜");
    /**
     *  编码
     */
    private String code;

    /**
     *  描述
     */
    private String desc;

    RewardTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RewardTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            RewardTypeEnum[] var1 = values();
            for (RewardTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
