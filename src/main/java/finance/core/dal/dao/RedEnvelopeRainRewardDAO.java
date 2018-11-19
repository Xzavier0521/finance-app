package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.RedEnvelopeRainRewardDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: RedEnvelopeRainRewardDAO.java, v0.1 2018/11/19 6:25 PM lili Exp $
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