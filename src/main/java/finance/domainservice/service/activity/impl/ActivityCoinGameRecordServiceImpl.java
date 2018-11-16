package finance.domainservice.service.activity.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.ResponseUtils;
import finance.domain.activity.ActivityCoinGame;
import finance.domain.activity.ActivityCoinGameParameter;
import finance.domain.basic.BasicParameter;
import finance.domainservice.repository.ActivityCoinGameRepository;
import finance.domainservice.service.activity.ActivityCoinGameRecordService;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameRecordServiceImpl.java, v0.1 2018/11/15 11:14 PM PM lili Exp $
 */
@Service("activityCoinGameRecordService")
public class ActivityCoinGameRecordServiceImpl implements ActivityCoinGameRecordService {

    @Resource
    private ActivityCoinGameRepository activityCoinGameRepository;

    /**
     * @param parameter 参数
     * @return BasicResponse
     */
    @Override
    public BasicResponse process(BasicParameter parameter) {
        ActivityCoinGameParameter activityCoinGameParameter = (ActivityCoinGameParameter) parameter;
        ActivityCoinGame activityCoinGame = ActivityCoinGame.builder()
            .activityCode(activityCoinGameParameter.getActivityCode())
            .userId(activityCoinGameParameter.getUserInfo().getId())
            .gameCode(activityCoinGameParameter.getGameCode())
            .gameName(activityCoinGameParameter.getGameName())
            .coinNum(activityCoinGameParameter.getCoinNum()).build();
        activityCoinGameRepository.save(activityCoinGame);
        return ResponseUtils.buildResp(ReturnCode.SUCCESS);
    }
}
