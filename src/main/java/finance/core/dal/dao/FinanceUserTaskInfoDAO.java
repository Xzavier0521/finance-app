package finance.core.dal.dao;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceUserTaskInfo;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserTaskInfoDAO.java, v0.1 2018/11/14 1:04 PM lili Exp $
 */
public interface FinanceUserTaskInfoDAO extends BaseDAO<FinanceUserTaskInfo, Long> {
    /**
      *功能描述:查询用户任务表
      * @author: moruihai
      * @date: 2018/8/27 17:01
      * @param: [userId, taskId]
      * @return: finance.core.dal.dataobject.FinanceUserTaskInfo
      */
    FinanceUserTaskInfo selectByUserIdAndTaskId(@Param("userId") Long userId, @Param("taskId") Long taskId);

}