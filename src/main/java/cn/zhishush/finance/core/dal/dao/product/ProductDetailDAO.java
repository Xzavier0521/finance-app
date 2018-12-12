package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.ProductDetailDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ProductDetailDAO.java, v0.1 2018/11/9 2:09 PM PM lili Exp $
 */

public interface ProductDetailDAO {

	int insertSelective(ProductDetailDO record);

	List<ProductDetailDO> query(Map parameters);

	int count(Map parameters);

	ProductDetailDO selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(ProductDetailDO record);

}
