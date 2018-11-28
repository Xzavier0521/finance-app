package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.CreditCardDetailsDO;
import finance.domain.creditcard.CreditCardDetails;

/**
 * <p>信用卡明细</p>
 *
 * @author lili
 * @version 1.0: CreditCardDetailsConverter.java, v0.1 2018/11/29 1:32 AM PM lili Exp $
 */
public class CreditCardDetailsConverter {

    public static CreditCardDetails convert(CreditCardDetailsDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardDetails to = new CreditCardDetails();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CreditCardDetailsDO convert(CreditCardDetails from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardDetailsDO to = new CreditCardDetailsDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CreditCardDetails> convert2List(List<CreditCardDetailsDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardDetails> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CreditCardDetailsDO> convert2DoList(List<CreditCardDetails> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardDetailsDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
