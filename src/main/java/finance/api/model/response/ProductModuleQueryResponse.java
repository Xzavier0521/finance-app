package finance.api.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import finance.api.model.vo.product.ProductModuleVO;

/**
 * <p> 产品模块查询</p>
 * @author lili
 * @version 1.0: ProductModuleQueryResponse.java, v0.1 2018/11/26 7:04 PM lili Exp $
 */
@Data
public class ProductModuleQueryResponse implements Serializable {

	private static final long serialVersionUID = -5671827976099864413L;
	private List<ProductModuleVO> items;

}
