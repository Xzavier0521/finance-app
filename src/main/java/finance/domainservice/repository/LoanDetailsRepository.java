package finance.domainservice.repository;

import finance.domain.loan.LoanDetails;

/**
 * <p>贷款产品明细</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsRepository.java, v0.1 2018/11/29 3:06 PM PM lili Exp $
 */
public interface LoanDetailsRepository {

    LoanDetails query(String productCode);
}
