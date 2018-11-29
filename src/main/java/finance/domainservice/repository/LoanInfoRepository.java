package finance.domainservice.repository;

import finance.api.model.base.Page;
import finance.domain.loan.LoanInfo;

/**
 * <p>贷款产品信息</p>
 *
 * @author lili
 * @version 1.0: LoanInfoRepository.java, v0.1 2018/11/29 1:42 PM PM lili Exp $
 */
public interface LoanInfoRepository {

    Page<LoanInfo> query(int pageSize, int pageNum);

    LoanInfo query(String productCode);
}
