package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.FinancialProductDetailDO;
import org.apache.ibatis.annotations.Param;

public interface FinancialProductDetailDAO extends BaseDAO<FinancialProductDetailDO, Long> {
    /**
     * 根据产品ID查询我要理财页数据
     * 
     * @param productIds
     * @return
     */
    List<FinancialProductDetailDO> selectByProductId(@Param("productIds") List<Long> productIds);

    /**
     * 根据产品ID查询我要理财详情页数据
     * 
     * @param productId
     * @return
     */
    FinancialProductDetailDO selectProductDetailByProductId(@Param("productId") Long productId);

}