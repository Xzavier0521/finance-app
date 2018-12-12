package cn.zhishush.finance.domainservice.converter.third;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.third.ThirdUnionLoginLogDO;
import cn.zhishush.finance.domain.third.ThirdUnionLoginLog;

/**
 * <p>第三方联合登陆日志</p>
 *
 * @author lili
 * @version 1.0: ThirdUnionLoginLogConverter.java, v0.1 2018/11/28 8:36 PM PM lili Exp $
 */
public class ThirdUnionLoginLogConverter {

    public static ThirdUnionLoginLog convert(ThirdUnionLoginLogDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ThirdUnionLoginLog to = new ThirdUnionLoginLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ThirdUnionLoginLogDO convert(ThirdUnionLoginLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ThirdUnionLoginLogDO to = new ThirdUnionLoginLogDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ThirdUnionLoginLog> convert2List(List<ThirdUnionLoginLogDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ThirdUnionLoginLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ThirdUnionLoginLogDO> convert2DoList(List<ThirdUnionLoginLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ThirdUnionLoginLogDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
