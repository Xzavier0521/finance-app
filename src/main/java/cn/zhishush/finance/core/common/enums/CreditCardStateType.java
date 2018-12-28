package cn.zhishush.finance.core.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: StatusType.java, v0.1 2018/11/13 2:31 PM lili Exp $
 */
@Getter
public enum CreditCardStateType {

                        /**
                         * 否
                         */
                        not_queried("not_queried", "未查询 "),
                        /**
                         * 是
                         */
                        check_sucess("check_sucess", "核卡成功 "),

                        check_failure("check_failure", "核卡失败 ");
    /**
     * 编码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    CreditCardStateType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public static CreditCardStateType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            CreditCardStateType[] var1 = values();
            for (CreditCardStateType value : var1) {
                if (value.code.equals(code)) {
                    return value;
                }
            }
            return null;
        }
    }
}
