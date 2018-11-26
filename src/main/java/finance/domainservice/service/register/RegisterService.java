package finance.domainservice.service.register;

import finance.domain.dto.LoginParamDto;
import finance.core.dal.dataobject.UserInfoDO;

/**
 * <p>
 * 用户注册服务接口
 * </p>
 * 
 * @author lili
 * @version $Id: RegisterService.java, v0.1 2018/11/13 4:42 PM lili Exp $
 */
public interface RegisterService {

	/**
	 * 用户注册
	 * 
	 * @param paramDto
	 *            注册参数
	 * @return UserInfoDO
	 */
	UserInfoDO registerUser(LoginParamDto paramDto);
}
