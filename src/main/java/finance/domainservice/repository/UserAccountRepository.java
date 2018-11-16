package finance.domainservice.repository;

import finance.core.dal.dataobject.FinanceUserAccount;

import java.util.List;

/**
 *  <p>用户账户信息</p>
 * @author  lili
 * @version :1.0  UserAccountRepository.java.java, v 0.1 2018/9/27 下午8:40 lili Exp $
 */
public interface UserAccountRepository {

    /**
     * List<FinanceUserAccount>
     * @param userIds 用户id列表
     * @return List<FinanceUserAccount>
     */
    List<FinanceUserAccount> queryCondition(List<Long> userIds);

}
