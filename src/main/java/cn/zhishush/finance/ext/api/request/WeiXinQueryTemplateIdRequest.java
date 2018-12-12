package cn.zhishush.finance.ext.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinQueryTemplateIdRequest.java, v0.1 2018/10/21 7:58 PM lili
 *          Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinQueryTemplateIdRequest extends WeiXinBasicRequest {

	/**
	 * 模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
	 */
	private String template_id_short;
}
