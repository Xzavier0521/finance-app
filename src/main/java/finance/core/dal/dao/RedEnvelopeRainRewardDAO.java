package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.RedEnvelopeRainRewardDO;

/**
 * <p>红包雨活动金币奖励日志</p>
 * @author lili
 * @version $Id: RedEnvelopeRainRewardDAO.java, v0.1 2018/11/18 3:01 PM lili Exp $
 */
public interface RedEnvelopeRainRewardDAO {

    int deleteByPrimaryKey(Long id);

    int insert(RedEnvelopeRainRewardDO record);

    int insertSelective(RedEnvelopeRainRewardDO record);

    List<RedEnvelopeRainRewardDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainRewardDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainRewardDO record);

    int updateByPrimaryKey(RedEnvelopeRainRewardDO record);
}