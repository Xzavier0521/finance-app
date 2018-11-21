package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.ThirdAccountInfoDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ThirdAccountInfoDAO.java, v0.1 2018/11/21 3:39 PM lili Exp $
 */
public interface ThirdAccountInfoDAO {
    int deleteByPrimaryKey(Long id);

    int insert(ThirdAccountInfoDO record);

    int insertSelective(ThirdAccountInfoDO record);

    List<ThirdAccountInfoDO> query(Map parameters);

    int count(Map parameters);

    ThirdAccountInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdAccountInfoDO record);

    int updateByPrimaryKey(ThirdAccountInfoDO record);
}