package cn.zhishush.finance.domainservice.repository.third.impl.product;

import cn.zhishush.finance.domain.loan.LoanDetails;

import java.util.List;

/**
 * <p>贷款产品明细</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsRepository.java, v0.1 2018/11/29 3:06 PM PM lili Exp $
 */
public interface LoanDetailsRepository {

    LoanDetails query(String productCode);

    List<LoanDetails> query();
}
