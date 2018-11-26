package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.core.dal.dataobject.CoinLogDO;
import finance.domain.coin.CoinLog;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: CoinLogConverter.java, v 0.1 2018/9/28 上午10:22 lili Exp $
 */
public class CoinLogConverter {
    public static CoinLog convert(CoinLogDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinLog to = new CoinLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CoinLogDO convert(CoinLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinLogDO to = new CoinLogDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CoinLog> convert2List(List<CoinLogDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CoinLogDO> convert2DoList(List<CoinLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinLogDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
