package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.RedEnvelopeRainConfigDO;
import finance.domain.activity.RedEnvelopeRainConfig;

/**
 * <p>
 * 红包雨活动时间配置
 * </p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainConfigConverter.java, v0.1 2018/11/16 11:49 AM
 *          PM lili Exp $
 */
public class RedEnvelopeRainConfigConverter {

	public static RedEnvelopeRainConfig convert(RedEnvelopeRainConfigDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		RedEnvelopeRainConfig to = new RedEnvelopeRainConfig();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static RedEnvelopeRainConfigDO convert(RedEnvelopeRainConfig from) {
		if (Objects.isNull(from)) {
			return null;
		}
		RedEnvelopeRainConfigDO to = new RedEnvelopeRainConfigDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		return to;
	}

	public static List<RedEnvelopeRainConfig> convert2List(List<RedEnvelopeRainConfigDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<RedEnvelopeRainConfig> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<RedEnvelopeRainConfigDO> convert2DoList(List<RedEnvelopeRainConfig> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<RedEnvelopeRainConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
