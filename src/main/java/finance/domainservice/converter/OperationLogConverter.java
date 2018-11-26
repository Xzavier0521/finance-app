package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.core.dal.dataobject.OperationLogDO;
import finance.domain.log.OperationLog;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 注释
 * </p>
 *
 * @author lili
 * @version 1.0: OperationLogConverter.java, v 0.1 2018/9/28 下午4:27 lili Exp $
 */
public class OperationLogConverter {

	public static OperationLog convert(OperationLogDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		OperationLog to = new OperationLog();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static OperationLogDO convert(OperationLog from) {
		if (Objects.isNull(from)) {
			return null;
		}
		OperationLogDO to = new OperationLogDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<OperationLog> convert2List(List<OperationLogDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<OperationLog> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<OperationLogDO> convert2DoList(List<OperationLog> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<OperationLogDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
