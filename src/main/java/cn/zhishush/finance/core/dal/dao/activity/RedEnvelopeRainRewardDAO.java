package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.activity.RedEnvelopeRainRewardDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: RedEnvelopeRainRewardDAO.java, v0.1 2018/11/19 6:25 PM lili Exp
 *          $
 */
public interface RedEnvelopeRainRewardDAO {

    int insertSelective(RedEnvelopeRainRewardDO record);

    List<RedEnvelopeRainRewardDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainRewardDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainRewardDO record);

}