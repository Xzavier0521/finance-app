package finance.domainservice.repository;

import finance.api.model.base.Page;
import finance.domain.loan.LoanApplyInfo;

/**
 * <p>贷款申请记录</p>
 *
 * @author lili
 * @version 1.0: LoanApplyInfoRepository.java, v0.1 2018/11/29 3:34 AM PM lili Exp $
 */
public interface LoanApplyInfoRepository {

    int save(LoanApplyInfo loanApplyInfo);

    Page<LoanApplyInfo> query(Long userId, int pageSize, int pageNum);
}
