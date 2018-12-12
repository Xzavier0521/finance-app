package cn.zhishush.finance.domainservice.service.activity;

import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.core.common.enums.ActivityRewardTypeEnum;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordService.java, v0.1 2018/11/26 2:13 PM PM lili Exp $
 */
public interface ActivityOperationRecordService {

    BasicResponse localData(UserInfo userInfo, String activityCode,
                            ActivityRewardTypeEnum activityRewardType);
}

