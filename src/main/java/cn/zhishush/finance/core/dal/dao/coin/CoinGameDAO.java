package cn.zhishush.finance.core.dal.dao.coin;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameDO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameLogDO;

/**
 * <p>金币游戏</p>
 * 
 * @author lili
 * @version $Id: CoinGameDAO.java, v0.1 2018/11/14 12:26 PM lili Exp $
 */
public interface CoinGameDAO extends BaseDAO<CoinGameDO, Long> {

    /**
     * 查询早起打卡
     * @param taskType 任务类型
     * @param gameType 游戏类型
     * @return CoinGameDO
     */
    CoinGameDO selectByTaskType(@Param("taskType") String taskType,
                                @Param("gameType") Integer gameType);

    /**
     * 查询新手任务
     * @param taskType 任务类型
     * @param gameType 游戏类型
     * @return  List<CoinGameDO>
     */
    List<CoinGameDO> selectNewRegisterTask(@Param("taskType") String taskType,
                                           @Param("gameType") Integer gameType);

    /**
     * 功能描述:活动列表
     * @param taskType 任务类型
     * @param gameType 游戏类型
     * @return  List<CoinGameDO>
     */
    List<CoinGameDO> selectByGameType(@Param("taskType") String taskType,
                                      @Param("gameType") Integer gameType);

    /**
     * 查询用户最新操作日志记录
     * @param userIds 用户id列表
     * @return List<CoinLogDO>
     */
    List<CoinGameLogDO> queryLatestLog(@Param("userIds") List<Long> userIds);
}