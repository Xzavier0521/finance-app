package cn.zhishush.finance.domainservice.converter.third;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.api.model.vo.third.RegisterStatisticsData;
import cn.zhishush.finance.core.dal.dataobject.third.RegisterStatisticsDataDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: RegisterStatisticsDataConverter.java, v0.1 2018/12/6 3:19 PM PM lili Exp $
 */
public class RegisterStatisticsDataConverter {

    public static RegisterStatisticsData convert(RegisterStatisticsDataDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        RegisterStatisticsData to = new RegisterStatisticsData();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static RegisterStatisticsDataDO convert(RegisterStatisticsData from) {
        if (Objects.isNull(from)) {
            return null;
        }
        RegisterStatisticsDataDO to = new RegisterStatisticsDataDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<RegisterStatisticsData> convert2List(List<RegisterStatisticsDataDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<RegisterStatisticsData> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<RegisterStatisticsDataDO> convert2DoList(List<RegisterStatisticsData> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<RegisterStatisticsDataDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
