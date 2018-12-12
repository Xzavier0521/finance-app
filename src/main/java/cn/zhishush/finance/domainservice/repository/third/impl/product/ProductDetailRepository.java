package cn.zhishush.finance.domainservice.repository.third.impl.product;

import java.util.List;

import cn.zhishush.finance.core.common.enums.ProductModuleTypeEnum;
import cn.zhishush.finance.domain.product.ProductDetail;

/**
 * <p>
 * 产品明细
 * </p>
 *
 * @author lili
 * @version 1.0: ProductDetailRepository.java, v0.1 2018/11/8 4:32 PM PM lili
 *          Exp $
 */
public interface ProductDetailRepository {

	List<ProductDetail> queryProductByModule(ProductModuleTypeEnum productModuleType);
}
