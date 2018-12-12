package cn.zhishush.finance.domainservice.service.auth;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.domain.dto.UserBankCardDto;

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
