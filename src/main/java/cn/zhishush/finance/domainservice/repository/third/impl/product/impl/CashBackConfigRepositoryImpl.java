package cn.zhishush.finance.domainservice.repository.third.impl.product.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.product.CashBackConfigDAO;
import cn.zhishush.finance.domain.cashbak.CashBackConfig;
import cn.zhishush.finance.domainservice.converter.product.CashBackConfigConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CashBackConfigRepository;

import com.google.common.collect.Maps;

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
    public CashBackConfig query(String configId) {
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

    @Override
    public List<CashBackConfig> query(List<String> configIds) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("configIds", configIds);
        return CashBackConfigConverter.convert2List(cashBackConfigDAO.query(parameters));
    }
}
