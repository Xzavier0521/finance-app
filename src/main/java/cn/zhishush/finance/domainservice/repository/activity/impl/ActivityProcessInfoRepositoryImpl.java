package cn.zhishush.finance.domainservice.repository.activity.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.activity.ActivityProcessInfoDAO;
import cn.zhishush.finance.domain.activity.ActivityDailyInviteInfo;
import cn.zhishush.finance.domain.activity.ActivityProcessInfo;
import cn.zhishush.finance.domainservice.converter.activity.ActivityDailyInviteInfoConverter;
import cn.zhishush.finance.domainservice.converter.activity.ActivityProcessInfoConverter;
import cn.zhishush.finance.domainservice.repository.activity.ActivityProcessInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>活动执行记录</p>
 *
 * @author lili
 * @version 1.0: ActivityProcessInfoRepositoryImpl.java, v0.1 2018/12/18 5:21 PM PM lili Exp $
 */
@Repository("activityProcessInfoRepository")
public class ActivityProcessInfoRepositoryImpl implements ActivityProcessInfoRepository {

    @Resource
    private ActivityProcessInfoDAO activityProcessInfoDAO;

    @Override
    public int save(ActivityProcessInfo activityProcessInfo) {
        return activityProcessInfoDAO
            .insertSelective(ActivityProcessInfoConverter.convert(activityProcessInfo));
    }

    @Override
    public int update(ActivityProcessInfo activityProcessInfo) {
        return activityProcessInfoDAO
            .updateByPrimaryKeySelective(ActivityProcessInfoConverter.convert(activityProcessInfo));
    }

    @Override
    public ActivityProcessInfo query(Long userId, String activityCode) {
        ActivityProcessInfo activityProcessInfo = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("userId", userId);
        parameters.put("activityCode", activityCode);
        List<ActivityProcessInfo> activityProcessInfoList = ActivityProcessInfoConverter
            .convert2List(activityProcessInfoDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(activityProcessInfoList)) {
            activityProcessInfo = activityProcessInfoList.get(0);
        }
        return activityProcessInfo;
    }

    @Override
    public int queryInviteNum(Long userId, String activityCode) {
        return activityProcessInfoDAO.queryInviteNum(userId, activityCode);
    }

    @Override
    public List<ActivityDailyInviteInfo> queryDailyInviteInfo(Long userId, String activityCode) {
        return ActivityDailyInviteInfoConverter
            .convert2List(activityProcessInfoDAO.queryDailyInviteInfo(userId, activityCode));
    }

    @Override
    public int queryDailyInviteNum(String activityCode, String inviteDate) {
        return activityProcessInfoDAO.queryDailyInviteNum(activityCode,inviteDate);
    }
}
