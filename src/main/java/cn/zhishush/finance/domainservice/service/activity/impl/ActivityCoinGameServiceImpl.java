package cn.zhishush.finance.domainservice.service.activity.impl;

import javax.annotation.Resource;

import com.google.common.collect.Lists;
import cn.zhishush.finance.domain.activity.ActivityCoinGameParameter;
import cn.zhishush.finance.domainservice.service.activity.ActivityCoinGameRecordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.domain.coin.PayCoinCondition;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.service.activity.ActivityCoinGameService;
import cn.zhishush.finance.domainservice.service.lock.DistributedLockService;

/**
 * <p>支付金币玩游戏</p>
 * @author lili
 * @version $Id: ActivityCoinGameServiceImpl.java, v0.1 2018/11/26 9:29 AM lili Exp $
 */
@Service("activityCoinGameService")
public class ActivityCoinGameServiceImpl implements ActivityCoinGameService {
	@Value("${user.coin.key.prefix}")
	private String userCoinKeyPrefix;
	@Resource
	private ActivityCoinGameRecordService activityCoinGameRecordService;
	@Resource
	private DistributedLockService distributedLockService;

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
	@Override
	public BasicResponse localData(UserInfo userInfo, String activityCode, String gameCode, Long coinNum) {

		ActivityCoinGameParameter parameter = new ActivityCoinGameParameter();
		parameter.setActivityCode(activityCode);
		parameter.setCoinNum(coinNum);
		parameter.setGameCode(gameCode);
		parameter.setUserInfo(userInfo);
		PayCoinCondition payCoinCondition = PayCoinCondition.builder().userId(userInfo.getId())
				.coinNum(coinNum.intValue()).keyPrefix(userCoinKeyPrefix).payReason("支付金币玩游戏").parameter(parameter)
				.functions(Lists.newArrayList(activityCoinGameRecordService::process)).build();
		return distributedLockService.process(payCoinCondition);
	}
}
