package finance.core.common.util;

import lombok.extern.slf4j.Slf4j;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: PreconditionUtils.java, v 0.1 2018/5/16 lili Exp $$
 */
@Slf4j
public class PreconditionUtils {

    public static void checkArgument(boolean expression, @NullableDecl ReturnCode returnCode) {
        if (!expression) {
            throw new BizException(returnCode);
        }
    }

    public static void checkArgument(boolean expression, @NullableDecl ReturnCode returnCode,
                                     @NullableDecl String errorMessageTemplate,
                                     @NullableDecl Object... p1) {
        if (!expression) {
            String msg = format(errorMessageTemplate, p1);
            log.error(msg);
            throw new BizException(returnCode);
        }
    }

    public static void checkArgument(boolean expression, boolean isCodeMsg,
                                     @NullableDecl ReturnCode returnCode,
                                     @NullableDecl String errorMessageTemplate,
                                     @NullableDecl Object... p1) {
        if (!expression) {
            String msg = format(errorMessageTemplate, p1);
            log.error(msg);
            throw new BizException(returnCode, isCodeMsg ? returnCode.getDesc() : msg);
        }
    }

    public static void checkArgument(boolean expression, @NullableDecl String errorMessage) {
        if (!expression) {
            throw new BizException(errorMessage);
        }
    }

    public static void checkArgument(boolean b, @NullableDecl String errorMessageTemplate,
                                     @NullableDecl Object... p1) {
        if (!b) {
            throw new BizException(format(errorMessageTemplate, p1));
        }
    }

    private static String format(@NullableDecl String template, @NullableDecl Object... args) {
        template = String.valueOf(template);
        args = args == null ? new Object[] { "(Object[])null" } : args;
        StringBuilder builder = new StringBuilder(template.length() + 16 * args.length);
        int templateStart = 0;
        int i = 0;
        while (i < args.length) {
            int placeholderStart = template.indexOf("%s", templateStart);
            if (placeholderStart == -1) {
                break;
            }
            builder.append(template, templateStart, placeholderStart);
            builder.append(args[i++]);
            templateStart = placeholderStart + 2;
        }
        builder.append(template, templateStart, template.length());
        if (i < args.length) {
            builder.append(" [");
            builder.append(args[i++]);
            while (i < args.length) {
                builder.append(", ");
                builder.append(args[i++]);
            }
            builder.append(']');
        }
        return builder.toString();
    }

}
