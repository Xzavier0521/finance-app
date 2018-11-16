package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.RedEnvelopeRainDataDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: RedEnvelopeRainDataDAO.java, v0.1 2018/11/16 10:41 AM lili Exp $
 */
public interface RedEnvelopeRainDataDAO {

    int deleteByPrimaryKey(Long id);

    int insert(RedEnvelopeRainDataDO record);

    int insertSelective(RedEnvelopeRainDataDO record);

    List<RedEnvelopeRainDataDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainDataDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainDataDO record);

    int updateByPrimaryKey(RedEnvelopeRainDataDO record);

    RedEnvelopeRainDataDO queryTodayData(@Param("userId") Long userId,
                                         @Param("activityCode") String activityCode,
                                         @Param("activityDay") Integer activityDay);

    RedEnvelopeRainDataDO queryHistoryData(@Param("userId") Long userId,
                                           @Param("activityCode") String activityCode);

    List<RedEnvelopeRainDataDO> queryRankingList(Map<String, Object> parameters);
}