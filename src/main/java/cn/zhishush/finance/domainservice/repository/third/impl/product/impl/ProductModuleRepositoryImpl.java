package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.domain.product.ProductModule;
import cn.zhishush.finance.domainservice.converter.product.ProductModuleConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.ProductModuleRepository;
import cn.zhishush.finance.core.dal.dao.product.ProductModuleDAO;

/**
 * <p>产品模块</p>
 *
 * @author lili
 * @version 1.0: ProductModuleRepositoryImpl.java, v0.1 2018/11/8 1:53 PM lili Exp $
 */
@Repository("productModuleRepository")
public class ProductModuleRepositoryImpl implements ProductModuleRepository {

	@Resource
	private ProductModuleDAO productModuleDAO;

	@Override
	public List<ProductModule> queryAll() {
		return ProductModuleConverter.convert2List(productModuleDAO.query(Maps.newHashMap()));
	}
}
