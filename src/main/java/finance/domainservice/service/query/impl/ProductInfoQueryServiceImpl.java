package finance.domainservice.service.query.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import finance.core.common.enums.ProductModuleTypeEnum;
import finance.domain.product.ProductModule;
import finance.domainservice.service.query.ProductInfoQueryService;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: ProductInfoQueryServiceImpl.java, v0.1 2018/11/9 2:42 PM PM
 *          lili Exp $
 */
@Service("productInfoQueryService")
public class ProductInfoQueryServiceImpl implements ProductInfoQueryService {
	@Override
	public List<ProductModule> queryProductModuleByType(ProductModuleTypeEnum productModuleType) {
		return null;
	}
}
