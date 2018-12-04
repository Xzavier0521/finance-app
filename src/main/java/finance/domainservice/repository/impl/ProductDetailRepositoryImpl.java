package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.common.enums.ProductModuleTypeEnum;
import finance.core.dal.dao.ProductDetailDAO;
import finance.domain.product.ProductDetail;
import finance.domainservice.converter.ProductDetailConverter;
import finance.domainservice.repository.ProductDetailRepository;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ProductDetailRepositoryImpl.java, v0.1 2018/11/8 4:33 PM lili Exp $
 */
@Repository("productDetailRepository")
public class ProductDetailRepositoryImpl implements ProductDetailRepository {

    @Resource
    private ProductDetailDAO productDetailDAO;

    @Override
    public List<ProductDetail> queryProductByModule(ProductModuleTypeEnum productModuleType) {

        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("module_code", productModuleType.getCode());
        return ProductDetailConverter.convert2List(productDetailDAO.query(parameters));
    }
}
