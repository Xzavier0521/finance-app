package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.CreditCardDetailDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 信用卡产品
 * </p>
 * 
 * @author lili
 * @version $Id: CreditCardDetailDAO.java, v0.1 2018/11/14 12:28 PM lili Exp $
 */
public interface CreditCardDetailDAO extends BaseDAO<CreditCardDetailDO, Long> {

    /**
     * 根据产品ID查询我要理财页数据
     * 
     * @param productIds
     * @return
     */
    List<CreditCardDetailDO> selectByProductId(@Param("productIds") List<Long> productIds);

    /**
     * 根据产品ID查询我要办卡详情页数据
     * 
     * @param productId
     * @return
     */
    CreditCardDetailDO selectProductDetailByProductId(@Param("productId") Long productId);
}