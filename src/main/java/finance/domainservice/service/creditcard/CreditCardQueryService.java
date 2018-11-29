package finance.domainservice.service.creditcard;

import finance.api.model.base.Page;
import finance.api.model.vo.creditCard.CreditCardApplyInfoVO;
import finance.api.model.vo.creditCard.CreditCardDetailVO;

/**
 * <p>信用卡明细查询</p>
 *
 * @author lili
 * @version 1.0: CreditCardQueryService.java, v0.1 2018/11/29 1:42 AM PM lili Exp $
 */
public interface CreditCardQueryService {

    CreditCardDetailVO queryCreditCardDetail(String cardCode);

    Page<CreditCardApplyInfoVO> queryApplyInfo(Long userId, int pageSize, int pageNum);
}
