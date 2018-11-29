package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.LoanInfoDO;
import finance.domain.loan.LoanInfo;

/**
 * <p>贷款产品</p>
 *
 * @author lili
 * @version 1.0: LoanInfoConverter.java, v0.1 2018/11/29 2:02 PM PM lili Exp $
 */
public class LoanInfoConverter {

    public static LoanInfo convert(LoanInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanInfo to = new LoanInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static LoanInfoDO convert(LoanInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanInfoDO to = new LoanInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<LoanInfo> convert2List(List<LoanInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<LoanInfoDO> convert2DoList(List<LoanInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
