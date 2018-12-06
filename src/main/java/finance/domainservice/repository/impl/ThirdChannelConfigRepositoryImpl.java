package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.ThirdChannelConfigDAO;
import finance.domain.third.ThirdChannelConfig;
import finance.domainservice.converter.ThirdChannelConfigConverter;
import finance.domainservice.repository.ThirdChannelConfigRepository;

/**
 * <p>第三方渠道配置p>
 *
 * @author lili
 * @version 1.0: ThirdChannelConfigRepositoryImpl.java, v0.1 2018/12/5 6:20 PM PM lili Exp $
 */
@Repository("thirdChannelConfigRepository")
public class ThirdChannelConfigRepositoryImpl implements ThirdChannelConfigRepository {

    @Resource
    private ThirdChannelConfigDAO thirdChannelConfigDAO;

    @Override
    public ThirdChannelConfig query(String mobileNum) {
        ThirdChannelConfig thirdChannelConfig = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("mobileNum", mobileNum);
        parameters.put("isDelete", "0");
        List<ThirdChannelConfig> thirdChannelConfigList = ThirdChannelConfigConverter
            .convert2List(thirdChannelConfigDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(thirdChannelConfigList)) {
            thirdChannelConfig = thirdChannelConfigList.get(0);
        }
        return thirdChannelConfig;
    }
}
