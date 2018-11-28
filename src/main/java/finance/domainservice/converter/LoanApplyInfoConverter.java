package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.LoanApplyInfoDO;
import finance.domain.loan.LoanApplyInfo;

/**
 * <p>贷款申请记录</p>
 *
 * @author lili
 * @version 1.0: LoanApplyInfoConverter.java, v0.1 2018/11/29 3:31 AM PM lili Exp $
 */
public class LoanApplyInfoConverter {

    public static LoanApplyInfo convert(LoanApplyInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanApplyInfo to = new LoanApplyInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static LoanApplyInfoDO convert(LoanApplyInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LoanApplyInfoDO to = new LoanApplyInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<LoanApplyInfo> convert2List(List<LoanApplyInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanApplyInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<LoanApplyInfoDO> convert2DoList(List<LoanApplyInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LoanApplyInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
