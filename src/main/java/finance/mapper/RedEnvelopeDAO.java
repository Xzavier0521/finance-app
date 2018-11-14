package finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import finance.model.po.LeaderBoardDO;
import finance.model.po.ParticipantInfoDO;

/**
 * <p>红包活动查询</p>
 * @author lili
 * @version $Id: RedEnvelopeDAO.java, v0.1 2018/10/19 8:50 PM lili Exp $
 */
public interface RedEnvelopeDAO {

    /**
     * 查询一级排行榜
     * @param activityCode 活动代码
     * @param num  num 排行榜人数
     * @return List<LeaderBoardDO>
     */
    List<LeaderBoardDO> queryFirstRanking(@Param("activityCode") String activityCode,
                                          @Param("num") int num);

    /**
     * 查询二级排行榜
     * @param activityCode 活动代码
     * @param num  num 排行榜人数
     * @return List<LeaderBoardDO>
     */
    List<LeaderBoardDO> querySecondRanking(@Param("activityCode") String activityCode,
                                           @Param("num") int num);

    /**
     * 查询总排行榜
     * @param activityCode 活动代码
     * @param num  num 排行榜人数
     * @return List<LeaderBoardDO>
     */
    List<LeaderBoardDO> queryTotalRanking(@Param("activityCode") String activityCode,
                                          @Param("num") int num);

    /**
     * 查询红包邀请人数
     * @param activityCode 活动代码
     * @return Long
     */
    Long queryJoinNum(String activityCode);

    /**
     *  查询红包活动明细　总数
     * @param parameters 查询条件
     * @return int
     */
    Long countJoinNum(Map<String, Object> parameters);

    /**
     *  分页查询红包活动明细
     * @param parameters 查询条件
     * @return Page<ParticipantInfoDO>
     */
    List<ParticipantInfoDO> queryJoinDetail4Page(Map<String, Object> parameters);

    /**
     *  查询用户的活动明细
     * @param userId 查询条件
     * @return ParticipantInfoDO
     */
    ParticipantInfoDO querySingleDetail(@Param("userId") Long userId);

}
