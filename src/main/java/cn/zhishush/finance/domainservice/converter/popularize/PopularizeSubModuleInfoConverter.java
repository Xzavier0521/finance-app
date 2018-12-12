package cn.zhishush.finance.domainservice.converter.popularize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeSubModuleInfoDO;
import cn.zhishush.finance.domain.popularize.PopularizeSubModuleInfo;

import com.google.common.collect.Lists;

/**
 * <p>推广子模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleInfoConverter.java, v0.1 2018/12/9 9:04 PM PM lili Exp $
 */
public class PopularizeSubModuleInfoConverter {

    public static PopularizeSubModuleInfo convert(PopularizeSubModuleInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeSubModuleInfo to = new PopularizeSubModuleInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static PopularizeSubModuleInfoDO convert(PopularizeSubModuleInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeSubModuleInfoDO to = new PopularizeSubModuleInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<PopularizeSubModuleInfo> convert2List(List<PopularizeSubModuleInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeSubModuleInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<PopularizeSubModuleInfoDO> convert2DoList(List<PopularizeSubModuleInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeSubModuleInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
