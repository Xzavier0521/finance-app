package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.product.FinancialProductDetailDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: FinancialProductDetailDAO.java, v0.1 2018/12/7 1:38 AM lili Exp $
 */
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