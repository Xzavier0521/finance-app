package cn.zhishush.finance.ext.integration.weixin;

import cn.zhishush.finance.ext.api.request.WeiXinQueryTemplateIdRequest;
import cn.zhishush.finance.ext.api.request.WeiXinTemplateMessageSendRequest;
import cn.zhishush.finance.ext.api.response.QueryTemplateIdResponse;
import cn.zhishush.finance.ext.api.response.WeiXinTemplateMessageSendResponse;

/**
 * <p>
 * 微信消息模版
 * </p>
 * 
 * @author lili
 * @version $Id: WeiXinTemplateMessageClient.java, v0.1 2018/10/21 7:08 PM lili
 *          Exp $
 */
public interface WeiXinTemplateMessageClient {

	/**
	 * 获取模版id
	 * 
	 * @param request
	 *            请求参数
	 * @return QueryTemplateIdResponse
	 */
	QueryTemplateIdResponse getTemplateId(WeiXinQueryTemplateIdRequest request);

	/**
	 * 发送模版消息
	 * 
	 * @param request
	 *            请求参数
	 * @return WeiXinTemplateMessageSendResponse
	 */
	WeiXinTemplateMessageSendResponse sendMessage(WeiXinTemplateMessageSendRequest request);
}
