package finance.core.dal.dao;

import finance.api.model.base.Page;
import finance.api.model.vo.coin.PushRewardVO;
import finance.core.dal.dataobject.CoinLogDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 注释
 * </p>
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
     * 功能描述:查询我的战绩明细
     * 
     * @author: moruihai
     * @date: 2018/8/22 16:46
     * @param: [userId,
     *             taskId]
     * @return: java.util.List<finance.core.dal.dataobject.CoinLogDO>
     */
    List<CoinLogDO> selectByUserId(@Param("userId") Long userId, @Param("taskId") Long taskId,
                                   @Param("page") Page<CoinLogDO> financeCoinLogPage);

    /**
     * 功能描述:查询用户金币数
     * 
     * @author: moruihai
     * @date: 2018/8/22 17:46
     * @param: []
     * @return: java.lang.Integer
     */
    // @Cacheable(key = "'coinNum_'.concat(#a0)", unless = "#result == null")
    Integer selectCoinNumByUserId(@Param("userId") Long userId);

    /**
     * 功能描述:查询用户签到记录
     * 
     * @author: moruihai
     * @date: 2018/8/26 17:05
     * @param: [userId,
     *             taskId]
     * @return: java.util.List<finance.core.dal.dataobject.CoinLogDO>
     */
    List<CoinLogDO> selectByUserIdAndTaskId(@Param("userId") Long userId,
                                            @Param("taskId") Long taskId);

    /**
     * 功能描述:查询用户昨天签到信息
     * 
     * @author: moruihai
     * @date: 2018/8/27 10:13
     * @param: [userId,
     *             taskId]
     * @return: finance.core.dal.dataobject.CoinLogDO
     */
    CoinLogDO selectYesAndToDatClockByUserId(@Param("userId") Long userId,
                                             @Param("taskId") Long taskId,
                                             @Param("yesterdayType") String yesterdayType,
                                             @Param("todayType") String todayType);

    /**
     * 功能描述:用户所有获得金币数
     * 
     * @author: moruihai
     * @date: 2018/8/30 14:35
     * @param: [userId]
     * @return: java.lang.Integer
     */
    // @Cacheable(key = "'totalGetCoinNum_'.concat(#a0)", unless = "#result ==
    // null")
    Integer selectTotalGetCoinNumByUserId(@Param("userId") Long userId);

    /**
     * 功能描述:查询金币获取或者消费信息
     * 
     * @author: moruihai
     * @date: 2018/8/30 16:48
     * @param: [paramType]
     *             参数只会是"in"或者"out"
     * @return: java.util.List<finance.core.dal.dataobject.CoinLogDO>
     */
    List<CoinLogDO> queryUserCoinRecordsByType(@Param("userId") Long userId,
                                               @Param("paramType") String paramType,
                                               @Param("page") Page<CoinLogDO> financeCoinLogPage);

    /**
     * 查询用户最新操作日志记录
     * 
     * @param userIds
     *            用户id列表
     * @return List<CoinLogDO>
     */
    List<CoinLogDO> queryLatestLog(@Param("userIds") List<Long> userIds);

    /**
     * @author ：tangwei
     * @Title: selectSignCoinRewardByUid
     * @Description:查询当前用户今日所得打卡奖励
     * @param userId
     * @param integer
     * @param earlySign
     * @return
     * @return: PushRewardVO
     */
    PushRewardVO selectSignCoinRewardByUid(@Param("userId") Long userId,
                                           @Param("taskType") String earlySign,
                                           @Param("gameType") Integer integer);
}