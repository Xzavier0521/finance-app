package finance.web.controller.noauth.validate;

import finance.api.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.domainservice.service.sms.SmsBiz;
import finance.core.common.enums.CodeEnum;
import finance.api.model.base.XMap;
import finance.domain.dto.SendSmsCodeDto;

/**
 * 短信验证码服务.
 * @author hewenbin
 * @version v1.0 2018年7月4日 上午9:52:05 hewenbin
 */
@RestController
@RequestMapping("validate")
public class SmsValidateApi {
	@Autowired
	private SmsBiz smsBiz;
	
	/**
	 * <pre>
	 * @api {POST} /validate/sendSmsVidateCode 发送短信验证码
	 * @apiName sendSmsVidateCode 
	 * @apiGroup SMSVALIDATE
	 * @apiVersion 0.1.0
	 * @apiDescription 发送短信验证码
	 * @apiParam {String} mobileNum 手机号
	 * @apiParam {String="login","changePayPwd","simpleRegist"} useType 短信类型（注册登录、修改提现密码、简单注册）
	 * @apiParam {String="register_home","finance_home","finance_home_list"} [platformCode=finance_home] 平台编码（注册宝、金融家、金融家集合页）
	 * @apiParam {String{32}} [imgCodeId] 图片验证码ID,usrType=simpleRegist时，不用传递
	 * @apiParam {String{4..6}} [imgCode] 图片验证码,usrType=simpleRegist时，不用传递
	 * @apiParamExample {JSON} Request-example
	 *   {
	 *   	"mobileNum": "13819191919",
	 *   	"useType": "changePayPwd",
	 *   	"platformCode": "finance_home",
	 *   	"imgCodeId": "698d51a19d8a121ce581499d7b701668",
	 *   	"imgCode": "abcd33"
	 *   }
	 * @apiSuccess {Boolean} succeed 是否成功
	 * @apiSuccess {String} errorCode 结果码
	 * @apiSuccess {String} errorMessage 消息说明
	 * @apiSuccess {NULL} data NULL
	 * @apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
     *   "succeed",true,
	 *   "data":null
	 *  }
	 * @apiError 0000000 成功
	 * @apiError 9999999 网络返回错误
	 * @apiError 0103001 参数不合法
	 * @apiError 0103002 图片验证码不正确
	 * @apiError 0103003 验证码发送失败，请重试
	 * </pre>
	 * @author hewenbin
	 * @version SmsValidateApi.java, v1.0 2018年7月4日 上午9:55:05 hewenbin
	 */
	@PostMapping("sendSmsVidateCode")
	public ResponseResult<String> sendSmsVidateCode(@RequestBody XMap xmap){
		SendSmsCodeDto paramDto = xmap.toBean(SendSmsCodeDto.class);
		if (paramDto == null || !paramDto.validateParam()) {
			return ResponseResult.error(CodeEnum.smsValidateInvalid);
		}
		ResponseResult<String> res = smsBiz.sendSmsValidateCode(paramDto);
		return res;
	}
}
