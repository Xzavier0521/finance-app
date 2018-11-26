package finance.domainservice.service.sms;

import finance.api.model.response.ResponseResult;

/**
 * <p>
 * 短信服务
 * </p>
 * 
 * @author lili
 * @version $Id: SmsService.java, v0.1 2018/11/24 8:41 PM lili Exp $
 */
public interface SmsService {

	/**
	 * 发送短信
	 * 
	 * @param mobile
	 *            手机号码
	 * @param message
	 *            短信内容，短信签名请调用者自己添加
	 * @return ResponseResult<String>
	 */
	ResponseResult<String> sendMsg(String mobile, String message);

}
