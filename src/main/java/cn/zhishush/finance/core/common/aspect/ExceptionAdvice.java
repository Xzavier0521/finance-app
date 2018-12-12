package cn.zhishush.finance.core.common.aspect;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.exception.JwtException;
import cn.zhishush.finance.core.common.exception.SignatureException;

/**
 * <p>API方法异常统一拦截</p>
 * @author lili
 * @version 1.0: ExceptionAdvice.java, v0.1 2018/12/9 10:14 PM lili Exp $
 */
@RestControllerAdvice
public class ExceptionAdvice {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(JwtException.class)
    public void jwtError(HttpServletRequest request, HttpServletResponse response,
                         Exception ex) throws IOException {
        response.sendError(401, "jwt is null or timeout");
    }

    @ExceptionHandler(SignatureException.class)
    public void signatureError(HttpServletRequest request, HttpServletResponse response,
                               Exception ex) throws IOException {
        response.sendError(403, "signature is error");
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, TypeMismatchException.class,
                        MissingServletRequestParameterException.class,
                        HttpMediaTypeNotSupportedException.class })
    public void badRequest(HttpServletRequest request, HttpServletResponse response,
                           Exception ex) throws IOException {
        response.sendError(400, "bad request");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void methodNotSupported(HttpServletRequest request, HttpServletResponse response,
                                   Exception ex) throws IOException {
        response.sendError(405, "method not supported");
    }

    @ExceptionHandler
    public ResponseResult<String> others(HttpServletRequest request, HttpServletResponse response,
                                         Exception ex) throws IOException {
        int httpCode = response.getStatus();
        logger.error("httpCode:" + httpCode);
        logger.error("systemError", ex);
        return ResponseResult.error(CodeEnum.systemError);

    }
}
