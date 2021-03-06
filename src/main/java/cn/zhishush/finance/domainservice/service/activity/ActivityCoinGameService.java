package cn.zhishush.finance.domainservice.service.activity;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>支付金币玩游戏</p>
 * @author lili
 * @version 1.0: ActivityCoinGameService.java, v0.1 2018/11/26 9:31 AM lili Exp $
 */
public interface ActivityCoinGameService {

	/**
	 * 保存支付金币玩游戏记录
	 * 
	 * @param userInfo
	 *            用户信息
	 * @param activityCode
	 *            活动代码
	 * @param gameCode
	 *            游戏代码
	 * @param coinNum
	 *            金币数
	 * @return BasicResponse
	 */
	BasicResponse localData(UserInfo userInfo, String activityCode, String gameCode, Long coinNum);

}
