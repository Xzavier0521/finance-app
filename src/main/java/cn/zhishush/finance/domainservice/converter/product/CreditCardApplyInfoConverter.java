package cn.zhishush.finance.domainservice.converter.product;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.domain.creditcard.CreditCardApplyInfo;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import cn.zhishush.finance.core.dal.dataobject.product.CreditCardApplyInfoDO;

/**
 * <p>信用卡申请信息</p>
 *
 * @author lili
 * @version 1.0: CreditCardApplyInfoConverter.java, v0.1 2018/11/29 5:23 PM PM lili Exp $
 */
public class CreditCardApplyInfoConverter {
    public static CreditCardApplyInfo convert(CreditCardApplyInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardApplyInfo to = new CreditCardApplyInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static CreditCardApplyInfoDO convert(CreditCardApplyInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        CreditCardApplyInfoDO to = new CreditCardApplyInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<CreditCardApplyInfo> convert2List(List<CreditCardApplyInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardApplyInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<CreditCardApplyInfoDO> convert2DoList(List<CreditCardApplyInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<CreditCardApplyInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
