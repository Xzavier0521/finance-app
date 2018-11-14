package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.OperationRecord;
import finance.model.po.FinanceOperationRecord;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: OperationRecordConverter.java, v 0.1 2018/9/28 上午11:12 lili Exp $
 */
public class OperationRecordConverter {

    public static OperationRecord convert(FinanceOperationRecord from) {
        if (Objects.isNull(from)) {
            return null;
        }
        OperationRecord to = new OperationRecord();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceOperationRecord convert(OperationRecord from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceOperationRecord to = new FinanceOperationRecord();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<OperationRecord> convert2List(List<FinanceOperationRecord> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<OperationRecord> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceOperationRecord> convert2DoList(List<OperationRecord> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceOperationRecord> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
