package cn.zhishush.finance.domainservice.converter.activity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.activity.RedEnvelopeRainDataDO;
import cn.zhishush.finance.domain.activity.RedEnvelopeRainData;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataConverter.java, v0.1 2018/11/14 10:09 PM lili Exp $
 */
public class RedEnvelopeRainDataConverter {

	public static RedEnvelopeRainData convert(RedEnvelopeRainDataDO from) {
		if (Objects.isNull(from)) {
			return null;
		}
		RedEnvelopeRainData to = new RedEnvelopeRainData();
		ConvertBeanUtil.copyBeanProperties(from, to);
		to.setTimeCode(RedEnvelopeRainTimeCodeEnum.getByCode(from.getTimeCode()));
		return to;
	}

	public static RedEnvelopeRainDataDO convert(RedEnvelopeRainData from) {
		if (Objects.isNull(from)) {
			return null;
		}
		RedEnvelopeRainDataDO to = new RedEnvelopeRainDataDO();
		ConvertBeanUtil.copyBeanProperties(from, to);
		to.setTimeCode(Objects.nonNull(from.getTimeCode()) ? from.getTimeCode().getCode() : "");
		return to;
	}

	public static List<RedEnvelopeRainData> convert2List(List<RedEnvelopeRainDataDO> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<RedEnvelopeRainData> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}

	public static List<RedEnvelopeRainDataDO> convert2DoList(List<RedEnvelopeRainData> froms) {
		if (CollectionUtils.isEmpty(froms)) {
			return Collections.emptyList();
		}
		List<RedEnvelopeRainDataDO> tos = Lists.newArrayListWithCapacity(froms.size());
		froms.forEach(from -> tos.add(convert(from)));
		return tos;
	}
}
