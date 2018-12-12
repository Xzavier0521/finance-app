package cn.zhishush.finance.domainservice.repository.third.impl.product;

import cn.zhishush.finance.domain.loan.LoanApplyInfo;
import cn.zhishush.finance.api.model.base.Page;

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
