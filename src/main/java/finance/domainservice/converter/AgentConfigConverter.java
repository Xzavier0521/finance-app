package finance.domainservice.converter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;

import com.google.common.collect.Lists;

import finance.domain.activity.AgentConfig;
import finance.core.dal.dataobject.AgentConfigDO;
import finance.core.common.util.ConvertBeanUtil;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version $Id: AgentConfigConverter.java, v0.1 2018/10/11 11:08 AM lili Exp $
 */
public class AgentConfigConverter {

    public static AgentConfig convert(AgentConfigDO from) {
        if (Objects.isNull(from)) {
            return null;
        }
        AgentConfig to = new AgentConfig();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static AgentConfigDO convert(AgentConfig from) {
        if (Objects.isNull(from)) {
            return null;
        }
        AgentConfigDO to = new AgentConfigDO();
        ConvertBeanUtil.copyBeanProperties(from, to);
        return to;
    }

    public static List<AgentConfig> convert2List(List<AgentConfigDO> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<AgentConfig> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }

    public static List<AgentConfigDO> convert2DoList(List<AgentConfig> froms) {
        if (CollectionUtils.isEmpty(froms)) {
            return Collections.emptyList();
        }
        List<AgentConfigDO> tos = Lists.newArrayListWithCapacity(froms.size());
        froms.forEach(from -> tos.add(convert(from)));
        return tos;
    }
}
