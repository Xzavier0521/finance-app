package cn.zhishush.finance.core.dal.dao.order;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.user.UserApplyCreditCardDetailDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserApplyCreditCardDetailDAO.java, v0.1 2018/12/7 1:48 AM lili Exp $
 */
public interface UserApplyCreditCardDetailDAO {


    int insertSelective(UserApplyCreditCardDetailDO record);

    List<UserApplyCreditCardDetailDO> query(Map parameters);

    int count(Map parameters);

    UserApplyCreditCardDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserApplyCreditCardDetailDO record);

}