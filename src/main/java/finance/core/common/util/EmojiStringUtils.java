package finance.core.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1。0: EmojiStringUtils.java, v0.1 2018/12/4 2:59 PM PM lili Exp $
 */

public class EmojiStringUtils {

    public static boolean hasEmoji(String content) {
        Pattern pattern = Pattern
            .compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        return matcher.find();
    }

    public static String replaceEmoji(String str) {
        if (!hasEmoji(str)) {
            return str;
        } else {
            str = str.replaceAll(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", " ");
            return str;
        }

    }

}
