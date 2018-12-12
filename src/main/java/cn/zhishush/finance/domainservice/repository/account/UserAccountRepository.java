package cn.zhishush.finance.domainservice.repository.account;

import java.util.List;

import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;

/**
 * <p>用户账户信息</p>
 * 
 * @author lili
 * @version :1.0 UserAccountRepository.java.java, v 0.1 2018/9/27 下午8:40 lili
 *          Exp $
 */
public interface UserAccountRepository {

    /**
     * List<UserAccountDO>
     * @param userIds 用户id列表
     * @return List<UserAccountDO>
     */
    List<UserAccountDO> queryCondition(List<Long> userIds);

}
