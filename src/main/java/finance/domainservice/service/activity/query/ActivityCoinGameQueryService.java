package finance.domainservice.service.activity.query;

import java.util.List;

import finance.domain.activity.CoinGame;
import finance.domain.user.UserInfo;

/**
 * <p>金币游戏</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameQueryService.java, v0.1 2018/11/15 9:02 PM PM lili Exp $
 */
public interface ActivityCoinGameQueryService {

    /**
     *  查询用户是否支付金币
     * @param userInfo 用户信息
     * @param activityCode 活动代码
     * @param gameCodes 游戏代码
     * @return List<CoinGame>
     */
    List<CoinGame> queryCoinGameList(UserInfo userInfo, String activityCode, List<String> gameCodes);

}
