package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.coin.CoinGameLog;
import finance.core.dal.dataobject.FinanceCoinGameLog;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinGameLogConverter.java, v 0.1 2018/9/28 上午10:54 lili Exp $
 */
public class CoinGameLogConverter {

    public static CoinGameLog convert(FinanceCoinGameLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CoinGameLog to = new CoinGameLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceCoinGameLog convert(CoinGameLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceCoinGameLog to = new FinanceCoinGameLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CoinGameLog> convert2List(List<FinanceCoinGameLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CoinGameLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceCoinGameLog> convert2DoList(List<CoinGameLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceCoinGameLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
