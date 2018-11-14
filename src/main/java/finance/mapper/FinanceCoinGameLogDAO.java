package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.model.po.FinanceCoinGameLog;
import finance.api.model.vo.JoinTaskBaseVO;
import finance.api.model.vo.MyRecordVO;

/**
 * <p>金币游戏日志</p>
 * @author lili
 * @version $Id: FinanceCoinGameLogDAO.java, v0.1 2018/11/14 12:27 PM lili Exp $
 */
public interface FinanceCoinGameLogDAO extends BaseDAO<FinanceCoinGameLog, Long> {
    /**
      *功能描述:根据当前时间查询参与游戏记录
      * @author: moruihai
      * @date: 2018/8/21 17:13
      * @param: [searchDate]
      * @return: finance.model.po.FinanceCoinGameLog
      */
    FinanceCoinGameLog selectByCurrentDate(@Param("isYesJoinDate") String isYesJoinDate,
                                           @Param("isJoinDate") String isJoinDate,
                                           @Param("isClockDate") String isClockDate,
                                           @Param("userId") Long userId);

    /**
      *功能描述:总参加打卡人数（报名参加明天的打卡） totalCoinNum 总金币数
      * @author: moruihai
      * @date: 2018/8/22 9:35
      * @param: []
      * @return: finance.model.vo.JoinTaskBaseVO
      */
    JoinTaskBaseVO selectAllDataByCurrentDay();

    List<FinanceCoinGameLog> selectByCondition(@Param("signDate") String signDate,
                                               @Param("inNum") String inNum,
                                               @Param("clockCount") String clockCount);

    /**
      *功能描述:昨日参与活动列表
      * @author: moruihai
      * @date: 2018/8/22 10:18
      * @param: []
      * @return: java.util.List<finance.model.po.FinanceCoinGameLog>
      */
    List<FinanceCoinGameLog> selectByYesterdayDate();

    /**
      *功能描述:我的战绩--汇总数据
      * @author: moruihai
      * @date: 2018/8/22 14:42
      * @param: [userId]
      * @return: finance.model.vo.MyRecordVO
      */
    MyRecordVO selectMyRecordTotalData(@Param("userId") Long userId);

    /**
      *功能描述:昨天打卡信息
      * @author: moruihai
      * @date: 2018/8/23 10:49
      * @param: [userId]
      * @return: finance.model.po.FinanceCoinGameLog
      */
    FinanceCoinGameLog selectByUserIdAndYesterdayDate(@Param("userId") Long userId);

    /**
      *功能描述:更新打卡时间
      * @author: moruihai
      * @date: 2018/8/23 10:49
      * @param: [financeCoinGameLog]
      * @return: java.lang.Long
      */
    Long updateByUserIdAndDateSelective(FinanceCoinGameLog financeCoinGameLog);

    /**
     *@author ：tangwei
     * @Title: selectSignByFirst
     * @Description: 查询当前用户是否第一次打卡
     * @param userId
     * @return
     * @return: Integer  
     */
    Integer selectSignByFirst(@Param("userId") Long userId);

}