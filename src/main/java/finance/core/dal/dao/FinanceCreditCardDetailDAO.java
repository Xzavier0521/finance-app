package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceCreditCardDetail;

/**
 * <p>信用卡产品</p>
 * @author lili
 * @version $Id: FinanceCreditCardDetailDAO.java, v0.1 2018/11/14 12:28 PM lili Exp $
 */
public interface FinanceCreditCardDetailDAO extends BaseDAO<FinanceCreditCardDetail, Long> {

    /**
     * 根据产品ID查询我要理财页数据
     * @param productIds
     * @return
     */
    List<FinanceCreditCardDetail> selectByProductId(@Param("productIds") List<Long> productIds);

    /**
     * 根据产品ID查询我要办卡详情页数据
     * @param productId
     * @return
     */
    FinanceCreditCardDetail selectProductDetailByProductId(@Param("productId") Long productId);
}