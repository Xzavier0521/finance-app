package finance.domainservice.service.financeproduct;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.LoanProductDetailVO;
import finance.api.model.vo.financeproduct.LoanProductListVO;
import finance.model.po.FinanceProductMain;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-12 18:28
 **/
public interface LoanProductBiz {
    /**
     * 信用卡-我的贷款页面查询
     * @param financeProductPage
     * @return
     */
    List<LoanProductListVO> findProductList(Page<FinanceProductMain> financeProductPage);

    /**
     * 理财--我要贷款详情页查询
     * @param productId
     * @return
     */
    LoanProductDetailVO findProductDetailByProductId(Long productId);
}
