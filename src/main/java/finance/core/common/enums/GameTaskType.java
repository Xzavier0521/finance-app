package finance.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: finance-server
 *
 * @description: 游戏任务分类
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-27 13:46
 **/
public enum GameTaskType {
	everydaySign("1", "每日签到"), newRegisterTask("2", "新手任务"), groupTask("3", "成长任务"),

	withdrawalTask("withdrawalTask", "提现"), perfectInfoTask("perfectInfoTask", "完善资料"), invitePerson("invitePerson",
			"邀请有礼"), exchangeFee("exchangeFee", "兑换手续费"); // 提现时，使用金币兑换手续费

	private String code;
	private String msg;
	GameTaskType(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	private static final Map<String, GameTaskType> param = new HashMap<String, GameTaskType>();

	static {
		for (GameTaskType gameTaskType : GameTaskType.values()) {
			param.put(gameTaskType.getCode(), gameTaskType);
		}
	}
	public static Map<String, GameTaskType> getParam() {
		return param;
	}

}
