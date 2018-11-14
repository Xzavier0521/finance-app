package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import finance.model.po.ActivityConfigDO;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.domain.ActivityConfig;
import finance.core.common.util.ConvertBeanUtil;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: ActivityConfigConverter.java, v0.1 2018/10/11 10:28 AM lili Exp $
 */
public class ActivityConfigConverter {

    public static ActivityConfig convert(ActivityConfigDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityConfig to = new ActivityConfig();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ActivityConfigDO convert(ActivityConfig from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityConfigDO to = new ActivityConfigDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ActivityConfig> convert2List(List<ActivityConfigDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityConfig> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ActivityConfigDO> convert2DoList(List<ActivityConfig> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
