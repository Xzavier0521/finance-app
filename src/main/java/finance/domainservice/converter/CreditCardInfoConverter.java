package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.CreditCardInfoDO;
import finance.domain.creditcard.CreditCardInfo;

/**
 * <p>信用卡信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardInfoConverter.java, v0.1 2018/11/29 12:57 AM PM lili Exp $
 */
public class CreditCardInfoConverter {

    public static CreditCardInfo convert(CreditCardInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardInfo to = new CreditCardInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CreditCardInfoDO convert(CreditCardInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardInfoDO to = new CreditCardInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CreditCardInfo> convert2List(List<CreditCardInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CreditCardInfoDO> convert2DoList(List<CreditCardInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
