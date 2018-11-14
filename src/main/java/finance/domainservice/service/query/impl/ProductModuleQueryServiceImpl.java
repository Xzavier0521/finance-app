package finance.domainservice.service.query.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import finance.domain.product.ProductModule;
import finance.domainservice.repository.ProductDetailRepository;
import finance.domainservice.repository.ProductModuleRepository;
import finance.domainservice.service.query.ProductModuleQueryService;
import finance.api.model.vo.product.ProductModuleVO;

/**
 * <p>产品模块查询</p>
 *
 * @author lili
 * @version 1.0: ProductModuleQueryServiceImpl.java, v0.1 2018/11/8 5:20 PM PM lili Exp $
 */
@Slf4j
@Service("productModuleQueryService")
public class ProductModuleQueryServiceImpl implements ProductModuleQueryService {

    @Resource
    private ProductModuleRepository productModuleRepository;

    @Resource
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductModuleVO> queryAllModule() {

        List<ProductModuleVO> productModuleVOList = Lists.newArrayList();
        List<ProductModule> productModules = productModuleRepository.queryAll();
        if (CollectionUtils.isEmpty(productModules)) {
            return productModuleVOList;
        }
        ProductModuleVO productModuleVO;
        for (ProductModule productModule : productModules) {
            productModuleVO = new ProductModuleVO();
            productModuleVO.setProductModule(productModule);
            productModuleVO.setProductDetails(
                productDetailRepository.queryProductByModule(productModule.getModuleCode()));
            productModuleVOList.add(productModuleVO);
        }
        return productModuleVOList;
    }
}
