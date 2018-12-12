package cn.zhishush.finance.ext.api.request;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinPrivateTemplateDelRequest.java, v0.1 2018/10/21 10:29 PM
 *          lili Exp $
 */
@Data
public class WeiXinPrivateTemplateDelRequest implements Serializable {

	/**
	 * 公众帐号下模板消息ID
	 */
	private String template_id;
}
