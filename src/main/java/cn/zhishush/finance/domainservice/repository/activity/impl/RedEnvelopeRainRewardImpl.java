package cn.zhishush.finance.domainservice.repository.activity.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.common.enums.RewardTypeEnum;
import cn.zhishush.finance.core.dal.dao.activity.RedEnvelopeRainRewardDAO;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainReward;
import cn.zhishush.finance.domainservice.converter.activity.RedEnvelopeRainRewardConverter;
import cn.zhishush.finance.domainservice.repository.activity.RedEnvelopeRainRewardRepository;

/**
 * <p>红包雨活动金币奖励日志</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRewardImpl.java, v0.1 2018/11/18 3:07 PM PM lili Exp $  
 */
@Repository("redEnvelopeRainRewardRepository")
public class RedEnvelopeRainRewardImpl implements RedEnvelopeRainRewardRepository {

    @Resource
    private RedEnvelopeRainRewardDAO redEnvelopeRainRewardDAO;

    @Override
    public int save(RedEnvelopeRainReward redEnvelopeRainReward) {
        return redEnvelopeRainRewardDAO
            .insertSelective(RedEnvelopeRainRewardConverter.convert(redEnvelopeRainReward));
    }

    @Override
    public RedEnvelopeRainReward queryByCondition(String activityCode, String activityDay,
                                                  RedEnvelopeRainTimeCodeEnum timeCode, Long userId,
                                                  RewardTypeEnum rewardType) {
        RedEnvelopeRainReward redEnvelopeRainReward = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("activityCode", activityCode);
        parameters.put("activityDay", activityDay);
        parameters.put("userId", userId);
        parameters.put("rewardType", rewardType.getCode());
        if (Objects.nonNull(timeCode)) {
            parameters.put("timeCode", timeCode.getCode());
        }
        List<RedEnvelopeRainReward> redEnvelopeRainRewards = RedEnvelopeRainRewardConverter
            .convert2List(redEnvelopeRainRewardDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(redEnvelopeRainRewards)) {
            redEnvelopeRainReward = redEnvelopeRainRewards.get(0);
        }
        return redEnvelopeRainReward;
    }
}
