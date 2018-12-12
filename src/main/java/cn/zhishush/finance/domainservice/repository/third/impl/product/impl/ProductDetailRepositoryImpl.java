package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.ProductModuleTypeEnum;
import cn.zhishush.finance.core.dal.dao.product.ProductDetailDAO;
import cn.zhishush.finance.domain.product.ProductDetail;
import cn.zhishush.finance.domainservice.converter.product.ProductDetailConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.ProductDetailRepository;

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
