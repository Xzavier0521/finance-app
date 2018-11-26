package finance.web.controller.noauth.common;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.KeyGeneratorResponse;
import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.activity.KeyGeneratorService;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: IncreaseKeyController.java, v0.1 2018/11/6 2:53 PM lili Exp $
 */
@RestController
@RequestMapping("api/key")
public class IncreaseKeyController {

	@Resource
	private KeyGeneratorService keyGeneratorService;

	@GetMapping("getKeyByCode")
	public ResponseResult<KeyGeneratorResponse> getKeyByCode(@RequestParam("code") String code) {
		ResponseResult<KeyGeneratorResponse> response;
		try {
			response = ResponseResult.success(KeyGeneratorResponse.builder().code(code)
					.key(keyGeneratorService.generatorKeyByCode(code)).build());
		} catch (final Exception e) {
			response = ResponseResult.error(CodeEnum.systemError);
		}
		return response;
	}
}
