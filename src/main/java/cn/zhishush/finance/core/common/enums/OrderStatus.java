package cn.zhishush.finance.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单状态
 */
public enum OrderStatus {
	/*
	 * 系统默认code，不允许修改，可以添加，需要和团队成员沟通好 在默认 code 不够用的情况下，可以添加具体业务code，注意：不建议添加业务code
	 */

	init("00", "提现中"), auditSuccess("01", "审核通过"), auditFail("02", "审核拒绝"), withDrawSuccess("03", "提现成功"), withDrawFail(
			"04", "提现失败"), rechargeFail("05",
					"充值失败"), rechargeSuccess("06", "充值成功"), canWithDraw("07", "可提现"), withDrawDealing("08", "提现任务处理中");

	// 业务code
	private static final Map<String, String> param = new HashMap<String, String>();

	static {
		for (OrderStatus orderstatus : OrderStatus.values()) {
			param.put(orderstatus.getCode(), orderstatus.getMsg());
		}
	}

	private String code;
	private String msg;

	OrderStatus(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 是否已审核.
	 *
	 * @param code
	 * @return
	 * @author hewenbin
	 * @version OrderStatus.java, v1.0 2018年8月28日 下午1:45:18 hewenbin
	 */
	public static Boolean isAudit(String code) {
		if (auditSuccess.getCode().equals(code) || auditFail.getCode().equals(code)
				|| withDrawSuccess.getCode().equals(code) || withDrawFail.getCode().equals(code)) {
			return true;
		}
		return false;
	}

	public static Map<String, String> getParam() {
		return param;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "CodeEnum{" + "code='" + code + '\'' + ", msg='" + msg + '\'' + '}';
	}
}
