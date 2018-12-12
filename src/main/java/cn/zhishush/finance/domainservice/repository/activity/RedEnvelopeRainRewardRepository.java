package cn.zhishush.finance.domainservice.repository.activity;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.common.enums.RewardTypeEnum;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainReward;

/**
 * <p>红包雨活动金币奖励日志</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRewardRepository.java, v0.1 2018/11/18 3:07 PM lili Exp $
 */
public interface RedEnvelopeRainRewardRepository {

    /**
     * 保存
     * @param redEnvelopeRainReward 记录
     * @return int
     */
    int save(RedEnvelopeRainReward redEnvelopeRainReward);

    /**
     * 查询
     * @param activityCode 活动代码
     * @param activityDay 活动日期
     * @param timeCode 时间代码
     * @param userId 用户id
     * @param rewardType 奖励类型
     * @return RewardTypeEnum
     */
    RedEnvelopeRainReward queryByCondition(String activityCode, String activityDay,
                                           RedEnvelopeRainTimeCodeEnum timeCode, Long userId,
                                           RewardTypeEnum rewardType);
}
