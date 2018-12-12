package cn.zhishush.finance.ext.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 长链接转短链接
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinLongUrlToShortRequest.java, v0.1 2018/10/29 10:11 PM lili
 *          Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinLongUrlToShortRequest extends WeiXinBasicRequest {

	private static final long serialVersionUID = 4238184928028335955L;
	/**
	 * 此处填long2short，代表长链接转短链接
	 */
	private String action;

	/**
	 * 需要转换的长链接，支持http://、https://、weixin://wxpay 格式的url
	 */
	private String long_url;
}
