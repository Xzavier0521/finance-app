package finance.core.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CommonUtils.java, v 0.1 2018/9/29 上午10:57 lili Exp $
 */
public class CommonUtils {

    /**
     * 手机号码前三后四脱敏
     * @param phoneNumber 手机号码
     * @return String
     */
    public static String mobileEncrypt(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber) || (phoneNumber.length() != 11)) {
            return phoneNumber;
        }
        return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证前三后四脱敏
     * @param id  身份证号码
     * @return String
     */
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    /**
     * 护照前2后3位脱敏，护照一般为8或9位
     * @param id 护照号码
     * @return  String
     */
    public static String idPassport(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.substring(0, 2) + new String(new char[id.length() - 5]).replace("\0", "*")
               + id.substring(id.length() - 3);
    }

    public static String decimalFormat(BigDecimal money) {
        DecimalFormat df = new DecimalFormat("0.00");
        if (Objects.isNull(money)) {
            return StringUtils.EMPTY;
        }
        return df.format(money);
    }
}
