package cn.zhishush.finance.core.common.enums;

/**
 * <p>
 * 微信公众号关注状态
 * </p>
 * 
 * @author lili
 * @version $Id: ConcernStatusEnum.java, v0.1 2018/11/7 4:03 PM lili Exp $
 */
public enum ConcernStatusEnum {

	/**
	 * 关注
	 */
	SUBSCRIBE("1", "关注"),
	/**
	 * 取消关注
	 */
	UNSUBSCRIBE("0", "取消关注");

	private String code;

	private String desc;

	ConcernStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
