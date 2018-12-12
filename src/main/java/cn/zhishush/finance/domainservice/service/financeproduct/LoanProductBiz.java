package cn.zhishush.finance.domainservice.service.financeproduct;

import java.util.List;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductListVO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-12 18:28
 **/
public interface LoanProductBiz {
	/**
	 * 信用卡-我的贷款页面查询
	 * 
	 * @param financeProductPage
	 * @return
	 */
	List<LoanProductListVO> findProductList(Page<ProductMain> financeProductPage);

	/**
	 * 理财--我要贷款详情页查询
	 * 
	 * @param productId
	 * @return
	 */
	LoanProductDetailVO findProductDetailByProductId(Long productId);
}
