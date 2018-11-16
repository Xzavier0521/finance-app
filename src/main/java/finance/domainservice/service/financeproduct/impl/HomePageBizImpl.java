package finance.domainservice.service.financeproduct.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.productmainpage.ProductMainpageVO;
import finance.domainservice.service.financeproduct.HomePageBiz;
import finance.core.dal.dao.FinanceProductMainPageDAO;

/**
 * @program: finance-app
 *
 * @description: 集合页BIZ实现类
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-13 09:23
 **/
@Service
public class HomePageBizImpl implements HomePageBiz {
    @Resource
    private FinanceProductMainPageDAO financeProductMainPageMapper;

    @Override
    public List<ProductMainpageVO> findHomePageList(Long productType,
                                                    Page<ProductMainpageVO> page) {
        return financeProductMainPageMapper.selectHomePageListByProductType(productType, page);
    }
}
