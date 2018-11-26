package finance.domainservice.service.financeproduct.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import finance.core.dal.dataobject.ProductMain;
import org.springframework.stereotype.Service;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.CreditCardProductDetailVO;
import finance.api.model.vo.financeproduct.CreditCardProductListVO;
import finance.domainservice.service.financeproduct.CreditCardProductBiz;
import finance.core.dal.dao.CreditCardDetailDAO;
import finance.core.dal.dao.ProductMainDAO;
import finance.core.dal.dataobject.CreditCardDetailDO;

/**
 * @program: finance-app
 * @description: 信用卡操作BIZ实现层
 * @author: MORUIHAI
 * @create: 2018-07-12 17:27
 **/
@Service
public class CreditCardProductBizImpl implements CreditCardProductBiz {
    @Resource
    private CreditCardDetailDAO financeCreditCardDetailMapper;
    @Resource
    private ProductMainDAO      financeProductMainMapper;

    @Override
    public List<CreditCardProductListVO> findProductList(Page<ProductMain> financeProductPage) {

        List<CreditCardProductListVO> creditCardProductList = new ArrayList<CreditCardProductListVO>();
        // 根据类型查主表数据
        List<ProductMain> productMainList = financeProductMainMapper
            .selectProductByType(2, financeProductPage);

        if (productMainList != null && productMainList.size() > 0) {
            // 遍历主表数据取得id
            List<Long> financeMainIds = new ArrayList<>();
            productMainList
                .forEach(financeProductMain -> financeMainIds.add(financeProductMain.getId()));
            // 根据主表ID查询出办卡明细表
            List<CreditCardDetailDO> creditCardDetailDOS = financeCreditCardDetailMapper
                .selectByProductId(financeMainIds);
            CreditCardProductListVO creditCardProductListVO = null;
            CreditCardDetailDO creditCardDetailDO = null;

            Map<Long, CreditCardDetailDO> ffpdMap = new HashMap<Long, CreditCardDetailDO>();
            creditCardDetailDOS.forEach(ffpd -> ffpdMap.put(ffpd.getProductId(), ffpd));

            for (ProductMain fpm : productMainList) {
                creditCardDetailDO = ffpdMap.get(fpm.getId());
                creditCardProductListVO = new CreditCardProductListVO();

                creditCardProductListVO.setId(fpm.getId());
                creditCardProductListVO.setProductName(fpm.getProductName());
                creditCardProductListVO.setLogoUrl(fpm.getLogoUrl());
                creditCardProductListVO.setTotalBonus(fpm.getTotalBonus(), fpm.getAmountType());
                creditCardProductListVO.setPassRate(creditCardDetailDO.getPassRate());
                creditCardProductListVO.setRebackCashDesc(creditCardDetailDO.getRebackCashDesc());
                creditCardProductListVO.setDirectBonus(fpm.getDirectBonus());
                creditCardProductListVO.setIndirectBonus(fpm.getIndirectBonus());
                creditCardProductList.add(creditCardProductListVO);
            }

        }
        return creditCardProductList;
    }

    @Override
    public CreditCardProductDetailVO findProductDetailByProductId(Long productId) {
        // 根据id查主表数据
        ProductMain productMain = financeProductMainMapper
            .selectByPrimaryKey(productId);
        CreditCardDetailDO creditCardDetailDO = financeCreditCardDetailMapper
            .selectProductDetailByProductId(productId);

        CreditCardProductDetailVO creditCardProductDetailVO = new CreditCardProductDetailVO();
        creditCardProductDetailVO.setId(productMain.getId());
        creditCardProductDetailVO.setProductName(productMain.getProductName());
        creditCardProductDetailVO.setDetailPageUrl(creditCardDetailDO.getDetailPageUrl());
        creditCardProductDetailVO.setAuditLength(creditCardDetailDO.getAuditLength());
        creditCardProductDetailVO.setPassRate(creditCardDetailDO.getPassRate());
        creditCardProductDetailVO.setMaxAmount(creditCardDetailDO.getMaxAmount());
        creditCardProductDetailVO.setTerminalBonus(productMain.getTerminalBonus(),
            productMain.getAmountType());
        creditCardProductDetailVO.setDirectBonus(productMain.getDirectBonus(),
            productMain.getAmountType());
        creditCardProductDetailVO.setIndirectBonus(productMain.getIndirectBonus(),
            productMain.getAmountType());
        creditCardProductDetailVO.setCashbackDate(productMain.getCashbackDate());
        creditCardProductDetailVO.setRedirectUrl(productMain.getRedirectUrl());

        creditCardProductDetailVO.setProductDesc(productMain.getProductDesc());
        creditCardProductDetailVO.setPromotionUrl(productMain.getPromotionUrl());
        creditCardProductDetailVO.setAmountType(String.valueOf(productMain.getAmountType()));

        return creditCardProductDetailVO;
    }
}
