package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.coin.CardInfo;
import finance.core.dal.dataobject.FinanceIdCardInfo;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CardInfoConverter {

    public static CardInfo convert(FinanceIdCardInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CardInfo to = new CardInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceIdCardInfo convert(CardInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceIdCardInfo to = new FinanceIdCardInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CardInfo> convert2List(List<FinanceIdCardInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CardInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceIdCardInfo> convert2DoList(List<CardInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceIdCardInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
