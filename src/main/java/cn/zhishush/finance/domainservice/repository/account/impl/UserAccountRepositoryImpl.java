package cn.zhishush.finance.domainservice.repository.account.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.zhishush.finance.core.dal.dao.account.UserAccountDAO;
import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;
import cn.zhishush.finance.domainservice.repository.account.UserAccountRepository;

/**
 * <p>用户账户信息</p>
 * 
 * @author lili
 * @version :1.0 UserAccountRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:41  lili Exp $
 */
@Slf4j
@Repository("userAccountRepository")
public class UserAccountRepositoryImpl implements UserAccountRepository {

    @Resource
    private UserAccountDAO financeUserAccountMapper;

    @Override
    public List<UserAccountDO> queryCondition(List<Long> userIds) {
        List<UserAccountDO> userAccountDOList = Lists.newArrayList();
        Map<String, Object> parameters = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(userIds)) {
            parameters.put("userIds", userIds);
            financeUserAccountMapper.query(parameters);
        }
        return userAccountDOList;
    }
}
