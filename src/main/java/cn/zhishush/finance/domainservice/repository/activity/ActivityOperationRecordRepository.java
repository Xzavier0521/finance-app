package cn.zhishush.finance.domainservice.repository.activity;

import cn.zhishush.finance.core.common.enums.ActivityRewardTypeEnum;
import cn.zhishush.finance.domain.activity.ActivityOperationRecord;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordRepository.java, v0.1 2018/11/26 2:02 PM PM lili Exp $
 */
public interface ActivityOperationRecordRepository {

    /**
     * 保存
     * @param activityOperationRecord 记录
     * @return int
     */
    int save(ActivityOperationRecord activityOperationRecord);

    /**
     *  查询操作记录
     * @param activityCode  活动代码
     * @param userId 用户id
     * @param rewardType 奖励类型
     * @return ActivityOperationRecord
     */
    ActivityOperationRecord query(String activityCode, Long userId,
                                  ActivityRewardTypeEnum rewardType);
}
