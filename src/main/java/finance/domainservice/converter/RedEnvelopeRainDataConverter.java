package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.util.CommonUtils;
import finance.core.common.util.ConvertBeanUtil;
import finance.core.dal.dataobject.RedEnvelopeRainDataDO;
import finance.domain.activity.RedEnvelopeRainData;

/**
 * <p>红包雨活动数据</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainDataConverter.java, v0.1 2018/11/14 10:09 PM PM lili Exp $
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
