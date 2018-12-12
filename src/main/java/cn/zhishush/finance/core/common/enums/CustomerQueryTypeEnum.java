package cn.zhishush.finance.core.common.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author lili
 */
public enum CustomerQueryTypeEnum {

	/**
	 * 所有客户
	 */
	ALl("0", "所有客户"),
	/**
	 * 一级客户
	 */
	FIRST_LEVEL("1", "一级客户"),
	/**
	 * 一级客户
	 */
	SECOND_LEVEL("2", "二级客户");
	private String code;
	private String desc;

	CustomerQueryTypeEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	public static CustomerQueryTypeEnum getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		} else {
			CustomerQueryTypeEnum[] var1 = values();
			for (CustomerQueryTypeEnum value : var1) {
				if (value.code.equals(code)) {
					return value;
				}
			}
			return null;
		}
	}
}
