package cn.zhishush.finance.core.dal.dao.third;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.third.ThirdAccountInfoDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ThirdAccountInfoDAO.java, v0.1 2018/11/21 3:39 PM lili Exp $
 */
public interface ThirdAccountInfoDAO {

    int insertSelective(ThirdAccountInfoDO record);

    List<ThirdAccountInfoDO> query(Map parameters);

    int count(Map parameters);

    ThirdAccountInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ThirdAccountInfoDO record);

}