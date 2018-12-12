package cn.zhishush.finance.domainservice.service.financeproduct.impl;

import java.util.*;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dataobject.product.FinancialProductDetailDO;
import org.springframework.stereotype.Service;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeproduct.FinancingProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.FinancingProductListVO;
import cn.zhishush.finance.domainservice.service.financeproduct.FinanceProductBiz;
import cn.zhishush.finance.core.dal.dao.product.FinancialProductDetailDAO;
import cn.zhishush.finance.core.dal.dao.product.ProductMainDAO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * @program: finance-app
 *
 * @description: 理财产品Biz实现类
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-12 09:59
 **/
@Service
public class FinancialProductBizImpl implements FinanceProductBiz {

    @Resource
    private FinancialProductDetailDAO financeFinancialProductDetailMapper;
    @Resource
    private ProductMainDAO            financeProductMainMapper;

    @Override
    public List<FinancingProductListVO> findProductList(Page<ProductMain> financeProductPage) {
        List<FinancingProductListVO> financingProductList = new ArrayList<FinancingProductListVO>();
        // 根据类型查主表数据
        List<ProductMain> productMainList = financeProductMainMapper
            .selectProductByType(1, financeProductPage);

        if (productMainList != null && productMainList.size() > 0) {
            // 遍历主表数据取得id
            List<Long> financeMainIds = new ArrayList<>();
            productMainList
                .forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
            // 根据主表ID查询出理财明细表
            List<FinancialProductDetailDO> financialProductDetailDOS = financeFinancialProductDetailMapper
                .selectByProductId(financeMainIds);
            FinancingProductListVO financingProductListVO = null;
            FinancialProductDetailDO financialProductDetailDO = null;

            Map<Long, FinancialProductDetailDO> ffpdMap = new HashMap<Long, FinancialProductDetailDO>();
            financialProductDetailDOS.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

            for (ProductMain fpm : productMainList) {
                financialProductDetailDO = ffpdMap.get(fpm.getId());
                financingProductListVO = new FinancingProductListVO();

                financingProductListVO.setId(fpm.getId());
                financingProductListVO.setProductName(fpm.getProductName());
                financingProductListVO.setLogoUrl(fpm.getLogoUrl());
                financingProductListVO
                    .setMark(Arrays.asList(financialProductDetailDO.getMark().split(",")));
                financingProductListVO.setAveRevenue(financialProductDetailDO.getAveRevenue());
                financingProductListVO
                    .setProductBackground(financialProductDetailDO.getProductBackground());
                financingProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());

                financingProductList.add(financingProductListVO);
            }

        }
        return financingProductList;
    }

    @Override
    public FinancingProductDetailVO findProductDetailByProductId(Long productId) {
        // 根据id查主表数据
        ProductMain productMain = financeProductMainMapper
            .selectByPrimaryKey(productId);
        FinancialProductDetailDO financialProductDetailDO = financeFinancialProductDetailMapper
            .selectProductDetailByProductId(productId);

        FinancingProductDetailVO financingProductDetailVO = new FinancingProductDetailVO();
        financingProductDetailVO.setId(productMain.getId());
        financingProductDetailVO.setProductName(productMain.getProductName());
        financingProductDetailVO.setRedirectUrl(productMain.getRedirectUrl());
        financingProductDetailVO.setTerminalBonus(productMain.getTerminalBonus(),
            productMain.getAmountType());
        financingProductDetailVO.setDirectBonus(productMain.getDirectBonus(),
            productMain.getAmountType());
        financingProductDetailVO.setIndirectBonus(productMain.getIndirectBonus(),
            productMain.getAmountType());
        financingProductDetailVO.setLogoUrl(productMain.getLogoUrl());
        financingProductDetailVO.setCashbackDate(productMain.getCashbackDate());
        financingProductDetailVO.setLevel(financialProductDetailDO.getGrade());
        financingProductDetailVO
            .setBackgroundStrength(financialProductDetailDO.getBackgroundStrength());
        financingProductDetailVO.setRiskControl(financialProductDetailDO.getRiskControl());
        financingProductDetailVO
            .setOperationCapability(financialProductDetailDO.getOperationCapability());
        financingProductDetailVO.setStartAmount(financialProductDetailDO.getStartAmount());
        financingProductDetailVO.setStartPeriod(financialProductDetailDO.getStartPeriod());
        financingProductDetailVO.setRebackName(financialProductDetailDO.getRebackName());
        financingProductDetailVO.setRebackValue(financialProductDetailDO.getRebackValue());
        financingProductDetailVO.setTotalReturn(financialProductDetailDO.getTotalReturn());
        financingProductDetailVO.setAveRevenue(financialProductDetailDO.getAveRevenue());
        financingProductDetailVO.setCashbackRule(financialProductDetailDO.getCashbackRule());
        financingProductDetailVO.setProductDesc(productMain.getProductDesc());
        financingProductDetailVO.setPromotionUrl(productMain.getPromotionUrl());

        return financingProductDetailVO;
    }
}
