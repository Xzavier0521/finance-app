package cn.zhishush.finance.core.common.enums;

/**
 * <p>
 * 数值类型
 * </p>
 *
 * @author lili
 * @version 1.0: AmountType.java, v0.1 2018/11/14 2:13 PM PM lili Exp $
 */
public enum AmountType {
	amount(1, "金额值"), percenta(2, "百分比");
	private Integer code;
	private String msg;

	AmountType(Integer code, String msg) {
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