package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.enums.RewardTypeEnum;
import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.RedEnvelopeRainRewardDO;
import finance.domain.activity.RedEnvelopeRainReward;

/**
 * <p>红包雨活动金币奖励日志</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainRewardConverter.java, v0.1 2018/11/18 3:05 PM PM lili Exp $
 */
public class RedEnvelopeRainRewardConverter {

    public static RedEnvelopeRainReward convert(RedEnvelopeRainRewardDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        RedEnvelopeRainReward to = new RedEnvelopeRainReward();
        ConvertBeanUtil.copyBeanProperties(from, to);
        to.setRewardType(RewardTypeEnum.getByCode(from.getRewardType()));
        return to;
    }

    public static RedEnvelopeRainRewardDO convert(RedEnvelopeRainReward from) {
        if (Objects.isNull(from)) {
            return null;
        }
        RedEnvelopeRainRewardDO to = new RedEnvelopeRainRewardDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        if (Objects.nonNull(from.getRewardType())) {
            to.setRewardType(from.getRewardType().getCode());
        }
        if (Objects.nonNull(from.getTimeCode())) {
            to.setTimeCode(from.getTimeCode().getCode());
        }
        return to;
    }

    public static List<RedEnvelopeRainReward> convert2List(List<RedEnvelopeRainRewardDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<RedEnvelopeRainReward> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<RedEnvelopeRainRewardDO> convert2DoList(List<RedEnvelopeRainReward> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<RedEnvelopeRainRewardDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
