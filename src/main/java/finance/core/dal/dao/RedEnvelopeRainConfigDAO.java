package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.RedEnvelopeRainConfigDO;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: RedEnvelopeRainConfigDAO.java, v0.1 2018/11/16 11:42 AM lili Exp $
 */
public interface RedEnvelopeRainConfigDAO {
    
    int deleteByPrimaryKey(Long id);

    int insert(RedEnvelopeRainConfigDO record);

    int insertSelective(RedEnvelopeRainConfigDO record);

    List<RedEnvelopeRainConfigDO> query(Map parameters);

    int count(Map parameters);

    RedEnvelopeRainConfigDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RedEnvelopeRainConfigDO record);

    int updateByPrimaryKey(RedEnvelopeRainConfigDO record);
}