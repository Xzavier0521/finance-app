package cn.zhishush.finance.core.common.enums;

/**
 * <p>
 * 任务完成类型
 * </p>
 *
 * @author lili
 * @version 1.0: TaskStatus.java, v0.1 2018/11/14 2:09 PM PM lili Exp $
 */
public enum TaskStatus {
	unfinish(0, "未完成"), finishNotReceive(1, "已完成待领取"), receive(2, "已领取");
	private Integer code;
	private String msg;

	TaskStatus(Integer code, String msg) {
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
