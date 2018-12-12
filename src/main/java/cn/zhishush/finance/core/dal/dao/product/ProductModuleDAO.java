package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.ProductModuleDO;

/**
 * <p>产品模块</p>
 * 
 * @author lili
 * @version 1.0 ProductModuleDAO.java, v0.1 2018/11/8 1:47 PM lili Exp $
 */
public interface ProductModuleDAO {

    int insertSelective(ProductModuleDO record);

    List<ProductModuleDO> query(Map parameters);

    int count(Map parameters);

    ProductModuleDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductModuleDO record);

}
