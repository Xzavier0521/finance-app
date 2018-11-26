package finance.ext.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 微信公众号模版数据
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTemplateData.java, v0.1 2018/10/21 6:40 PM lili Exp $
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeiXinTemplateData {

	/**
	 * 模板显示值
	 */
	private String value;
	/**
	 * 模板内容字体颜色，不填默认为黑色
	 */
	private String color;

}