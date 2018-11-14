package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.CoinLog;
import finance.model.po.FinanceCoinLog;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinLogConverter.java, v 0.1 2018/9/28 上午10:22 lili Exp $
 */
public class CoinLogConverter {
    public static CoinLog convert(FinanceCoinLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinLog to = new CoinLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceCoinLog convert(CoinLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceCoinLog to = new FinanceCoinLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CoinLog> convert2List(List<FinanceCoinLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceCoinLog> convert2DoList(List<CoinLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceCoinLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
