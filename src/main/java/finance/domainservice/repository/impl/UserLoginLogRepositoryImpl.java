package finance.domainservice.repository.impl;

import finance.domain.user.UserLoginLog;
import finance.domainservice.converter.UserLoginLogConverter;
import finance.domainservice.repository.UserLoginLogRepository;
import finance.core.dal.dao.FinanceUserLoginLogDAO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserLoginLogRepositoryImpl.java, v 0.1 2018/9/28 上午11:32 lili Exp $
 */
@Repository("userLoginLogRepository")
public class UserLoginLogRepositoryImpl implements UserLoginLogRepository
{

    @Resource
    private FinanceUserLoginLogDAO financeUserLoginLogMapper;
    /**
     * 查询用户的最新日志
     *
     * @param userIds 用户id列表
     * @return List<UserLoginLog>
     */
    @Override
    public List<UserLoginLog> queryLatestLog(List<Long> userIds) {
        return UserLoginLogConverter.convert2List(financeUserLoginLogMapper.queryLatestLog(userIds));
    }
}
