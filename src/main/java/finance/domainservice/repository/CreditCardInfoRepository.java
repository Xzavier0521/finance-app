package finance.domainservice.repository;

import finance.api.model.base.Page;
import finance.domain.creditcard.CreditCardInfo;

/**
 * <p>信用卡信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardInfoRepository.java, v0.1 2018/11/29 12:58 AM PM lili Exp $
 */
public interface CreditCardInfoRepository {

    Page<CreditCardInfo> query(int pageSize, int pageNum,String cardCode,String bankCode);

    CreditCardInfo query(String cardCode);
}
