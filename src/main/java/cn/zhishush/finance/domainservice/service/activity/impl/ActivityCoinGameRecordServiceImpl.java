package cn.zhishush.finance.domainservice.service.activity.impl;

import javax.annotation.Resource;

import cn.zhishush.finance.domainservice.repository.activity.ActivityCoinGameRepository;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ResponseUtils;
import cn.zhishush.finance.domain.activity.ActivityCoinGame;
import cn.zhishush.finance.domain.activity.ActivityCoinGameParameter;
import cn.zhishush.finance.domain.basic.BasicParameter;
import cn.zhishush.finance.domainservice.service.activity.ActivityCoinGameRecordService;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityCoinGameRecordServiceImpl.java, v0.1 2018/11/26 9:28 AM lili Exp $
 */
@Service("activityCoinGameRecordService")
public class ActivityCoinGameRecordServiceImpl implements ActivityCoinGameRecordService {

	@Resource
	private ActivityCoinGameRepository activityCoinGameRepository;

	/**
	 * @param parameter
	 *            参数
	 * @return BasicResponse
	 */
	@Override
	public BasicResponse process(BasicParameter parameter) {
		ActivityCoinGameParameter activityCoinGameParameter = (ActivityCoinGameParameter) parameter;
		ActivityCoinGame activityCoinGame = ActivityCoinGame.builder()
				.activityCode(activityCoinGameParameter.getActivityCode())
				.userId(activityCoinGameParameter.getUserInfo().getId())
				.gameCode(activityCoinGameParameter.getGameCode()).gameName(activityCoinGameParameter.getGameName())
				.coinNum(activityCoinGameParameter.getCoinNum()).build();
		activityCoinGameRepository.save(activityCoinGame);
		return ResponseUtils.buildResp(ReturnCode.SUCCESS);
	}
}
