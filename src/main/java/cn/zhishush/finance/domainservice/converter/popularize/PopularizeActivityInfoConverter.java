package cn.zhishush.finance.domainservice.converter.popularize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeActivityInfoDO;
import cn.zhishush.finance.domain.popularize.PopularizeActivityInfo;

import com.google.common.collect.Lists;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoConverter.java, v0.1 2018/12/8 12:20 AM PM lili Exp $
 */
public class PopularizeActivityInfoConverter {

    public static PopularizeActivityInfo convert(PopularizeActivityInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeActivityInfo to = new PopularizeActivityInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static PopularizeActivityInfoDO convert(PopularizeActivityInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeActivityInfoDO to = new PopularizeActivityInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<PopularizeActivityInfo> convert2List(List<PopularizeActivityInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeActivityInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<PopularizeActivityInfoDO> convert2DoList(List<PopularizeActivityInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeActivityInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
