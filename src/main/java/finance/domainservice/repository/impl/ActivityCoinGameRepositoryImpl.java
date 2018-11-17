package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.ActivityCoinGameDAO;
import finance.domain.activity.ActivityCoinGame;
import finance.domainservice.converter.ActivityCoinGameConverter;
import finance.domainservice.repository.ActivityCoinGameRepository;

/**
 * <p>活动-金币游戏</p>
 *
 * @author lili
 * @version 1.0: ActivityCoinGameRepositoryImpl.java, v0.1 2018/11/15 11:34 AM PM lili Exp $
 */
@Slf4j
@Repository("activityCoinGameRepository")
public class ActivityCoinGameRepositoryImpl implements ActivityCoinGameRepository {

    @Resource
    private ActivityCoinGameDAO activityCoinGameDAO;

    /**
     * 保存
     *
     * @param activityCoinGame 记录
     * @return int
     */
    @Override
    public int save(ActivityCoinGame activityCoinGame) {
        return activityCoinGameDAO.insertSelective(ActivityCoinGameConverter.convert(activityCoinGame));
    }

    /**
     * 查询
     *
     * @param userId       用户id
     * @param activityCode 活动代码
     * @param gameCode     游戏代码
     * @return ActivityCoinGame
     */
    @Override
    public ActivityCoinGame queryByCondition(Long userId, String activityCode, String gameCode) {
        ActivityCoinGame activityCoinGame = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("activityCode", activityCode);
        parameters.put("gameCode", gameCode);
        List<ActivityCoinGame> activityCoinGames = ActivityCoinGameConverter
            .convert2List(activityCoinGameDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(activityCoinGames)) {
            activityCoinGame = activityCoinGames.get(0);
        }
        return activityCoinGame;
    }
}
