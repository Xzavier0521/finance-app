package finance.domainservice.service.financeproduct.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.CreditCardProductDetailVO;
import finance.api.model.vo.financeproduct.CreditCardProductListVO;
import finance.domainservice.service.financeproduct.CreditCardProductBiz;
import finance.mapper.FinanceCreditCardDetailDAO;
import finance.mapper.FinanceProductMainDAO;
import finance.model.po.FinanceCreditCardDetail;
import finance.model.po.FinanceProductMain;

/**
 * @program: finance-app
 * @description: 信用卡操作BIZ实现层
 * @author: MORUIHAI
 * @create: 2018-07-12 17:27
 **/
@Service
public class CreditCardProductBizImpl implements CreditCardProductBiz {
    @Resource
    private FinanceCreditCardDetailDAO financeCreditCardDetailMapper;
    @Resource
    private FinanceProductMainDAO      financeProductMainMapper;

    @Override
    public List<CreditCardProductListVO> findProductList(Page<FinanceProductMain> financeProductPage) {

        List<CreditCardProductListVO> creditCardProductList = new ArrayList<CreditCardProductListVO>();
        //根据类型查主表数据
        List<FinanceProductMain> financeProductMainList = financeProductMainMapper
            .selectProductByType(2, financeProductPage);

        if (financeProductMainList != null && financeProductMainList.size() > 0) {
            //遍历主表数据取得id
            List<Long> financeMainIds = new ArrayList<>();
            financeProductMainList
                .forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
            //根据主表ID查询出办卡明细表
            List<FinanceCreditCardDetail> financeCreditCardDetails = financeCreditCardDetailMapper
                .selectByProductId(financeMainIds);
            CreditCardProductListVO creditCardProductListVO = null;
            FinanceCreditCardDetail financeCreditCardDetail = null;

            Map<Long, FinanceCreditCardDetail> ffpdMap = new HashMap<Long, FinanceCreditCardDetail>();
            financeCreditCardDetails.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

            for (FinanceProductMain fpm : financeProductMainList) {
                financeCreditCardDetail = ffpdMap.get(fpm.getId());
                creditCardProductListVO = new CreditCardProductListVO();

                creditCardProductListVO.setId(fpm.getId());
                creditCardProductListVO.setProductName(fpm.getProductName());
                creditCardProductListVO.setLogoUrl(fpm.getLogoUrl());
                creditCardProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());
                creditCardProductListVO.setPassRate(financeCreditCardDetail.getPassRate());
                creditCardProductListVO
                    .setRebackCashDesc(financeCreditCardDetail.getRebackCashDesc());
                creditCardProductListVO.setDirectBonus(fpm.getDirectBonus());
                creditCardProductListVO.setIndirectBonus(fpm.getIndirectBonus());
                creditCardProductList.add(creditCardProductListVO);
            }

        }
        return creditCardProductList;
    }

    @Override
    public CreditCardProductDetailVO findProductDetailByProductId(Long productId) {
        //根据id查主表数据
        FinanceProductMain financeProductMain = financeProductMainMapper
            .selectByPrimaryKey(productId);
        FinanceCreditCardDetail financeCreditCardDetail = financeCreditCardDetailMapper
            .selectProductDetailByProductId(productId);

        CreditCardProductDetailVO creditCardProductDetailVO = new CreditCardProductDetailVO();
        creditCardProductDetailVO.setId(financeProductMain.getId());
        creditCardProductDetailVO.setProductName(financeProductMain.getProductName());
        creditCardProductDetailVO.setDetailPageUrl(financeCreditCardDetail.getDetailPageUrl());
        creditCardProductDetailVO.setAuditLength(financeCreditCardDetail.getAuditLength());
        creditCardProductDetailVO.setPassRate(financeCreditCardDetail.getPassRate());
        creditCardProductDetailVO.setMaxAmount(financeCreditCardDetail.getMaxAmount());
        creditCardProductDetailVO.setTerminalBonus(financeProductMain.getTerminalBonus(),
            financeProductMain.getAmountType());
        creditCardProductDetailVO.setDirectBonus(financeProductMain.getDirectBonus(),
            financeProductMain.getAmountType());
        creditCardProductDetailVO.setIndirectBonus(financeProductMain.getIndirectBonus(),
            financeProductMain.getAmountType());
        creditCardProductDetailVO.setCashbackDate(financeProductMain.getCashbackDate());
        creditCardProductDetailVO.setRedirectUrl(financeProductMain.getRedirectUrl());

        creditCardProductDetailVO.setProductDesc(financeProductMain.getProductDesc());
        creditCardProductDetailVO.setPromotionUrl(financeProductMain.getPromotionUrl());

        return creditCardProductDetailVO;
    }
}
