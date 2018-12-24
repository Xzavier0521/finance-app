package cn.zhishush.finance.domainservice.converter.flash;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.flash.InformationConfigDO;
import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeModuleInfoDO;
import cn.zhishush.finance.domain.flash.InformationConfig;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @program: finance-app
 * @description:快讯
 * @author: Mr.Zhang
 * @create: 2018-12-24 14:27
 **/
public class InformationConfigConverter {
    public static InformationConfig convert(InformationConfigDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        InformationConfig to = new InformationConfig();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static InformationConfigDO convert(InformationConfig from) {
        if (Objects.isNull(from)) {
            return null;
        }
        InformationConfigDO to = new InformationConfigDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<InformationConfig> convert2List(List<InformationConfigDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<InformationConfig> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<InformationConfigDO> convert2DoList(List<InformationConfig> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<InformationConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
