package cn.zhishush.finance.core.dal.dao.coin;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.coin.PushRewardVO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinLogDO;

/**
 * <p>金币日志</p>
 * 
 * @author lili
 * @version $Id: CoinLogDAO.java, v0.1 2018/11/14 2:53 PM lili Exp $
 */
// @CacheConfig(cacheNames = "coin")
public interface CoinLogDAO extends BaseDAO<CoinLogDO, Long> {

    /*
     * @Caching(evict = { @CacheEvict(key = "'coinNum_'.concat(#a0.userId)"),
     * 
     * @CacheEvict(key = "'totalGetCoinNum_'.concat(#a0.userId)") })
     */
    int insertSelective(CoinLogDO record);

    /**
     * 查询我的战绩明细
     * @param userId
     * @param taskId
     * @param financeCoinLogPage
     * @return
     */
    List<CoinLogDO> selectByUserId(@Param("userId") Long userId, @Param("taskId") Long taskId,
                                   @Param("page") Page<CoinLogDO> financeCoinLogPage);

    /**
     * 查询用户金币数
     * @param userId
     * @return
     */
    Integer selectCoinNumByUserId(@Param("userId") Long userId);

    /**
     * 查询用户签到记录
     * @param userId
     * @param taskId
     * @return
     */
    List<CoinLogDO> selectByUserIdAndTaskId(@Param("userId") Long userId,
                                            @Param("taskId") Long taskId);

    /**
     * 查询用户昨天签到信息
     * @param userId
     * @param taskId
     * @param yesterdayType
     * @param todayType
     * @return
     */
    CoinLogDO selectYesAndToDatClockByUserId(@Param("userId") Long userId,
                                             @Param("taskId") Long taskId,
                                             @Param("yesterdayType") String yesterdayType,
                                             @Param("todayType") String todayType);

    /**
     * 用户所有获得金币数
     * @param userId
     * @return
     */
    Integer selectTotalGetCoinNumByUserId(@Param("userId") Long userId);

    /**
     * 查询金币获取或者消费信息
     * @param userId
     * @param paramType
     * @param financeCoinLogPage
     * @return
     */
    List<CoinLogDO> queryUserCoinRecordsByType(@Param("userId") Long userId,
                                               @Param("paramType") String paramType,
                                               @Param("page") Page<CoinLogDO> financeCoinLogPage);

    /**
     * 查询用户最新操作日志记录
     * @param userIds 用户id列表
     * @return List<CoinLogDO>
     */
    List<CoinLogDO> queryLatestLog(@Param("userIds") List<Long> userIds);

    /**
     * 查询当前用户今日所得打卡奖励
     * @param userId
     * @param earlySign
     * @param integer
     * @return
     */
    PushRewardVO selectSignCoinRewardByUid(@Param("userId") Long userId,
                                           @Param("taskType") String earlySign,
                                           @Param("gameType") Integer integer);
}