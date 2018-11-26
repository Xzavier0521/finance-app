package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.CoinGameLogDO;
import org.apache.ibatis.annotations.Param;

import finance.api.model.vo.coin.JoinTaskBaseVO;
import finance.api.model.vo.coin.MyRecordVO;

/**
 * <p>
 * 金币游戏日志
 * </p>
 * 
 * @author lili
 * @version $Id: CoinGameLogDAO.java, v0.1 2018/11/14 12:27 PM lili Exp $
 */
public interface CoinGameLogDAO extends BaseDAO<CoinGameLogDO, Long> {
    /**
     * 功能描述:根据当前时间查询参与游戏记录
     * 
     * @author: moruihai
     * @date: 2018/8/21 17:13
     * @param: [searchDate]
     * @return: finance.core.dal.dataobject.CoinGameLogDO
     */
    CoinGameLogDO selectByCurrentDate(@Param("isYesJoinDate") String isYesJoinDate,
                                      @Param("isJoinDate") String isJoinDate,
                                      @Param("isClockDate") String isClockDate,
                                      @Param("userId") Long userId);

    /**
     * 功能描述:总参加打卡人数（报名参加明天的打卡） totalCoinNum 总金币数
     * 
     * @author: moruihai
     * @date: 2018/8/22 9:35
     * @param: []
     * @return: finance.model.vo.JoinTaskBaseVO
     */
    JoinTaskBaseVO selectAllDataByCurrentDay();

    List<CoinGameLogDO> selectByCondition(@Param("signDate") String signDate,
                                          @Param("inNum") String inNum,
                                          @Param("clockCount") String clockCount);

    /**
     * 功能描述:昨日参与活动列表
     * 
     * @author: moruihai
     * @date: 2018/8/22 10:18
     * @param: []
     * @return: java.util.List<finance.core.dal.dataobject.CoinGameLogDO>
     */
    List<CoinGameLogDO> selectByYesterdayDate();

    /**
     * 功能描述:我的战绩--汇总数据
     * 
     * @author: moruihai
     * @date: 2018/8/22 14:42
     * @param: [userId]
     * @return: finance.model.vo.MyRecordVO
     */
    MyRecordVO selectMyRecordTotalData(@Param("userId") Long userId);

    /**
     * 功能描述:昨天打卡信息
     * 
     * @author: moruihai
     * @date: 2018/8/23 10:49
     * @param: [userId]
     * @return: finance.core.dal.dataobject.CoinGameLogDO
     */
    CoinGameLogDO selectByUserIdAndYesterdayDate(@Param("userId") Long userId);

    /**
     * 功能描述:更新打卡时间
     * 
     * @author: moruihai
     * @date: 2018/8/23 10:49
     * @param: [coinGameLogDO]
     * @return: java.lang.Long
     */
    Long updateByUserIdAndDateSelective(CoinGameLogDO coinGameLogDO);

    /**
     * @author ：tangwei
     * @Title: selectSignByFirst
     * @Description: 查询当前用户是否第一次打卡
     * @param userId
     * @return
     * @return: Integer
     */
    Integer selectSignByFirst(@Param("userId") Long userId);

}