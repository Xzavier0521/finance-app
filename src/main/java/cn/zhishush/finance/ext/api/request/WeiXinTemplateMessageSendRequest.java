package cn.zhishush.finance.ext.api.request;

import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import cn.zhishush.finance.ext.api.model.MiniProgram;
import cn.zhishush.finance.ext.api.model.WeiXinTemplateData;

/**
 * <p>
 * 微信模版消息发送请求参数
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTemplateMessageSendRequest.java, v0.1 2018/10/21 7:36 PM
 *          lili Exp $
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WeiXinTemplateMessageSendRequest extends WeiXinBasicRequest {
	private static final long serialVersionUID = 1883419803142932227L;
	/**
	 * 模板
	 */
	private String template_id;
	/**
	 * 接收者openid
	 */
	private String touser;

	/**
	 * 用户点击模板信息的跳转页面
	 */
	private String url;

	/**
	 * 跳小程序所需数据，不需跳小程序可不用传该数据
	 */
	private MiniProgram miniProgram;
	/**
	 * 模板里的数据
	 */
	private Map<String, WeiXinTemplateData> data;

}
