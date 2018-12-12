package cn.zhishush.finance.core.dal.dao.coin;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.vo.coin.JoinTaskBaseVO;
import cn.zhishush.finance.api.model.vo.coin.MyRecordVO;
import cn.zhishush.finance.core.dal.dataobject.coin.CoinGameLogDO;

/**
 * <p>金币游戏日志</p>
 * 
 * @author lili
 * @version $Id: CoinGameLogDAO.java, v0.1 2018/11/14 12:27 PM lili Exp $
 */
public interface CoinGameLogDAO extends BaseDAO<CoinGameLogDO, Long> {
    /**
     * 根据当前时间查询参与游戏记录
     * @param isYesJoinDate
     * @param isJoinDate
     * @param isClockDate
     * @param userId
     * @return
     */
    CoinGameLogDO selectByCurrentDate(@Param("isYesJoinDate") String isYesJoinDate,
                                      @Param("isJoinDate") String isJoinDate,
                                      @Param("isClockDate") String isClockDate,
                                      @Param("userId") Long userId);

    /**
     * 总参加打卡人数（报名参加明天的打卡） totalCoinNum 总金币数
     * @return JoinTaskBaseVO
     */
    JoinTaskBaseVO selectAllDataByCurrentDay();

    /**
     *
     * @param signDate
     * @param inNum
     * @param clockCount
     * @return
     */
    List<CoinGameLogDO> selectByCondition(@Param("signDate") String signDate,
                                          @Param("inNum") String inNum,
                                          @Param("clockCount") String clockCount);

    /**
     * 昨日参与活动列表
     * @return List<CoinGameLogDO>
     */
    List<CoinGameLogDO> selectByYesterdayDate();

    /**
     * 我的战绩--汇总数据
     * @param userId 用户id
     * @return MyRecordVO
     */
    MyRecordVO selectMyRecordTotalData(@Param("userId") Long userId);

    /**
     * 昨天打卡信息
     * @param userId 用户id
     * @return CoinGameLogDO
     */
    CoinGameLogDO selectByUserIdAndYesterdayDate(@Param("userId") Long userId);

    /**
     * 更新打卡时间
     * @param coinGameLogDO 记录
     * @return Long
     */
    Long updateByUserIdAndDateSelective(CoinGameLogDO coinGameLogDO);

    /**
     * 查询当前用户是否第一次打卡
     * @param userId 用户id
     * @return Integer
     */
    Integer selectSignByFirst(@Param("userId") Long userId);

}