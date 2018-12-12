package cn.zhishush.finance.domainservice.service.wechat;

import java.util.Map;

import cn.zhishush.finance.domain.user.ThirdAccountInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.WeiXinMessageTemplate;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: WeiXinTemplateMessageSendService.java, v0.1 2018/11/26 7:00 PM lili Exp $
 */
public interface WeiXinTemplateMessageSendService {

	/**
	 * 发送微信消息
	 * 
	 * @param userInfo
	 *            新注册用户的基本信息
	 * @param thirdAccountInfo
	 *            新注册用户的父级用户的绑定信息
	 * @param weiXinMessageTemplate
	 *            模版信息
	 * @param parameters
	 *            参数
	 */
	void send(UserInfo userInfo, ThirdAccountInfo thirdAccountInfo, WeiXinMessageTemplate weiXinMessageTemplate,
			Map<String, String> parameters);

}
