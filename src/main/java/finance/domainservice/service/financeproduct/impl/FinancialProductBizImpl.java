package finance.domainservice.service.financeproduct.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.FinancingProductDetailVO;
import finance.api.model.vo.financeproduct.FinancingProductListVO;
import finance.domainservice.service.financeproduct.FinanceProductBiz;
import finance.core.dal.dao.FinanceFinancialProductDetailDAO;
import finance.core.dal.dao.FinanceProductMainDAO;
import finance.core.dal.dataobject.FinanceFinancialProductDetail;
import finance.core.dal.dataobject.FinanceProductMain;

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
    private FinanceFinancialProductDetailDAO financeFinancialProductDetailMapper;
    @Resource
    private FinanceProductMainDAO            financeProductMainMapper;

    @Override
    public List<FinancingProductListVO> findProductList(Page<FinanceProductMain> financeProductPage) {
        List<FinancingProductListVO> financingProductList = new ArrayList<FinancingProductListVO>();
        //根据类型查主表数据
        List<FinanceProductMain> financeProductMainList = financeProductMainMapper
            .selectProductByType(1, financeProductPage);

        if (financeProductMainList != null && financeProductMainList.size() > 0) {
            //遍历主表数据取得id
            List<Long> financeMainIds = new ArrayList<>();
            financeProductMainList
                .forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
            //根据主表ID查询出理财明细表
            List<FinanceFinancialProductDetail> financeFinancialProductDetails = financeFinancialProductDetailMapper
                .selectByProductId(financeMainIds);
            FinancingProductListVO financingProductListVO = null;
            FinanceFinancialProductDetail financeFinancialProductDetail = null;

            Map<Long, FinanceFinancialProductDetail> ffpdMap = new HashMap<Long, FinanceFinancialProductDetail>();
            financeFinancialProductDetails.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

            for (FinanceProductMain fpm : financeProductMainList) {
                financeFinancialProductDetail = ffpdMap.get(fpm.getId());
                financingProductListVO = new FinancingProductListVO();

                financingProductListVO.setId(fpm.getId());
                financingProductListVO.setProductName(fpm.getProductName());
                financingProductListVO.setLogoUrl(fpm.getLogoUrl());
                financingProductListVO
                    .setMark(Arrays.asList(financeFinancialProductDetail.getMark().split(",")));
                financingProductListVO.setAveRevenue(financeFinancialProductDetail.getAveRevenue());
                financingProductListVO
                    .setProductBackground(financeFinancialProductDetail.getProductBackground());
                financingProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());

                financingProductList.add(financingProductListVO);
            }

        }
        return financingProductList;
    }

    @Override
    public FinancingProductDetailVO findProductDetailByProductId(Long productId) {
        //根据id查主表数据
        FinanceProductMain financeProductMain = financeProductMainMapper
            .selectByPrimaryKey(productId);
        FinanceFinancialProductDetail financeFinancialProductDetail = financeFinancialProductDetailMapper
            .selectProductDetailByProductId(productId);

        FinancingProductDetailVO financingProductDetailVO = new FinancingProductDetailVO();
        financingProductDetailVO.setId(financeProductMain.getId());
        financingProductDetailVO.setProductName(financeProductMain.getProductName());
        financingProductDetailVO.setRedirectUrl(financeProductMain.getRedirectUrl());
        financingProductDetailVO.setTerminalBonus(financeProductMain.getTerminalBonus(),
            financeProductMain.getAmountType());
        financingProductDetailVO.setDirectBonus(financeProductMain.getDirectBonus(),
            financeProductMain.getAmountType());
        financingProductDetailVO.setIndirectBonus(financeProductMain.getIndirectBonus(),
            financeProductMain.getAmountType());
        financingProductDetailVO.setLogoUrl(financeProductMain.getLogoUrl());
        financingProductDetailVO.setCashbackDate(financeProductMain.getCashbackDate());
        financingProductDetailVO.setLevel(financeFinancialProductDetail.getGrade());
        financingProductDetailVO
            .setBackgroundStrength(financeFinancialProductDetail.getBackgroundStrength());
        financingProductDetailVO.setRiskControl(financeFinancialProductDetail.getRiskControl());
        financingProductDetailVO
            .setOperationCapability(financeFinancialProductDetail.getOperationCapability());
        financingProductDetailVO.setStartAmount(financeFinancialProductDetail.getStartAmount());
        financingProductDetailVO.setStartPeriod(financeFinancialProductDetail.getStartPeriod());
        financingProductDetailVO.setRebackName(financeFinancialProductDetail.getRebackName());
        financingProductDetailVO.setRebackValue(financeFinancialProductDetail.getRebackValue());
        financingProductDetailVO.setTotalReturn(financeFinancialProductDetail.getTotalReturn());
        financingProductDetailVO.setAveRevenue(financeFinancialProductDetail.getAveRevenue());
        financingProductDetailVO.setCashbackRule(financeFinancialProductDetail.getCashbackRule());
        financingProductDetailVO.setProductDesc(financeProductMain.getProductDesc());
        financingProductDetailVO.setPromotionUrl(financeProductMain.getPromotionUrl());

        return financingProductDetailVO;
    }
}
