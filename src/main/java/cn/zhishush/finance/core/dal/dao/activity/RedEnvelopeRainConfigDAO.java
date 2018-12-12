package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.activity.RedEnvelopeRainConfigDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeRainConfigDAO.java, v0.1 2018/11/16 11:42 AM lili
 *          Exp $
 */
public interface RedEnvelopeRainConfigDAO {

    int insertSelective(RedEnvelopeRainConfigDO record);

    List<RedEnvelopeRainConfigDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainConfigDO record);

}