package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceFinancialProductDetail;

public interface FinanceFinancialProductDetailDAO extends
        BaseDAO<FinanceFinancialProductDetail, Long> {
    /**
     * 根据产品ID查询我要理财页数据
     * @param productIds
     * @return
     */
    List<FinanceFinancialProductDetail> selectByProductId(@Param("productIds") List<Long> productIds);

    /**
     * 根据产品ID查询我要理财详情页数据
     * @param productId
     * @return
     */
    FinanceFinancialProductDetail selectProductDetailByProductId(@Param("productId") Long productId);

}