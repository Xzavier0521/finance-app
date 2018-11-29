package finance.domainservice.repository;

import finance.api.model.base.Page;
import finance.domain.creditcard.BankInfo;

/**
 * <p>银行信息</p>
 *
 * @author lili
 * @version 1.0: BankInfoRepository.java, v0.1 2018/11/28 11:36 PM PM lili Exp $
 */
public interface BankInfoRepository {

    Page<BankInfo> query(int pageSize, int pageNum);

    BankInfo query(String bankCode);
}
