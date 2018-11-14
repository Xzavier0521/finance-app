package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.model.po.FinanceLoanDetail;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceLoanDetailDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
public interface FinanceLoanDetailDAO extends BaseDAO<FinanceLoanDetail, Long> {
    /**
     * 根据产品ID查询我要贷款页数据
     * @param productIds
     * @return
     */
    List<FinanceLoanDetail> selectByProductId(@Param("productIds") List<Long> productIds);

    /**
     * 根据产品ID查询我要贷款详情页数据
     * @param productId
     * @return
     */
    FinanceLoanDetail selectProductDetailByProductId(@Param("productId") Long productId);
}