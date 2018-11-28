package finance.domainservice.repository.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;

import finance.core.dal.dao.CashBackConfigDAO;
import finance.domain.cashbak.CashBackConfig;
import finance.domainservice.converter.CashBackConfigConverter;
import finance.domainservice.repository.CashBackConfigRepository;

/**
 * <p>返佣配置</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigRepositoryImpl.java, v0.1 2018/11/29 2:13 AM PM lili Exp $
 */
@Repository("cashBackConfigRepository")
public class CashBackConfigRepositoryImpl implements CashBackConfigRepository {

    @Resource
    private CashBackConfigDAO cashBackConfigDAO;

    @Override
    public CashBackConfig query(Long configId) {
        CashBackConfig cashBackConfig = null;
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("configId", configId);
        List<CashBackConfig> cashBackConfigs = CashBackConfigConverter
            .convert2List(cashBackConfigDAO.query(parameters));
        if (CollectionUtils.isNotEmpty(cashBackConfigs)) {
            cashBackConfig = cashBackConfigs.get(0);
        }
        return cashBackConfig;
    }
}
