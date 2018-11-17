package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.api.model.base.Page;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.dal.dao.RedEnvelopeRainDataDAO;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domainservice.converter.RedEnvelopeRainDataConverter;
import finance.domainservice.repository.RedEnvelopeRainDataRepository;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataRepositoryImpl.java, v0.1 2018/11/14 10:07 PM PM lili Exp $
 */
@Slf4j
@Repository("redEnvelopeRainDataRepositoryImpl")
public class RedEnvelopeRainDataRepositoryImpl implements RedEnvelopeRainDataRepository {

    @Resource
    private RedEnvelopeRainDataDAO redEnvelopeRainDataDAO;

    /**
     * 保存
     *
     * @param redEnvelopeRainData 记录
     * @return int
     */
    @Override
    public int save(RedEnvelopeRainData redEnvelopeRainData) {
        return redEnvelopeRainDataDAO
            .insertSelective(RedEnvelopeRainDataConverter.convert(redEnvelopeRainData));
    }

    /**
     *  查询当日红包雨活动数据
     * @param userId 用户id
     * @param activityDay 活动日期
     * @return  RedEnvelopeRainData
     */
    @Override
    public RedEnvelopeRainData queryTodayData(Long userId, String activityCode,
                                              Integer activityDay) {
        return RedEnvelopeRainDataConverter
            .convert(redEnvelopeRainDataDAO.queryTodayData(userId, activityCode, activityDay));
    }

    /**
     * 查询用户历史红包雨活动数据
     *
     * @param userId 用户id
     * @return RedEnvelopeRainData
     */
    @Override
    public RedEnvelopeRainData queryHistoryData(Long userId, String activityCode) {
        return RedEnvelopeRainDataConverter
            .convert(redEnvelopeRainDataDAO.queryHistoryData(userId, activityCode));
    }

    /**
     * @param pageSize 每页记录数
     * @param pageNum 第几页
     * @param activityDay  活动日期
     * @param timeCode 时间代码
     * @return Page<RedEnvelopeRainData>
     */
    @Override
    public Page<RedEnvelopeRainData> query4Page(Integer pageSize, Long pageNum, String activityCode,
                                                Integer activityDay,
                                                RedEnvelopeRainTimeCodeEnum timeCode) {
        Page<RedEnvelopeRainData> page = new Page<>(pageSize, pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        if (Objects.nonNull(activityDay)) {
            parameters.put("activityDay", activityDay);
        }
        if (Objects.nonNull(timeCode)) {
            parameters.put("timeCode", timeCode.getCode());
        }
        long count = redEnvelopeRainDataDAO.count(parameters);
        page.setTotalCount(count);
        if (count > 0) {
            parameters.put("page", page);
            page.setDataList(RedEnvelopeRainDataConverter
                .convert2List(redEnvelopeRainDataDAO.query(parameters)));
        }
        return page;
    }

    @Override
    public Page<RedEnvelopeRainData> queryDailyRainData4Page(Integer pageSize, Long pageNum,
                                                             String activityCode,
                                                             Integer activityDay) {
        Page<RedEnvelopeRainData> page = new Page<>(pageSize, pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("activityDay", activityDay);
        long count = redEnvelopeRainDataDAO.countDailyRainData(parameters);
        page.setTotalCount(count);
        if (count > 0) {
            parameters.put("page", page);
            page.setDataList(RedEnvelopeRainDataConverter
                .convert2List(redEnvelopeRainDataDAO.queryDailyRainData(parameters)));
        }
        return page;
    }

    @Override
    public RedEnvelopeRainData query(String activityCode, Integer activityDay, Long userId) {
        RedEnvelopeRainData redEnvelopeRainData = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("activityDay", activityDay);
        parameters.put("userId", userId);
        List<RedEnvelopeRainData> redEnvelopeRainDataList = RedEnvelopeRainDataConverter
            .convert2List(redEnvelopeRainDataDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(redEnvelopeRainDataList)) {
            redEnvelopeRainData = redEnvelopeRainDataList.get(0);
        }
        return redEnvelopeRainData;
    }

    @Override
    public RedEnvelopeRainData query(String activityCode, Integer activityDay, Long userId,
                                     RedEnvelopeRainTimeCodeEnum timeCode) {

        RedEnvelopeRainData redEnvelopeRainData = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("activityDay", activityDay);
        parameters.put("userId", userId);
        parameters.put("timeCode", timeCode.getCode());
        List<RedEnvelopeRainData> redEnvelopeRainDataList = RedEnvelopeRainDataConverter
            .convert2List(redEnvelopeRainDataDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(redEnvelopeRainDataList)) {
            redEnvelopeRainData = redEnvelopeRainDataList.get(0);
        }
        return redEnvelopeRainData;
    }

    @Override
    public List<RedEnvelopeRainData> queryRankingList(String activityCode, Integer activityDay,
                                                      int pageSize, int pageNum) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("activityDay", activityDay);
        Page<RedEnvelopeRainData> page = new Page<>(pageSize, (long) pageNum);
        parameters.put("page", page);
        return RedEnvelopeRainDataConverter
            .convert2List(redEnvelopeRainDataDAO.queryRankingList(parameters));
    }

}
