package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.domain.UserInfo;
import finance.model.po.FinanceUserInfo;
import finance.core.common.util.ConvertBeanUtil;
import finance.core.common.util.DateUtils;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: UserInfoConverter.java, v 0.1 2018/9/29 上午9:13 lili Exp $
 */
public class UserInfoConverter {

    public static UserInfo convert(FinanceUserInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        UserInfo to = new UserInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        // 注册时间
        if (Objects.nonNull(from.getCreateTime())) {
            to.setRegisterDate(DateUtils.format(from.getCreateTime(), DateUtils.WEB_FORMAT));
            to.setRegisterTime(DateUtils.format(from.getCreateTime(), DateUtils.LONG_WEB_FORMAT));
        }
        return to;
    }

    public static FinanceUserInfo convert(UserInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceUserInfo to = new FinanceUserInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<UserInfo> convert2List(List<FinanceUserInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<UserInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceUserInfo> convert2DoList(List<UserInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceUserInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
