package cn.zhishush.finance.domainservice.repository.popularize.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.popularize.PopularizeSubModuleInfoDAO;
import cn.zhishush.finance.domain.popularize.PopularizeSubModuleInfo;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeSubModuleInfoConverter;
import cn.zhishush.finance.domainservice.repository.popularize.PopularizeSubModuleInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>推广子模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleInRepositoryImpl.java, v0.1 2018/12/9 8:59 PM PM lili Exp $
 */
@Repository("popularizeSubModuleInfoRepository")
public class PopularizeSubModuleInRepositoryImpl implements PopularizeSubModuleInfoRepository {

    @Resource
    private PopularizeSubModuleInfoDAO popularizeSubModuleInfoDAO;

    @Override
    public List<PopularizeSubModuleInfo> query(String moduleCode) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("moduleCode", moduleCode);
        return PopularizeSubModuleInfoConverter
            .convert2List(popularizeSubModuleInfoDAO.query(parameters));
    }
}
