package finance.domainservice.service.auth;

import finance.api.model.response.ResponseResult;
import finance.domain.dto.UserBankCardDto;

public interface AuthService {
	/**
	 * 身份认证
	 *
	 * @param bankCardDto
	 * @return
	 * @throws @author
	 *             panzhongkang
	 * @date 2018/8/22 10:34
	 */
	ResponseResult<String> auth(UserBankCardDto bankCardDto);
}
