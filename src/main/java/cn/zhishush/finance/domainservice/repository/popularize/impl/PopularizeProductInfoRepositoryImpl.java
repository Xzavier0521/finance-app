package cn.zhishush.finance.domainservice.repository.popularize.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.popularize.PopularizeProductInfoDAO;
import cn.zhishush.finance.domain.popularize.PopularizeProductInfo;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeProductInfoConverter;
import cn.zhishush.finance.domainservice.repository.popularize.PopularizeProductInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>推广产品信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeProductInfoRepositoryImpl.java, v0.1 2018/12/9 9:39 PM PM lili Exp $
 */
@Repository("popularizeProductInfoRepository")
public class PopularizeProductInfoRepositoryImpl implements PopularizeProductInfoRepository {

    @Resource
    private PopularizeProductInfoDAO popularizeProductInfoDAO;

    @Override
    public List<PopularizeProductInfo> query(String subModuleCode) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("subModuleCode", subModuleCode);
        return PopularizeProductInfoConverter
            .convert2List(popularizeProductInfoDAO.query(parameters));
    }
}
