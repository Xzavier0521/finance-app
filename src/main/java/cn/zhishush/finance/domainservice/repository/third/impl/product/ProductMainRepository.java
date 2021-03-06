package cn.zhishush.finance.domainservice.repository.third.impl.product;

import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

import java.util.List;

/**
 * <p>
 * 产品信息
 * </p>
 * 
 * @author lili
 * @version :1.0 ProductMainRepository.java.java, v 0.1 2018/9/27 下午8:40 lili
 *          Exp $
 */
public interface ProductMainRepository {
	/**
	 * 查询产品信息
	 * 
	 * @param ids
	 *            产品id列表
	 * @return List<ProductMain>
	 */
	List<ProductMain> queryByCondition(String... ids);

	/**
	 * 汇总收益
	 * 
	 * @param ids
	 *            产品id列表
	 * @return ProductMain
	 */
	ProductMain sumBonus(List<Long> ids);
}
