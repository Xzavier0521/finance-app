package cn.zhishush.finance.api.model.vo.product;

import java.io.Serializable;
import java.util.List;

import cn.zhishush.finance.domain.product.ProductDetail;
import cn.zhishush.finance.domain.product.ProductModule;
import lombok.Data;

/**
 * <p>
 * 产品模块
 * </p>
 *
 * @author lili
 * @version 1.0: ProductModuleVO.java, v0.1 2018/11/8 3:28 PM PM lili Exp $
 */
@Data
public class ProductModuleVO implements Serializable {

	private static final long serialVersionUID = -1806617837423531446L;

	/**
	 * 产品模块
	 */
	private ProductModule productModule;

	/**
	 * 产品明细
	 */
	private List<ProductDetail> productDetails;

}
