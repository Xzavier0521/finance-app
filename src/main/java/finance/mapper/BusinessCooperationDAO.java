package finance.mapper;

import finance.model.po.BusinessCooperationDO;

/**
 * <p>商务客户</p>
 * @author lili
 * @version $Id: BusinessCooperationDAO.java, v0.1 2018/10/29 4:47 PM lili Exp $
 */
public interface BusinessCooperationDAO {

    int deleteByPrimaryKey(Long id);

    int insert(BusinessCooperationDO record);

    int insertSelective(BusinessCooperationDO record);

    BusinessCooperationDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusinessCooperationDO record);

    int updateByPrimaryKey(BusinessCooperationDO record);
}