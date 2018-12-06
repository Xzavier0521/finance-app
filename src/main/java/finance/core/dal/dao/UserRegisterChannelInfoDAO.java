package finance.core.dal.dao;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.UserRegisterChannelInfoDO;

/**
 * <p>用户注册渠道信息</p>
 * 
 * @author lili
 * @version $Id: UserRegisterChannelInfoDAO.java, v0.1 2018/11/14 1:03 PM lili Exp $
 */
public interface UserRegisterChannelInfoDAO extends BaseDAO<UserRegisterChannelInfoDO, Long> {

    int countRegisterNumByChannel(@Param("channelCode") String channelCode);
}