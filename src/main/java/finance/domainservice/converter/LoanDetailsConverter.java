package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.LoanDetailsDO;
import finance.domain.loan.LoanDetails;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsConverter.java, v0.1 2018/11/29 3:22 PM PM lili Exp $
 */
public class LoanDetailsConverter {

    public static LoanDetails convert(LoanDetailsDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanDetails to = new LoanDetails();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static LoanDetailsDO convert(LoanDetails from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanDetailsDO to = new LoanDetailsDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<LoanDetails> convert2List(List<LoanDetailsDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanDetails> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<LoanDetailsDO> convert2DoList(List<LoanDetails> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanDetailsDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
