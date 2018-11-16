package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.domain.user.ThirdAccountInfo;
import finance.core.dal.dataobject.FinanceThirdAccountInfo;
import finance.core.common.util.ConvertBeanUtil;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ThirdAccountInfoConverter.java, v0.1 2018/10/24 1:44 PM lili Exp $
 */
public class ThirdAccountInfoConverter {

    public static ThirdAccountInfo convert(FinanceThirdAccountInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        ThirdAccountInfo to = new ThirdAccountInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceThirdAccountInfo convert(ThirdAccountInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceThirdAccountInfo to = new FinanceThirdAccountInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<ThirdAccountInfo> convert2List(List<FinanceThirdAccountInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<ThirdAccountInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceThirdAccountInfo> convert2DoList(List<ThirdAccountInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceThirdAccountInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
