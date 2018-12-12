package cn.zhishush.finance.domainservice.converter.account;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.dal.dataobject.account.BankInfoDO;
import cn.zhishush.finance.domain.creditcard.BankInfo;
import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

/**
 * <p>银行信息</p>
 *
 * @author lili
 * @version 1.0: BankInfoConverter.java, v0.1 2018/11/28 11:34 PM PM lili Exp $
 */
public class BankInfoConverter {

    public static BankInfo convert(BankInfoDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        BankInfo to = new BankInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static BankInfoDO convert(BankInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        BankInfoDO to = new BankInfoDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<BankInfo> convert2List(List<BankInfoDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<BankInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<BankInfoDO> convert2DoList(List<BankInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<BankInfoDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
