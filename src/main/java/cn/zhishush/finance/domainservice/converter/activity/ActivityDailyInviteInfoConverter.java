package cn.zhishush.finance.domainservice.converter.activity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.activity.ActivityDailyInviteInfoDO;
import cn.zhishush.finance.domain.activity.ActivityDailyInviteInfo;

import com.google.common.collect.Lists;

/**
 * <p>活动每日邀请数据</p>
 *
 * @author lili
 * @version 1.0: ActivityDailyInviteInfoConverter.java, v0.1 2018/12/19 10:43 AM PM lili Exp $
 */
public class ActivityDailyInviteInfoConverter {

    public static ActivityDailyInviteInfo convert(ActivityDailyInviteInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityDailyInviteInfo to = new ActivityDailyInviteInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ActivityDailyInviteInfoDO convert(ActivityDailyInviteInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityDailyInviteInfoDO to = new ActivityDailyInviteInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ActivityDailyInviteInfo> convert2List(List<ActivityDailyInviteInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityDailyInviteInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ActivityDailyInviteInfoDO> convert2DoList(List<ActivityDailyInviteInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityDailyInviteInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
