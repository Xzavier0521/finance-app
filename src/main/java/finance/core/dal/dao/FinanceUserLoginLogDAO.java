package finance.core.dal.dao;

import finance.core.dal.dataobject.FinanceUserLoginLog;

import java.util.List;
/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceUserLoginLogDAO.java.java, v 0.1 2018/9/28 下午1:41 lili Exp $
 */
public interface FinanceUserLoginLogDAO extends BaseDAO<FinanceUserLoginLog, Long> {


    /**
     * 查询用户的最新日志
     *
     * @param userIds 用户id列表
     * @return List<UserLoginLog>
     */
    List<FinanceUserLoginLog> queryLatestLog(List<Long> userIds);
}