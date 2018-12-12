package cn.zhishush.finance.core.dal.dao.user;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.third.RegisterStatisticsDataDO;
import cn.zhishush.finance.core.dal.dataobject.user.UserRegisterChannelInfoDO;

/**
 * <p>用户注册渠道信息</p>
 * 
 * @author lili
 * @version $Id: UserRegisterChannelInfoDAO.java, v0.1 2018/11/14 1:03 PM lili Exp $
 */
public interface UserRegisterChannelInfoDAO extends BaseDAO<UserRegisterChannelInfoDO, Long> {

    int countRegisterNumByChannel(@Param("channelCode") String channelCode);

    List<RegisterStatisticsDataDO> queryStatisticsData(String channelCode);
}