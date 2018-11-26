package finance.ext.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 注释
 * </p >
 *
 * @author liwei
 * @version $Id: KaMengUserApplyCreditCardRequest.java, v0.1 2018/11/19 下午6:09
 *          PM user Exp $
 */
@Data
public class KaMengUserApplyCreditCardRequest implements Serializable {
	/**
	 * 办卡人姓名
	 */
	private String name;
	/**
	 * 电话号码
	 */
	private String phone;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * IP地址(ipv4地址)
	 */
	private String ip;
	/**
	 * 系统名
	 */
	private String systemVersion;
	/**
	 * 浏览器版本
	 */
	private String softVersion;
	/**
	 * 渠道商编号
	 */
	private Long fatherId;
	/**
	 * 产品编号
	 */
	private Long goodsId;
	/**
	 * 产品类型
	 */
	private Long type;
	/**
	 * 代理商编号
	 */
	private Long otherUserId;
}
