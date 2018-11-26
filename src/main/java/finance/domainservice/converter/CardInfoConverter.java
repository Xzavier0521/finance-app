package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.core.dal.dataobject.IdCardInfoDO;
import finance.domain.coin.CardInfo;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: CardInfoConverter.java, v0.1 2018/11/26 9:34 AM lili Exp $
 */
public class CardInfoConverter {

    public static CardInfo convert(IdCardInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CardInfo to = new CardInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static IdCardInfoDO convert(CardInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        IdCardInfoDO to = new IdCardInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CardInfo> convert2List(List<IdCardInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CardInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<IdCardInfoDO> convert2DoList(List<CardInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<IdCardInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
