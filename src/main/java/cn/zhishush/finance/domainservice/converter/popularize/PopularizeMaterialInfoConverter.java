package cn.zhishush.finance.domainservice.converter.popularize;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeMaterialInfoDO;
import cn.zhishush.finance.domain.popularize.PopularizeMaterialInfo;

import com.google.common.collect.Lists;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoConverter.java, v0.1 2018/12/9 9:49 PM PM lili Exp $
 */
public class PopularizeMaterialInfoConverter {

    public static PopularizeMaterialInfo convert(PopularizeMaterialInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeMaterialInfo to = new PopularizeMaterialInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static PopularizeMaterialInfoDO convert(PopularizeMaterialInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        PopularizeMaterialInfoDO to = new PopularizeMaterialInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<PopularizeMaterialInfo> convert2List(List<PopularizeMaterialInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeMaterialInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<PopularizeMaterialInfoDO> convert2DoList(List<PopularizeMaterialInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<PopularizeMaterialInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
