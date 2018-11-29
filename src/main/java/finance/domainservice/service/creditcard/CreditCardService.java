package finance.domainservice.service.creditcard;

import finance.api.model.response.BasicResponse;
import finance.domain.user.UserInfo;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CreditCardService.java, v0.1 2018/11/29 5:42 PM PM lili Exp $
 */
public interface CreditCardService {

    BasicResponse saveApplyInfo(UserInfo userInfo, String productCode);
}
