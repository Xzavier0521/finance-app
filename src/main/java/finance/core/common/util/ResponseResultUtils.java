package finance.core.common.util;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;

/**
 * <p>注释</p>
 *x
 * @author lili
 * @version 1.0: ResponseResultUtils.java, v0.1 2018/11/13 6:09 PM PM lili Exp $
 */
public class ResponseResultUtils {

    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<T>("0000000", message, data);
    }

    public static <T> ResponseResult<T> success(T data) {

        return new ResponseResult<T>("0000000", "success", data);
    }

    @Deprecated
    public static <T> ResponseResult<T> error(String errorCode, String message) {
        return new ResponseResult<T>(errorCode, message, null);
    }

    public static <T> ResponseResult<T> error(CodeEnum codeEnum) {
        return new ResponseResult<T>(codeEnum.getCode(), codeEnum.getMsg(), null);
    }

    public static ResponseResult error(String errorMsg) {
        return new ResponseResult(CodeEnum.systemError.getCode(), errorMsg);
    }
}
