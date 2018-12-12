package cn.zhishush.finance.domainservice.service.validate.impl;

import static cn.zhishush.finance.core.common.util.PreconditionUtils.checkArgument;

import java.util.Objects;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import cn.zhishush.finance.api.model.response.ValidateResponse;
import cn.zhishush.finance.core.common.constants.Constant;
import cn.zhishush.finance.core.common.enums.LoginType;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.core.common.util.ValidatorTools;
import cn.zhishush.finance.domain.dto.LoginParamDto;
import cn.zhishush.finance.domainservice.service.validate.LoginValidateService;

/**
 * <p>
 * 用户登陆参数验证
 * </p>
 *
 * @author lili
 * @version 1.0: LoginValidateServiceImpl.java, v0.1 2018/11/14 4:11 PM PM lili
 *          Exp $
 */
@Slf4j
@Service("loginValidateService")
public class LoginValidateServiceImpl implements LoginValidateService {

	@Override
	public void validate(LoginParamDto paramDto) {
		checkArgument(Objects.nonNull(paramDto), ReturnCode.PARAM_EMPTY);
		ValidateResponse validateResponse = ValidatorTools.validate(paramDto);
		PreconditionUtils.checkArgument(validateResponse.isStatus(), validateResponse.getErrorMsg());
		// 公共验证
		checkArgument(Constant.platform_code.containsKey(paramDto.getPlatformCode()), "平台编码不合法");
		Set<LoginType> loginTypeSet = Sets.newHashSet(LoginType.IMG_MOBILE, LoginType.WE_CHAT, LoginType.MOBILE,
				LoginType.QQ);
		checkArgument(loginTypeSet.contains(LoginType.getByCode(paramDto.getType())), "登录类型不合法");
	}
}
