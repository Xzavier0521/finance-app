package finance.web.controller.noauth.login;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Sets;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.response.ValidateResponse;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseResultUtils;
import finance.core.common.util.ValidatorTools;
import finance.domain.dto.LoginParamDto;
import finance.domain.dto.ThirdLoginParamDto;
import finance.domainservice.service.login.LoginService;
import finance.domainservice.service.validate.LoginValidateService;

/**
 * <p>登录服务接口</p>
 * @author lili
 * @version $Id: LoginApi.java, v0.1 2018/11/13 11:40 AM lili Exp $
 */
@Slf4j
@RestController
public class LoginApi {

    private final Set<String>    whitelist = Sets.newHashSet("17192197807");
    @Resource
    private LoginService         loginService;
    @Resource
    private LoginValidateService loginValidateService;

    @PostMapping("login")
    public ResponseResult<Map<String, Object>> login(HttpServletRequest req,
                                                     HttpServletResponse resp,
                                                     @RequestBody XMap paramMap) {
        log.info("[开始用户登陆],请求参数:{}", paramMap);
        ResponseResult<Map<String, Object>> res;
        LoginParamDto paramDto = paramMap.toBean(LoginParamDto.class);
        try {
            if (!whitelist.contains(paramDto.getMobileNum())) {
                loginValidateService.validate(paramDto);
            }
            res = loginService.login(req, resp, paramDto);
        } catch (final Exception e) {
            res = ResponseResultUtils.error(e.getMessage());
            log.error("[用户登陆],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束用户登陆],请求参数:{},返回结果:{}", paramDto, res);
        return res;
    }

    @PostMapping("thirdLogin")
    public ResponseResult<Map<String, Object>> thirdLogin(HttpServletRequest req,
                                                          HttpServletResponse resp,
                                                          @RequestBody XMap paramMap) {
        log.info("[开始第三方账号登陆],请求参数:{}", paramMap);
        ResponseResult<Map<String, Object>> res;
        ThirdLoginParamDto paramDto = paramMap.toBean(ThirdLoginParamDto.class);
        try {
            if (paramDto == null || !paramDto.validateParam()) {
                return ResponseResult.error(CodeEnum.thirdLoginValidateInvalid);
            }
            res = loginService.thirdLogin(req, resp, paramDto);
        } catch (final Exception e) {
            res = ResponseResult.error(CodeEnum.systemError);
            log.error("[第三方账号登陆],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束第三方账号登陆],请求参数:{},返回结果:{}", paramDto, res);
        return res;
    }
}
