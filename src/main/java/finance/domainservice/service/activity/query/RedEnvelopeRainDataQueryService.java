package finance.domainservice.service.activity.query;

import java.util.List;

import finance.api.model.base.Page;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.activity.UserRedEnvelopeRainInfo;
import finance.domain.activity.UserRedEnvelopeRainSummaryData;
import finance.domain.user.UserInfo;

/**
 * <p>红包雨活动数据查询</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataQueryService.java, v0.1 2018/11/14 10:56 PM PM lili Exp $
 */
public interface RedEnvelopeRainDataQueryService {

    /**
     *  红包雨活动数据查询
     * @param userId 用户id
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @return UserRedEnvelopeRainSummaryData
     */
    UserRedEnvelopeRainSummaryData querySummaryData(Long userId, String activityCode,
                                                    Integer activityDay);

    /**
     * 分页查询红包雨活动数据
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @param activityCode 活动代码
     * @param activityDay  活动日期
     * @param timeCode 时间代码
     * @return Page<RedEnvelopeRainData>
     */
    Page<RedEnvelopeRainData> query4Page(Integer pageSize, Long pageNum, String activityCode,
                                         Integer activityDay, RedEnvelopeRainTimeCodeEnum timeCode);

    /**
     *  分页查询红包雨活动数据
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @return Page<RedEnvelopeRainData>
     */
    Page<RedEnvelopeRainData> queryDailyRainData4Page(Integer pageSize, Long pageNum,
                                                      String activityCode, Integer activityDay);

    /**
     *  查询用户当前排行榜
     * @param activityCode  活动代码
     * @param userInfo 用户id
     * @return String
     */
    String queryUserCurrentRanking(String activityCode, UserInfo userInfo);

    /**
     * 排行榜查询
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @return List<RedEnvelopeRainData>
     */
    List<RedEnvelopeRainData> queryRankingList(String activityCode, Integer activityDay,
                                               int pageSize, int pageNum);

    /**
     *  查询用户活动数据
     * @param userInfo 用户信息
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @return UserRedEnvelopeRainInfo
     */
    UserRedEnvelopeRainInfo queryUserRedEnvelopeRainInfo(UserInfo userInfo, String activityCode,
                                                         Integer activityDay);

}
