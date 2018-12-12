package cn.zhishush.finance.domainservice.repository.user.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.user.UserRegisterChannelInfoDAO;
import cn.zhishush.finance.domainservice.repository.user.UserRegisterChannelInfoRepository;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.api.model.vo.third.RegisterStatisticsData;
import cn.zhishush.finance.domainservice.converter.third.RegisterStatisticsDataConverter;

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
