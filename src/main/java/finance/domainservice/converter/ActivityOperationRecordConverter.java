package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.ActivityOperationRecordDO;
import finance.domain.activity.ActivityOperationRecord;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordConverter.java, v0.1 2018/11/26 2:00 PM PM lili Exp $
 */
public class ActivityOperationRecordConverter {

    public static ActivityOperationRecord convert(ActivityOperationRecordDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityOperationRecord to = new ActivityOperationRecord();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static ActivityOperationRecordDO convert(ActivityOperationRecord from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ActivityOperationRecordDO to = new ActivityOperationRecordDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        if (Objects.nonNull(from.getRewardType())) {
            to.setRewardType(from.getRewardType().getCode());
        }
        return to;
    }

    public static List<ActivityOperationRecord> convert2List(List<ActivityOperationRecordDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityOperationRecord> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<ActivityOperationRecordDO> convert2DoList(List<ActivityOperationRecord> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ActivityOperationRecordDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
