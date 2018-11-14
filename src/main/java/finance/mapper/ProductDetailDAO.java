package finance.mapper;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.ProductDetailDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ProductDetailDAO.java, v0.1 2018/11/9 2:09 PM PM lili Exp $
 */

public interface ProductDetailDAO {

    int deleteByPrimaryKey(Long id);

    int insert(ProductDetailDO record);

    int insertSelective(ProductDetailDO record);

    List<ProductDetailDO> query(Map parameters);

    int count(Map parameters);

    ProductDetailDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductDetailDO record);

    int updateByPrimaryKey(ProductDetailDO record);
}
