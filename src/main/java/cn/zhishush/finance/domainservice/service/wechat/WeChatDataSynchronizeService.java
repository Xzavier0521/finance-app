package cn.zhishush.finance.domainservice.service.wechat;

import cn.zhishush.finance.core.common.enums.OperatorTypeEnum;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: WeChatDataSynchronizeService.java, v0.1 2018/11/26 6:58 PM lili Exp $
 */
public interface WeChatDataSynchronizeService {

	/**
	 * 执行
	 */
	void process();

	/**
	 * 更新微信公众号关注用户信息
	 * 
	 * @param openId
	 *            微信open_id
	 * @param operatorType
	 *            操作类型
	 */
	void process(String openId, OperatorTypeEnum operatorType);
}
