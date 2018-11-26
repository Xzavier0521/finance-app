package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.core.dal.dataobject.BarrageMessageDO;
import finance.domain.log.BarrageMessage;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: BarrageMessageConverter.java, v0.1 2018/11/26 9:34 AM lili Exp $
 */
public class BarrageMessageConverter {

	public static BarrageMessage convert(BarrageMessageDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		BarrageMessage to = new BarrageMessage();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static BarrageMessageDO convert(BarrageMessage from) {
		if (Objects.isNull(from)) {
			return null;
		}
		BarrageMessageDO to = new BarrageMessageDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<BarrageMessage> convert2List(List<BarrageMessageDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<BarrageMessage> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<BarrageMessageDO> convert2DoList(List<BarrageMessage> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<BarrageMessageDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
