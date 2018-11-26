package finance.core.common.enums;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version $Id: OperatorTypeEnum.java, v0.1 2018/10/23 10:36 PM lili Exp $
 */
public enum OperatorTypeEnum {

	/**
	 * 增加
	 */
	ADD("add", "增加"),
	/**
	 * 删除
	 */
	DEL("delete", "删除");
	private String code;

	private String desc;

	OperatorTypeEnum(String code, String desc) {
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
