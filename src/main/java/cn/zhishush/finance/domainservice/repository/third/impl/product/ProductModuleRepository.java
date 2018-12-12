package cn.zhishush.finance.domainservice.repository.third.impl.product;

import java.util.List;

import cn.zhishush.finance.domain.product.ProductModule;

/**
 * <p>
 * 产品模块
 * </p>
 * 
 * @author lili
 * @version $Id: ProductModuleRepository.java, v0.1 2018/11/8 1:51 PM lili Exp $
 */
public interface ProductModuleRepository {

	/**
	 * 查询所有产品模块
	 * 
	 * @return List<ProductModule>
	 */
	List<ProductModule> queryAll();
}
