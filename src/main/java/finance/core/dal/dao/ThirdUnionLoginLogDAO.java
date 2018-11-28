package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.ThirdUnionLoginLogDO;

/**
 * <p>第三方联合登陆日志</p>
 * @author lili
 * @version 1.0: ThirdUnionLoginLogDAO.java, v0.1 2018/11/28 8:32 PM lili Exp $
 */
public interface ThirdUnionLoginLogDAO {

    int insertSelective(ThirdUnionLoginLogDO record);

    List<ThirdUnionLoginLogDO> query(Map parameters);

    int count(Map parameters);

    ThirdUnionLoginLogDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdUnionLoginLogDO record);

}