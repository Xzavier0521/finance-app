package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.common.enums.ActivityRewardTypeEnum;
import finance.core.dal.dao.ActivityOperationRecordDAO;
import finance.domain.activity.ActivityOperationRecord;
import finance.domainservice.converter.ActivityOperationRecordConverter;
import finance.domainservice.repository.ActivityOperationRecordRepository;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordRepositoryImpl.java, v0.1 2018/11/26 2:04 PM PM lili Exp $
 */
@Repository("activityOperationRecordRepository")
public class ActivityOperationRecordRepositoryImpl implements ActivityOperationRecordRepository {

    @Resource
    private ActivityOperationRecordDAO activityOperationRecordDAO;

    @Override
    public int save(ActivityOperationRecord activityOperationRecord) {
        return activityOperationRecordDAO
            .insertSelective(ActivityOperationRecordConverter.convert(activityOperationRecord));
    }

    @Override
    public ActivityOperationRecord query(String activityCode, Long userId,
                                         ActivityRewardTypeEnum rewardType) {
        ActivityOperationRecord activityOperationRecord = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("userId", userId);
        if (Objects.nonNull(rewardType)) {
            parameters.put("rewardType", rewardType.getCode());
        }
        List<ActivityOperationRecord> activityOperationRecords = ActivityOperationRecordConverter
            .convert2List(activityOperationRecordDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(activityOperationRecords)) {
            activityOperationRecord = activityOperationRecords.get(0);
        }
        return activityOperationRecord;
    }
}
