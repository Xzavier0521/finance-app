package finance.domainservice.repository;

import finance.domain.user.UserLoginLog;

import java.util.List;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: UserLoginLogRepository.java, v 0.1 2018/9/28 上午11:29 lili Exp $
 */
public interface UserLoginLogRepository {

	/**
	 * 查询用户的最新日志
	 *
	 * @param userIds
	 *            用户id列表
	 * @return List<UserLoginLog>
	 */
	List<UserLoginLog> queryLatestLog(List<Long> userIds);

}
