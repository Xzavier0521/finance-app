package cn.zhishush.finance.core.common.enums;

import lombok.Getter;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: AgentLevelEnum.java, v0.1 2018/10/11 12:04 PM lili Exp $
 */
@Getter
public enum AgentLevelEnum {

	/**
	 * 一级代理
	 */
	FIRST_LEVEL("1", "一级代理"),

	/**
	 * 一级代理
	 */
	SECOND_LEVEL("2", "一级代理");
	/**
	 * 编码
	 */
	private String code;

	/**
	 * 描述
	 */
	private String desc;

	AgentLevelEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static AgentLevelEnum getByCode(String code) {
		if (StringUtils.isBlank(code)) {
			return null;
		} else {
			AgentLevelEnum[] var1 = values();
			for (AgentLevelEnum value : var1) {
				if (value.code.equals(code)) {
					return value;
				}
			}
			return null;
		}
	}
}
