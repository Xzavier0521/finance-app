package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.activity.ActivityProcessInfoDO;

/**
 * <p>活动执行记录</p>
 * @author lili
 * @version 1.0: ActivityProcessInfoDAO.java, v0.1 2018/12/18 3:58 PM lili Exp $
 */
public interface ActivityProcessInfoDAO {

    int insertSelective(ActivityProcessInfoDO record);

    List<ActivityProcessInfoDO> query(Map parameters);

    int count(Map parameters);

    ActivityProcessInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityProcessInfoDO record);

    int queryInviteNum(@Param("userId") Long userId, @Param("activityCode") String activityCode);

}