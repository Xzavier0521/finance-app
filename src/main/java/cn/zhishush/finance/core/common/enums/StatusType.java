package cn.zhishush.finance.core.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: StatusType.java, v0.1 2018/11/13 2:31 PM lili Exp $
 */
public enum StatusType {

	/**
	 * 否
	 */
	unGiving(0, "否"),
	/**
	 * 是
	 */
	giving(1, "是");

	private static final Map<Integer, StatusType> param = new HashMap();

	private Integer code;
	private String msg;

	StatusType(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static Map<Integer, StatusType> getParam() {
		return param;
	}

	public Integer getCode() {
		return this.code;
	}

	public String getMsg() {
		return this.msg;
	}
}
