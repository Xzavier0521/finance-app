package finance.domainservice.repository.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import finance.core.dal.dao.FinanceUserAccountDAO;
import finance.core.dal.dataobject.FinanceUserAccount;
import finance.domainservice.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 *  <p>用户账户信息</p>
 * @author  lili
 * @version :1.0  UserAccountRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:41 lili Exp $
 */
@Slf4j
@Repository("userAccountRepository")
public class UserAccountRepositoryImpl  implements UserAccountRepository {

    @Resource
    private FinanceUserAccountDAO financeUserAccountMapper;
    @Override
    public List<FinanceUserAccount> queryCondition(List<Long> userIds) {
        List<FinanceUserAccount> financeUserAccountList = Lists.newArrayList();
        Map<String, Object> parameters = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(userIds)){
            parameters.put("userIds",userIds);
            financeUserAccountMapper.query(parameters);
        }
        return financeUserAccountList;
    }
}
