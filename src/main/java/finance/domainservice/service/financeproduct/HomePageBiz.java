package finance.domainservice.service.financeproduct;

import finance.api.model.base.Page;
import finance.api.model.vo.productmainpage.ProductMainpageVO;

import java.util.List;

/**
 * @program: finance-app
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-07-13 09:22
 **/
public interface HomePageBiz {
	List<ProductMainpageVO> findHomePageList(Long productType, Page<ProductMainpageVO> page);
}
