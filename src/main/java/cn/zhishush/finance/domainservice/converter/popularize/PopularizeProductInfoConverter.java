package cn.zhishush.finance.domainservice.converter.popularize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeProductInfoDO;
import cn.zhishush.finance.domain.popularize.PopularizeProductInfo;

import com.google.common.collect.Lists;

/**
 * <p>推广产品信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeProductInfoConverter.java, v0.1 2018/12/9 9:36 PM PM lili Exp $
 */
public class PopularizeProductInfoConverter {
    public static PopularizeProductInfo convert(PopularizeProductInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeProductInfo to = new PopularizeProductInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static PopularizeProductInfoDO convert(PopularizeProductInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeProductInfoDO to = new PopularizeProductInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<PopularizeProductInfo> convert2List(List<PopularizeProductInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeProductInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<PopularizeProductInfoDO> convert2DoList(List<PopularizeProductInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeProductInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
