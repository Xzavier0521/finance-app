package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.ProductModuleDO;

/**
 * <p>产品模块</p>
 * @author lili
 * @version 1.0 ProductModuleDAO.java, v0.1 2018/11/8 1:47 PM lili Exp $
 */
public interface ProductModuleDAO {

    int deleteByPrimaryKey(Long id);

    int insert(ProductModuleDO record);

    int insertSelective(ProductModuleDO record);

    List<ProductModuleDO> query(Map parameters);

    int count(Map parameters);

    ProductModuleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductModuleDO record);

    int updateByPrimaryKey(ProductModuleDO record);
}
