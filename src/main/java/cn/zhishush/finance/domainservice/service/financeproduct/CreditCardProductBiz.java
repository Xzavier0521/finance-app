package cn.zhishush.finance.domainservice.service.financeproduct;

import java.util.List;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeproduct.CreditCardProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.CreditCardProductListVO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-12 17:26
 **/
public interface CreditCardProductBiz {
	/**
	 * 信用卡-我的信用卡页面查询
	 * 
	 * @param financeProductPage
	 * @return
	 */
	public List<CreditCardProductListVO> findProductList(Page<ProductMain> financeProductPage);

	/**
	 * 理财--我要办卡详情页查询
	 * 
	 * @param productId
	 * @return
	 */
	public CreditCardProductDetailVO findProductDetailByProductId(Long productId);
}
