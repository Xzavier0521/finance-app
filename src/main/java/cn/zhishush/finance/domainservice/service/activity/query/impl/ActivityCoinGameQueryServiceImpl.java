package cn.zhishush.finance.domainservice.service.activity.query.impl;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import cn.zhishush.finance.domain.activity.ActivityCoinGame;
import cn.zhishush.finance.domain.activity.CoinGame;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.repository.activity.ActivityCoinGameRepository;
import cn.zhishush.finance.domainservice.service.activity.query.ActivityCoinGameQueryService;

/**
 * <p>金币游戏</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameQueryServiceImpl.java, v0.1 2018/11/15 9:05 PM lili Exp $
 */
@Service("activityCoinGameQueryService")
public class ActivityCoinGameQueryServiceImpl implements ActivityCoinGameQueryService {

    @Resource
    private ActivityCoinGameRepository activityCoinGameRepository;

    @Override
    public List<CoinGame> queryCoinGameList(UserInfo userInfo, String activityCode,
                                            List<String> gameCodes) {
        List<CoinGame> coinGames = Lists.newArrayListWithCapacity(gameCodes.size());
        ActivityCoinGame activityCoinGame;
        CoinGame coinGame;
        for (String gameCode : gameCodes) {
            coinGame = new CoinGame();
            coinGame.setGameCode(gameCode);
            activityCoinGame = activityCoinGameRepository.queryByCondition(userInfo.getId(),
                activityCode, gameCode);
            if (Objects.nonNull(activityCoinGame)) {
                coinGame.setGameName(activityCoinGame.getGameName());
                coinGame.setIsPayCoin(true);
            } else {
                coinGame.setIsPayCoin(false);
            }
            coinGames.add(coinGame);

        }
        return coinGames;
    }
}
