package cn.zhishush.finance.core.dal.dao.business;

import cn.zhishush.finance.core.dal.dataobject.business.BusinessCooperationDO;

/**
 * <p>商务合作</p>
 * 
 * @author lili
 * @version $Id: BusinessCooperationDAO.java, v0.1 2018/10/29 4:47 PM lili Exp $
 */
public interface BusinessCooperationDAO {

    int insertSelective(BusinessCooperationDO record);

    BusinessCooperationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCooperationDO record);

}