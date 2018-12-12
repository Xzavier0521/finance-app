package cn.zhishush.finance.domainservice.converter.third;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.third.ThirdChannelConfigDO;
import cn.zhishush.finance.domain.third.ThirdChannelConfig;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * <p>第三方渠道配置</p>
 *
 * @author lili
 * @version 1.0: ThirdChannelConfigConverter.java, v0.1 2018/12/5 6:17 PM PM lili Exp $
 */
public class ThirdChannelConfigConverter {

    public static ThirdChannelConfig convert(ThirdChannelConfigDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ThirdChannelConfig to = new ThirdChannelConfig();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ThirdChannelConfigDO convert(ThirdChannelConfig from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ThirdChannelConfigDO to = new ThirdChannelConfigDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ThirdChannelConfig> convert2List(List<ThirdChannelConfigDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ThirdChannelConfig> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ThirdChannelConfigDO> convert2DoList(List<ThirdChannelConfig> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ThirdChannelConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
