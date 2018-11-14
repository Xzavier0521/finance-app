package finance.domainservice.converter;

import com.google.common.collect.Lists;
import finance.domain.UserInviteInfo;
import finance.model.po.FinanceUserInviteInfo;
import finance.core.common.util.ConvertBeanUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: UserInviteInfoConverter.java, v0.1 2018/10/11 1:52 PM lili Exp $
 */
public class UserInviteInfoConverter {

    public static UserInviteInfo convert(FinanceUserInviteInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        UserInviteInfo to = new UserInviteInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static FinanceUserInviteInfo convert(UserInviteInfo from) {
        if (Objects.isNull(from)) {
            return null;
        }
        FinanceUserInviteInfo to = new FinanceUserInviteInfo();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<UserInviteInfo> convert2List(List<FinanceUserInviteInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<UserInviteInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<FinanceUserInviteInfo> convert2DoList(List<UserInviteInfo> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<FinanceUserInviteInfo> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
