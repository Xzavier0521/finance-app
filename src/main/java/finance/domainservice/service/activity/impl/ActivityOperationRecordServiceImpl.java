package finance.domainservice.service.activity.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import finance.api.model.response.BasicResponse;
import finance.core.common.enums.ActivityRewardTypeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseUtils;
import finance.domain.activity.ActivityOperationRecord;
import finance.domain.user.UserInfo;
import finance.domainservice.repository.ActivityOperationRecordRepository;
import finance.domainservice.service.activity.ActivityOperationRecordService;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordServiceImpl.java, v0.1 2018/11/26 2:17 PM PM lili Exp $
 */
@Service("activityOperationRecordService")
public class ActivityOperationRecordServiceImpl implements ActivityOperationRecordService {

    @Resource
    private ActivityOperationRecordRepository activityOperationRecordRepository;

    @Override
    public BasicResponse localData(UserInfo userInfo, String activityCode,
                                   ActivityRewardTypeEnum activityRewardType) {
        BasicResponse response = ResponseUtils.buildResp(ReturnCode.SUCCESS);
        if (ActivityRewardTypeEnum.THIRD_REWARD == activityRewardType) {
            ActivityOperationRecord record = activityOperationRecordRepository.query(activityCode,
                userInfo.getId(), activityRewardType);
            PreconditionUtils.checkArgument(Objects.nonNull(record),
                ReturnCode.UNFINISHED_SECOND_REWARD_ACTIVITY);
        }
        ActivityOperationRecord activityOperationRecord = ActivityOperationRecord.builder()
            .activityCode(activityCode).userId(userInfo.getId()).mobileNum(userInfo.getMobileNum())
            .rewardType(activityRewardType).build();
        activityOperationRecordRepository.save(activityOperationRecord);
        return response;
    }
}
