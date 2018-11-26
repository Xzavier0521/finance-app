package finance.ext.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 获取用户基本信息
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinUserInfoQueryRequest.java, v0.1 2018/10/30 12:08 AM lili
 *          Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinUserInfoQueryRequest extends WeiXinBasicRequest {

	private static final long serialVersionUID = 5895430495024856280L;
	/**
	 * 用户的标识，对当前公众号唯一
	 */
	private String openid;
	/**
	 * 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
	 */
	private String lang;
}
