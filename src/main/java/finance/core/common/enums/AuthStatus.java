package finance.core.common.enums;

/**
 * <p>
 * 身份证信息认证状态
 * </p>
 *
 * @author lili
 * @version 1.0: AuthStatus.java, v0.1 2018/11/14 2:11 PM PM lili Exp $
 */
public enum AuthStatus {
	not_save(0, "未完善"), is_auth(1, "已认证");
	private int code;
	private String msg;

	AuthStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
