package finance.core.common.enums;

/**
 * <p>
 * 活动完成状态
 * </p>
 *
 * @author lili
 * @version 1.0: ActivityFinishStatus.java, v0.1 2018/11/14 2:12 PM PM lili Exp
 *          $
 */
public enum ActivityFinishStatus {
	firstJoin(1, "首次参加"), unfinish(2, "未完成"), finish(3, "已完成");
	private Integer code;
	private String msg;

	ActivityFinishStatus(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}