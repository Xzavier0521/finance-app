package cn.zhishush.finance.domainservice.repository.activity;

import java.util.List;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainData;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataRepository.java, v0.1 2018/11/14 10:00 PM lili Exp $
 */
public interface RedEnvelopeRainDataRepository {

    /**
     * 保存
     * @param redEnvelopeRainData 记录
     * @return int
     */
    int save(RedEnvelopeRainData redEnvelopeRainData);

    /**
     * 查询用户当日红包雨活动数据
     * @param userId 用户id
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @return RedEnvelopeRainData
     */
    RedEnvelopeRainData queryTodayData(Long userId, String activityCode, Integer activityDay);

    /**
     * 查询用户历史红包雨活动数据
     * @param userId 用户id
     * @param activityCode 活动代码
     * @return RedEnvelopeRainData
     */
    RedEnvelopeRainData queryHistoryData(Long userId, String activityCode);

    /**
     * 分页查询
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param timeCode 时间代码
     * @return Page<RedEnvelopeRainData>
     */
    Page<RedEnvelopeRainData> query4Page(Integer pageSize, Long pageNum, String activityCode,
                                         Integer activityDay, RedEnvelopeRainTimeCodeEnum timeCode);

    /**
     * 查询
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param userId 用户id 	
     * @return RedEnvelopeRainData
     */
    RedEnvelopeRainData query(String activityCode, Integer activityDay, Long userId);

    /**
     * 查询
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param userId 用户id
     * @param timeCodes 时间代码
     * @return Page<RedEnvelopeRainData>
     */
    RedEnvelopeRainData query(String activityCode, Integer activityDay, Long userId,
                              RedEnvelopeRainTimeCodeEnum timeCodes);

    /**
     * 分页查询
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @return Page<RedEnvelopeRainData>
     */
    Page<RedEnvelopeRainData> queryDailyRainData4Page(Integer pageSize, Long pageNum,
                                                      String activityCode, Integer activityDay);

    /**
     * 排行榜查询
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param timeCode 时间编码
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @return List<RedEnvelopeRainData>
     */
    List<RedEnvelopeRainData> queryRankingList(String activityCode, Integer activityDay,
                                               RedEnvelopeRainTimeCodeEnum timeCode, int pageSize,
                                               int pageNum);
}
