package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.CashBackConfigDO;
import finance.domain.cashbak.CashBackConfig;

/**
 * <p>返佣配置</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigConverter.java, v0.1 2018/11/29 2:10 AM PM lili Exp $
 */
public class CashBackConfigConverter {

    public static CashBackConfig convert(CashBackConfigDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CashBackConfig to = new CashBackConfig();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CashBackConfigDO convert(CashBackConfig from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CashBackConfigDO to = new CashBackConfigDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CashBackConfig> convert2List(List<CashBackConfigDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CashBackConfig> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CashBackConfigDO> convert2DoList(List<CashBackConfig> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CashBackConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
