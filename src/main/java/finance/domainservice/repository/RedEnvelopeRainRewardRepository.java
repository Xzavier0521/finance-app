package finance.domainservice.repository;

import finance.core.common.enums.RewardTypeEnum;
import finance.domain.activity.RedEnvelopeRainReward;

/**
 * <p>红包雨活动金币奖励日志</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRewardRepository.java, v0.1 2018/11/18 3:07 PM PM lili Exp $
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
     * @param userId 用户id
     * @param rewardType 奖励类型
     * @return  RewardTypeEnum
     */
    RedEnvelopeRainReward queryByCondition(String activityCode, String activityDay, Long userId,
                                           RewardTypeEnum rewardType);
}
