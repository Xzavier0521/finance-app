package finance.domainservice.service.validate;

/**
 * 短信验证码服务接口.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午5:35:06 hewenbin
 */
public interface SmsValidateService {

	/**
	 * 缓存短信验证码.
	 * 
	 * @param mobileNum
	 *            not null and ''
	 * @param validateCode
	 *            not null and ''
	 * @return
	 * @author hewenbin
	 * @version SmsValidateService.java, v1.0 2018年7月10日 下午3:01:35 hewenbin
	 */
	Boolean cacheSmsValidateCode(String mobileNum, String validateCode, String useType);

	/**
	 * 校验短信验证码.
	 * 
	 * @param mobileNum
	 *            not null and ''
	 * @param smsCode
	 *            not null and ''
	 * @return
	 * @author hewenbin
	 * @version SmsValidateService.java, v1.0 2018年7月9日 下午8:22:59 hewenbin
	 */
	Boolean validateSmsCode(String mobileNum, String smsCode, String type);

}
