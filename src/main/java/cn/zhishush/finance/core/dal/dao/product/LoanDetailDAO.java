package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.product.LoanDetailDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: LoanDetailDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
public interface LoanDetailDAO extends BaseDAO<LoanDetailDO, Long> {
	/**
	 * 根据产品ID查询我要贷款页数据
	 * 
	 * @param productIds
	 * @return
	 */
	List<LoanDetailDO> selectByProductId(@Param("productIds") List<Long> productIds);

	/**
	 * 根据产品ID查询我要贷款详情页数据
	 * 
	 * @param productId
	 * @return
	 */
	LoanDetailDO selectProductDetailByProductId(@Param("productId") Long productId);
}