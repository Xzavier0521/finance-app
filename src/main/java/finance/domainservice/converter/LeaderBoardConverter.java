package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import finance.domain.activity.LeaderBoard;
import finance.core.common.enums.LeaderBoardTypeEnum;
import finance.core.dal.dataobject.LeaderBoardDO;
import finance.core.common.util.CommonUtils;
import finance.core.common.util.ConvertBeanUtil;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: LeaderBoardConverter.java, v0.1 2018/10/20 8:57 AM lili Exp $
 */
public class LeaderBoardConverter {

    public static LeaderBoard convert(LeaderBoardDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LeaderBoard to = new LeaderBoard();
        ConvertBeanUtil.copyBeanProperties(from, to);
        if (StringUtils.isNotBlank(from.getMobilePhone())) {
            // 手机号码脱敏处理 --后续数据库加密然后脱敏
            to.setMobilePhone(CommonUtils.mobileEncrypt(from.getMobilePhone()));
        }
        to.setLeaderBoardType(LeaderBoardTypeEnum.getByCode(from.getLeaderBoardType()));
        return to;
    }

    public static LeaderBoardDO convert(LeaderBoard from) {
        if (Objects.isNull(from)) {
            return null;
        }
        LeaderBoardDO to = new LeaderBoardDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<LeaderBoard> convert2List(List<LeaderBoardDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LeaderBoard> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<LeaderBoardDO> convert2DoList(List<LeaderBoard> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<LeaderBoardDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
