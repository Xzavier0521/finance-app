package finance.web.controller.noauth.validate;

import java.util.Map;

import javax.annotation.Resource;

import finance.api.model.response.ResponseResult;
import finance.core.common.constants.Constant;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import finance.core.common.enums.CodeEnum;
import finance.api.model.vo.login.ImgValidateVo;
import finance.domainservice.service.validate.ImgValidateService;

/**
 * 短信验证码服务.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月4日 上午9:52:05 hewenbin
 */
@Slf4j
@RestController
@RequestMapping("validate")
public class ImgValidateApi {

	@Resource
	private ImgValidateService imgValidateService;

	/**
	 * <pre>
	 * &#64;api {GET} /validate/validateImgCode 获取图片验证码
	 * &#64;apiName getVidateImgCode
	 * &#64;apiGroup IMGVALIDATE
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 获取6位图片验证码图片的base64编码
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} data 图片验证码数据
	 * &#64;apiSuccess {String} data.imgCodeId 图片验证码ID
	 * &#64;apiSuccess {String} data.imgCodeBase64 图片验证码文件的base64编码
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":{
	 *   	"imgCodeId":"9c225376631d467d8419e268981254f8",
	 *   	"imgCodeBase64":"data:image/png;base64,xxxxxxxxx"
	 *   }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author hewenbin
	 * @version SmsValidateApi.java, v1.0 2018年7月4日 上午9:55:05 hewenbin
	 */
	@GetMapping("vidateImgCode")
	public ResponseResult<ImgValidateVo> getVidateImgCode() {
		ImgValidateVo imgVo = imgValidateService.getImgValidate();
		return ResponseResult.success(imgVo);
	}

	/**
	 * <pre>
	 * &#64;api {POST} /validate/validateImgCode 校验图片验证码
	 * &#64;apiName validateImgCode
	 * &#64;apiGroup IMGVALIDATE
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 校验图片验证码是否正确
	 * &#64;apiParam {String{32}} imgCodeId 图片验证码ID
	 * &#64;apiParam {String{4..6}} imgCode 图片验证码
	 * &#64;apiParamExample {JSON} Request-example
	 *   {
	 *   	"imgCodeId": "9c225376631d467d8419e268981254f8",
	 *   	"imgCode": "abcdef"
	 *   }
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {NULL} data null
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":null
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0102001 参数不合法
	 * &#64;apiError 0102002 图片验证码不正确
	 * </pre>
	 * 
	 * @author hewenbin
	 * @version ImgValidateApi.java, v1.0 2018年7月6日 下午2:16:18 hewenbin
	 */
	@PostMapping("vidateImgCode")
	public ResponseResult<String> vidateImgCode(@RequestBody Map<String, String> param) {
		String imgCodeId = param.get("imgCodeId");
		String imgCode = param.get("imgCode");
		if (StringUtils.isEmpty(imgCodeId) || StringUtils.isEmpty(imgCode) || imgCodeId.length() != 32
				|| imgCode.length() != Constant.imgcode_length) {
			return ResponseResult.error(CodeEnum.imgValidateInvalid);
		}

		Boolean res = imgValidateService.validateImgCode(imgCodeId, imgCode);
		if (!res) {
			return ResponseResult.error(CodeEnum.imgValidateFail);
		}
		return ResponseResult.success(null);
	}

}
