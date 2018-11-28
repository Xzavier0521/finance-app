package finance.domainservice.repository;

import finance.domain.creditcard.CreditCardDetails;

/**
 * <p>信用卡明细</p>
 *
 * @author lili
 * @version 1.0: CreditCardDetailsRepository.java, v0.1 2018/11/29 1:33 AM PM lili Exp $
 */
public interface CreditCardDetailsRepository {

    CreditCardDetails query(String cardCode);
}
