package finance.core.common.enums;

/**
 * <p>
 * 短信使用类型
 * </p>
 *
 * @author lili
 * @version 1.0: SmsUseType.java, v0.1 2018/11/14 2:06 PM PM lili Exp $
 */
public enum SmsUseType {
	login(), // 登录
	changePayPwd(), // 修改账户密码
	simpleRegist(); // 简单注册

	SmsUseType() {
	}

	public static Boolean contains(String smsUseType) {
		for (SmsUseType type : SmsUseType.values()) {
			if (type.name().equals(smsUseType)) {
				return true;
			}
		}
		return false;
	}
}
