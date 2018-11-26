package finance.domainservice.service.financeproduct;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.vo.financeproduct.RebackCashRuleVO;
import finance.core.dal.dataobject.ProductMain;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-12 19:36
 **/
public interface InsuranceProductBiz {
	/**
	 * 信用卡-我的信用卡页面查询
	 * 
	 * @param financeProductPage
	 * @return
	 */
	public List<RebackCashRuleVO> findProductList(Page<ProductMain> financeProductPage);

}
