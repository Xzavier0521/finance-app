package finance.domainservice.service.query;

import java.util.List;

import finance.api.model.vo.product.ProductModuleVO;

/**
 * <p>
 * 产品模块查询
 * </p>
 *
 * @author lili
 * @version 1.0: ProductModuleQueryService.java, v0.1 2018/11/8 5:13 PM PM lili
 *          Exp $
 */
public interface ProductModuleQueryService {

	/**
	 * 查询首页产品模块信息
	 * 
	 * @return List<ProductModuleVO>
	 */
	List<ProductModuleVO> queryAllModule();
}
