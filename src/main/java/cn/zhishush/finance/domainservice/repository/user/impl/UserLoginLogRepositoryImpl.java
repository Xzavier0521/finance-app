package cn.zhishush.finance.domainservice.repository.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.log.UserLoginLogDAO;
import cn.zhishush.finance.domain.user.UserLoginLog;
import cn.zhishush.finance.domainservice.converter.user.UserLoginLogConverter;
import cn.zhishush.finance.domainservice.repository.user.UserLoginLogRepository;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserLoginLogRepositoryImpl.java, v 0.1 2018/9/28 上午11:32 lili Exp $
 */
@Repository("userLoginLogRepository")
public class UserLoginLogRepositoryImpl implements UserLoginLogRepository {

    @Resource
    private UserLoginLogDAO financeUserLoginLogMapper;

    @Override
    public List<UserLoginLog> queryLatestLog(List<Long> userIds) {
        return UserLoginLogConverter
            .convert2List(financeUserLoginLogMapper.queryLatestLog(userIds));
    }
}
