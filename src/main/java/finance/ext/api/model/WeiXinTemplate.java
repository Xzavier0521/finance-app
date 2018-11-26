package finance.ext.api.model;

import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 微信消息模版列表
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTemplateList.java, v0.1 2018/10/21 7:27 PM lili Exp $
 */
@Data
public class WeiXinTemplate implements Serializable {

	private static final long serialVersionUID = -7337341900614561501L;
	/**
	 * 模板ID
	 */
	private String template_id;
	/**
	 * 模板标题
	 */
	private String title;
	/**
	 * 模板所属行业的一级行业
	 */
	private String primary_industry;
	/**
	 * 模板所属行业的二级行业
	 */
	private String deputy_industry;
	/**
	 * 模板内容
	 */
	private String content;
	/**
	 * 模板示例
	 */
	private String example;
}
