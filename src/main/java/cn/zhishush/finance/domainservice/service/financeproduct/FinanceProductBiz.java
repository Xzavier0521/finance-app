package cn.zhishush.finance.domainservice.service.financeproduct;

import java.util.List;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeproduct.FinancingProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.FinancingProductListVO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * @program: finance-app
 *
 * @description: 理财产品类接口BIZ
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-12 09:57
 **/
public interface FinanceProductBiz {
	/**
	 * 理财-我的理财页面查询
	 * 
	 * @param financeProductPage
	 * @return
	 */
	public List<FinancingProductListVO> findProductList(Page<ProductMain> financeProductPage);

	/**
	 * 理财--我要理财详情页查询
	 * 
	 * @param productId
	 * @return
	 */
	public FinancingProductDetailVO findProductDetailByProductId(Long productId);
}
