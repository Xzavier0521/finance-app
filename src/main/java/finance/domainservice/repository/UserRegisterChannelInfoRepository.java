package finance.domainservice.repository;

import java.util.List;

import finance.api.model.vo.third.RegisterStatisticsData;

/**
 * <p>用户注册渠道信息</p>
 *
 * @author lili
 * @version 1.0: UserRegisterChannelInfoRepository.java, v0.1 2018/12/5 6:54 PM PM lili Exp $
 */
public interface UserRegisterChannelInfoRepository {

    /**
     * 分渠道查询注册用户数
     * @param channelCode 渠道代码
     * @return int
     */
    int countRegisterNumByChannel(String channelCode);

    /**
     * 查询每日注册用户数
     * @param channelCode 渠道代码
     * @return List<RegisterStatisticsData>
     */
    List<RegisterStatisticsData> queryStatisticsData(String channelCode);

}
