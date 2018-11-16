package finance.domainservice.service.finance.tansAccount;

import finance.core.dal.dataobject.FinanceUserAccount;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: TransAccBiz.java, v0.1 2018/11/14 1:54 PM lili Exp $
 */
public interface TransAccBiz {

    /**
     * 根据useid查询账户
     * @param userId
     * @return
     */
    FinanceUserAccount getAccountByUserId(Long userId);
}
