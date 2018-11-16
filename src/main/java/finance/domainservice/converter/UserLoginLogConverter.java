package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.user.UserLoginLog;
import finance.core.dal.dataobject.FinanceUserLoginLog;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserLoginLogConverter.java, v 0.1 2018/9/28 下午1:36 lili Exp $
 */
public class UserLoginLogConverter {

    public static UserLoginLog convert(FinanceUserLoginLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        UserLoginLog to = new UserLoginLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceUserLoginLog convert(UserLoginLog from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceUserLoginLog to = new FinanceUserLoginLog();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<UserLoginLog> convert2List(List<FinanceUserLoginLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<UserLoginLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceUserLoginLog> convert2DoList(List<UserLoginLog> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceUserLoginLog> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
