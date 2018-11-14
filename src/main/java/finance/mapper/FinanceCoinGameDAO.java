package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.model.po.FinanceCoinGame;
import finance.model.po.FinanceCoinGameLog;

/**
 * <p>金币游戏</p>
 * @author lili
 * @version $Id: FinanceCoinGameDAO.java, v0.1 2018/11/14 12:26 PM lili Exp $
 */
public interface FinanceCoinGameDAO extends BaseDAO<FinanceCoinGame, Long> {
    /**
      *功能描述:查询早起打卡
      * @author: moruihai
      * @date: 2018/8/22 16:39
      * @param: [taskType]
      * @return: finance.model.po.FinanceCoinGame
      */
    FinanceCoinGame selectByTaskType(@Param("taskType") String taskType,
                                     @Param("gameType") Integer gameType);

    /**
      *功能描述:查询新手任务
      * @author: moruihai
      * @date: 2018/8/27 16:47
      * @param: [taskType, gameType]
      * @return: java.util.List<finance.model.po.FinanceCoinGame>
      */
    List<FinanceCoinGame> selectNewRegisterTask(@Param("taskType") String taskType,
                                                @Param("gameType") Integer gameType);

    /**
      *功能描述:活动列表
      * @author: moruihai
      * @date: 2018/9/3 14:59
      * @param: [gameType]
      * @return: java.util.List<finance.model.po.FinanceCoinGame>
      */
    List<FinanceCoinGame> selectByGameType(@Param("taskType") String taskType,
                                           @Param("gameType") Integer gameType);

    /**
     * 查询用户最新操作日志记录
     * @param userIds 用户id列表
     * @return List<FinanceCoinLog>
     */
    List<FinanceCoinGameLog> queryLatestLog(@Param("userIds") List<Long> userIds);
}