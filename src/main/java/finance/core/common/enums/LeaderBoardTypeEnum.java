package finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>排行榜类型</p>
 * @author lili
 * @version $Id: LeaderBoardTypeEnum.java, v0.1 2018/10/19 5:07 PM lili Exp $
 */
@Getter
public enum LeaderBoardTypeEnum {

                                 /**
                                  * 一级排行榜
                                  */
                                 ALL_LEVEL("0", "all", "总排行榜"),
                                 /**
                                  * 一级排行榜
                                  */
                                 FIRST_LEVEL("1", "first", "一级排行榜"),
                                 /**
                                  *  二级排行榜
                                  */
                                 SECOND_LEVEL("2", "second", "二级排行榜");
    private String ranking;
    /**
     *  编码
     */
    private String code;
    /**
     *  描述
     */
    private String desc;

    LeaderBoardTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    LeaderBoardTypeEnum(String ranking, String code, String desc) {
        this.ranking = ranking;
        this.code = code;
        this.desc = desc;
    }

    public static LeaderBoardTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            LeaderBoardTypeEnum[] var1 = values();
            for (LeaderBoardTypeEnum value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }

    public static LeaderBoardTypeEnum getByRanking(String ranking) {
        if (StringUtils.isBlank(ranking)) {
            return null;
        } else {
            LeaderBoardTypeEnum[] var1 = values();
            for (LeaderBoardTypeEnum value : var1) {
                if (value.ranking.equals(ranking)) {
                    return value;
                }
            }
            return null;
        }
    }
}
