package cn.zhishush.finance.domainservice.repository.user;

import java.util.List;

import cn.zhishush.finance.domain.user.UserLoginLog;

/**
 * <p>用户登陆日志</p>
 *
 * @author lili
 * @version 1.0: UserLoginLogRepository.java, v 0.1 2018/9/28 上午11:29 lili Exp $
 */
public interface UserLoginLogRepository {

    /**
     * 查询用户的最新日志
     * @param userIds 用户id列表
     * @return List<UserLoginLog>
     */
    List<UserLoginLog> queryLatestLog(List<Long> userIds);

}
