package finance.domainservice.service.financeproduct.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.LoanProductDetailVO;
import finance.api.model.vo.financeproduct.LoanProductListVO;
import finance.domainservice.service.financeproduct.LoanProductBiz;
import finance.core.dal.dao.FinanceLoanDetailDAO;
import finance.core.dal.dao.FinanceProductMainDAO;
import finance.core.dal.dataobject.FinanceLoanDetail;
import finance.core.dal.dataobject.FinanceProductMain;

/**
 * <p>贷款产品Biz实现层</p>
 * @author MORUIHAI
 * @version 1.0: LoanProductBizImpl.java, v0.1 2018/07/12 18:28 PM MORUIHAI Exp $
 * @version 1.1: 增加字段[金额类型] 2018/10/08
 */
@Service
public class LoanProductBizImpl implements LoanProductBiz {
    @Resource
    private FinanceLoanDetailDAO  financeLoanDetailMapper;
    @Resource
    private FinanceProductMainDAO financeProductMainMapper;

    @Override
    public List<LoanProductListVO> findProductList(Page<FinanceProductMain> financeProductPage) {
        List<LoanProductListVO> loanProductList = new ArrayList<>();
        //根据类型查主表数据
        List<FinanceProductMain> financeProductMainList = financeProductMainMapper
            .selectProductByType(3, financeProductPage);

        if (financeProductMainList != null && financeProductMainList.size() > 0) {
            //遍历主表数据取得id
            List<Long> financeMainIds = new ArrayList<>();
            financeProductMainList
                .forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
            //根据主表ID查询出办卡明细表
            List<FinanceLoanDetail> financeLoanDetails = financeLoanDetailMapper
                .selectByProductId(financeMainIds);
            LoanProductListVO loanProductListVO;
            FinanceLoanDetail financeLoanDetail = null;

            Map<Long, FinanceLoanDetail> ffpdMap = new HashMap<>();
            financeLoanDetails.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

            for (FinanceProductMain fpm : financeProductMainList) {
                financeLoanDetail = ffpdMap.get(fpm.getId());
                loanProductListVO = new LoanProductListVO();

                loanProductListVO.setId(fpm.getId());
                loanProductListVO.setProductName(fpm.getProductName());
                loanProductListVO.setLogoUrl(fpm.getLogoUrl());
                loanProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());
                loanProductList.add(loanProductListVO);
            }

        }

        return loanProductList;
    }

    @Override
    public LoanProductDetailVO findProductDetailByProductId(Long productId) {
        //根据id查主表数据
        FinanceProductMain financeProductMain = financeProductMainMapper
            .selectByPrimaryKey(productId);
        FinanceLoanDetail financeLoanDetail = financeLoanDetailMapper
            .selectProductDetailByProductId(productId);

        LoanProductDetailVO loanProductDetailVO = new LoanProductDetailVO();
        loanProductDetailVO.setId(financeProductMain.getId());
        loanProductDetailVO.setProductName(financeProductMain.getProductName());
        loanProductDetailVO.setMark1(financeLoanDetail.getMark1());
        loanProductDetailVO.setMark2(financeLoanDetail.getMark2());
        loanProductDetailVO.setAmountScope(financeLoanDetail.getAmountScope());
        loanProductDetailVO.setDateScope(financeLoanDetail.getDateScope());
        loanProductDetailVO.setLogoUrl(financeProductMain.getLogoUrl());
        loanProductDetailVO.setTerminalBonus(financeProductMain.getTerminalBonus(),
            financeProductMain.getAmountType());
        loanProductDetailVO.setDirectBonus(financeProductMain.getDirectBonus(),
            financeProductMain.getAmountType());
        loanProductDetailVO.setIndirectBonus(financeProductMain.getIndirectBonus(),
            financeProductMain.getAmountType());
        loanProductDetailVO.setCashbackDate(financeProductMain.getCashbackDate());
        loanProductDetailVO.setRedirectUrl(financeProductMain.getRedirectUrl());

        loanProductDetailVO.setProductDesc(financeProductMain.getProductDesc());
        loanProductDetailVO.setPromotionUrl(financeProductMain.getPromotionUrl());
        // 金额类型
        loanProductDetailVO.setAmountType(String.valueOf(financeProductMain.getAmountType()));

        return loanProductDetailVO;
    }
}
