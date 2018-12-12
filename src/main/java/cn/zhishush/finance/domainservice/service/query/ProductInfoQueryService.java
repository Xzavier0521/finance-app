package cn.zhishush.finance.domainservice.service.query;

import java.util.List;

import cn.zhishush.finance.core.common.enums.ProductModuleTypeEnum;
import cn.zhishush.finance.domain.product.ProductModule;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: ProductInfoQueryService.java, v0.1 2018/11/8 3:31 PM PM lili
 *          Exp $
 */
public interface ProductInfoQueryService {

	List<ProductModule> queryProductModuleByType(ProductModuleTypeEnum productModuleType);
}
