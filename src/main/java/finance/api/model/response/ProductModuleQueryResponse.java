package finance.api.model.response;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import finance.api.model.vo.product.ProductModuleVO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ProductModuleQueryResponse.java, v0.1 2018/11/8 5:05 PM PM lili Exp $
 */
@Data
public class ProductModuleQueryResponse implements Serializable {

    private static final long     serialVersionUID = -5671827976099864413L;
    private List<ProductModuleVO> items;

}
