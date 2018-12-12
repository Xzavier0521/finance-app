package cn.zhishush.finance.core.dal.dao.log;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.user.UserLoginLogDO;

import java.util.List;
/**
 * <p>注释</p>
 * 
 * @author lili
 * @version :1.0 UserLoginLogDAO.java.java, v 0.1 2018/9/28 下午1:41 lili Exp $
 */
public interface UserLoginLogDAO extends BaseDAO<UserLoginLogDO, Long> {

	/**
	 * 查询用户的最新日志
	 *
	 * @param userIds
	 *            用户id列表
	 * @return List<UserLoginLog>
	 */
	List<UserLoginLogDO> queryLatestLog(List<Long> userIds);
}