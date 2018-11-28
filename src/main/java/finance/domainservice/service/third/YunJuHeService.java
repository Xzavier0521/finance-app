package finance.domainservice.service.third;

import finance.api.model.response.BasicResponse;
import finance.domain.product.ProductInfo;
import finance.domain.user.UserInfo;

/**
 * <p>云聚合联合登陆</p>
 *
 * @author lili
 * @version 1.0: YunJuHeService.java, v0.1 2018/11/28 7:59 PM PM lili Exp $
 */
public interface YunJuHeService {

    BasicResponse unionLogin(UserInfo userInfo, String realName, ProductInfo productInfo);
}
