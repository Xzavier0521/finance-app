package finance.domainservice.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import finance.api.model.vo.third.RegisterStatisticsData;
import finance.core.dal.dao.UserRegisterChannelInfoDAO;
import finance.domainservice.converter.RegisterStatisticsDataConverter;

/**
 * <p>用户注册渠道信息</p>
 *
 * @author lili
 * @version 1.0: UserRegisterChannelInfoRepositoryImpl.java, v0.1 2018/12/5 6:55 PM PM lili Exp $
 */
@Repository("userRegisterChannelInfoRepository")
public class UserRegisterChannelInfoRepositoryImpl implements UserRegisterChannelInfoRepository {

    @Resource
    private UserRegisterChannelInfoDAO userRegisterChannelInfoDAO;

    @Override
    public int countRegisterNumByChannel(String channelCode) {
        return userRegisterChannelInfoDAO.countRegisterNumByChannel(channelCode);
    }

    @Override
    public List<RegisterStatisticsData> queryStatisticsData(String channelCode) {
        return RegisterStatisticsDataConverter
            .convert2List(userRegisterChannelInfoDAO.queryStatisticsData(channelCode));
    }
}
