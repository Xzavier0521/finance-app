package finance.domainservice.service.login;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finance.api.model.response.ResponseResult;
import finance.domain.dto.LoginParamDto;
import finance.domain.dto.ThirdLoginParamDto;

/**
 * <p>登录逻辑</p>
 * @author lili
 * @version $Id: LoginService.java, v0.1 2018/11/14 9:23 AM lili Exp $
 */
public interface LoginService {

    /**
     * 用户登陆
     * @param req 请求参数
     * @param resp 响应结果
     * @param paramDto 登陆参数
     * @return ResponseResult<Map<String, Object>>
     */
    ResponseResult<Map<String, Object>> login(HttpServletRequest req, HttpServletResponse resp,
                                              LoginParamDto paramDto);

    /**
     * 第三方账号登录
     * @param req 请求参数
     * @param resp 响应结果
     * @param paramDto 登陆参数
     * @return ResponseResult<Map<String, Object>>
     */
    ResponseResult<Map<String, Object>> thirdLogin(HttpServletRequest req, HttpServletResponse resp,
                                                   ThirdLoginParamDto paramDto);

}
