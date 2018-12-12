package cn.zhishush.finance.domainservice.converter.popularize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeModuleInfoDO;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * <p>推广模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoConverter.java, v0.1 2018/12/7 12:14 AM PM lili Exp $
 */
public class PopularizeModuleInfoConverter {

    public static PopularizeModuleInfo convert(PopularizeModuleInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeModuleInfo to = new PopularizeModuleInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static PopularizeModuleInfoDO convert(PopularizeModuleInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeModuleInfoDO to = new PopularizeModuleInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<PopularizeModuleInfo> convert2List(List<PopularizeModuleInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeModuleInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<PopularizeModuleInfoDO> convert2DoList(List<PopularizeModuleInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeModuleInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
