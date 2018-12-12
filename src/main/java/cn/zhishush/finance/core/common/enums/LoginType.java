package cn.zhishush.finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 登陆类型
 * </p>
 *
 * @author lili
 * @version 1.0: LoginType.java, v0.1 2018/11/13 2:47 PM PM lili Exp $
 */
public enum LoginType {

	/**
	 * img_mobile
	 */
	IMG_MOBILE("img_mobile", "img_mobile"),
	/**
	 * 
	 */
	MOBILE("mobile", "mobile"),
	/**
	 * 微信
	 */
	WE_CHAT("wechat", "微信"),
	/**
	 * qq
	 */
	QQ("qq", "qq");

	private String code;
	private String msg;

	LoginType(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Boolean contains(String loginType) {
		LoginType[] var1 = values();
		int var2 = var1.length;

		for (LoginType type : var1) {
			if (type.name().equals(loginType)) {
				return true;
			}
		}
		return false;
	}

	public static LoginType getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		}
		for (LoginType temp : LoginType.values()) {
			if (temp.getCode().equals(code)) {
				return temp;
			}
		}
		return null;
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}
}