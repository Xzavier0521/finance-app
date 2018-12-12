package cn.zhishush.finance.domainservice.converter.log;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.log.OperationRecordDO;
import cn.zhishush.finance.domain.log.OperationRecord;

import com.google.common.collect.Lists;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: OperationRecordConverter.java, v 0.1 2018/9/28 上午11:12 lili Exp
 *          $
 */
public class OperationRecordConverter {

    public static OperationRecord convert(OperationRecordDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        OperationRecord to = new OperationRecord();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static OperationRecordDO convert(OperationRecord from) {
        if (Objects.isNull(from)) {
            return null;
        }
        OperationRecordDO to = new OperationRecordDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<OperationRecord> convert2List(List<OperationRecordDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<OperationRecord> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<OperationRecordDO> convert2DoList(List<OperationRecord> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<OperationRecordDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
