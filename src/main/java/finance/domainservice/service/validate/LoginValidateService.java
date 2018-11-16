package finance.domainservice.service.validate;

import finance.api.model.response.ValidateResponse;
import finance.domain.dto.LoginParamDto;

/**
 * <p>用户登陆参数验证</p>
 *
 * @author lili
 * @version 1.0: LoginValidateService.java, v0.1 2018/11/14 4:11 PM PM lili Exp $
 */
public interface LoginValidateService {

    /**
     *  用户登陆参数验证
     * @param paramDto 登陆参数
     */
    void validate(LoginParamDto paramDto);
}
