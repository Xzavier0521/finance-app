package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.log.BarrageMessage;
import finance.core.dal.dataobject.FinanceBarrageMessage;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: BarrageMessageConverter.java, v 0.1 2018/9/29 上午10:11 lili Exp $
 */
public class BarrageMessageConverter  {

    public static BarrageMessage convert(FinanceBarrageMessage from) {
        if (Objects.isNull(from)) {
            return null;
        }
        BarrageMessage to = new BarrageMessage();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceBarrageMessage convert(BarrageMessage from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceBarrageMessage to = new FinanceBarrageMessage();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<BarrageMessage> convert2List(List<FinanceBarrageMessage> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<BarrageMessage> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceBarrageMessage> convert2DoList(List<BarrageMessage> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceBarrageMessage> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
