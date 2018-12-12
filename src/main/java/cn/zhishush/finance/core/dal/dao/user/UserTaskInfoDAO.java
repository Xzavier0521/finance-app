package cn.zhishush.finance.core.dal.dao.user;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.user.UserTaskInfoDO;

/**
 * <p>用户任务表</p>
 * 
 * @author lili
 * @version $Id: UserTaskInfoDAO.java, v0.1 2018/11/14 1:04 PM lili Exp $
 */
public interface UserTaskInfoDAO extends BaseDAO<UserTaskInfoDO, Long> {

    /**
     * 查询用户任务表
     * @param userId 用户id
     * @param taskId 任务id
     * @return 查询用户任务表
     */
    UserTaskInfoDO selectByUserIdAndTaskId(@Param("userId") Long userId,
                                           @Param("taskId") Long taskId);

}