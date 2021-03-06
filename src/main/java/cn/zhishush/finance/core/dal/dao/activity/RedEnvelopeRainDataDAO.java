package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.activity.RedEnvelopeRainDataDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: RedEnvelopeRainDataDAO.java, v0.1 2018/11/16 10:41 AM lili Exp
 *          $
 */
public interface RedEnvelopeRainDataDAO {

    int insertSelective(RedEnvelopeRainDataDO record);

    List<RedEnvelopeRainDataDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainDataDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainDataDO record);

    RedEnvelopeRainDataDO queryTodayData(@Param("userId") Long userId,
                                         @Param("activityCode") String activityCode,
                                         @Param("activityDay") Integer activityDay);

    RedEnvelopeRainDataDO queryHistoryData(@Param("userId") Long userId,
                                           @Param("activityCode") String activityCode);

    List<RedEnvelopeRainDataDO> queryRankingList(Map<String, Object> parameters);

    List<RedEnvelopeRainDataDO> queryDailyRainData(Map<String, Object> parameters);

    Long countDailyRainData(Map<String, Object> parameters);
}