package finance.domainservice.service.financeproduct;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.CreditCardProductDetailVO;
import finance.api.model.vo.financeproduct.CreditCardProductListVO;
import finance.core.dal.dataobject.FinanceProductMain;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-12 17:26
 **/
public interface CreditCardProductBiz {
    /**
     * 信用卡-我的信用卡页面查询
     * @param financeProductPage
     * @return
     */
    public List<CreditCardProductListVO> findProductList(Page<FinanceProductMain> financeProductPage);

    /**
     * 理财--我要办卡详情页查询
     * @param productId
     * @return
     */
    public CreditCardProductDetailVO findProductDetailByProductId(Long productId);
}
