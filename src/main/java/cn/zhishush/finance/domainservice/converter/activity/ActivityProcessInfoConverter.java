package cn.zhishush.finance.domainservice.converter.activity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.activity.ActivityProcessInfoDO;
import cn.zhishush.finance.domain.activity.ActivityProcessInfo;

import com.google.common.collect.Lists;

/**
 * <p>活动执行记录</p>
 *
 * @author lili
 * @version 1.0: ActivityProcessInfoConverter.java, v0.1 2018/12/18 5:23 PM PM lili Exp $
 */
public class ActivityProcessInfoConverter {

    public static ActivityProcessInfo convert(ActivityProcessInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityProcessInfo to = new ActivityProcessInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ActivityProcessInfoDO convert(ActivityProcessInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityProcessInfoDO to = new ActivityProcessInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ActivityProcessInfo> convert2List(List<ActivityProcessInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityProcessInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ActivityProcessInfoDO> convert2DoList(List<ActivityProcessInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityProcessInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
