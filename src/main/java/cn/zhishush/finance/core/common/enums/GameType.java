package cn.zhishush.finance.core.common.enums;

/**
 * <p>游戏类型</p>
 *
 * @author lili
 * @version 1.0: GameType.java, v0.1 2018/11/14 2:08 PM PM lili Exp $
 */
public enum GameType {
	task(1, "任务"), activity(2, "活动");
	private Integer code;
	private String msg;

	GameType(Integer code, String msg) {
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
