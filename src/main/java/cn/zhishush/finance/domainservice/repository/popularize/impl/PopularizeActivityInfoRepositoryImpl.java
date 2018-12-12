package cn.zhishush.finance.domainservice.repository.popularize.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.popularize.PopularizeActivityInfoDAO;
import cn.zhishush.finance.domain.popularize.PopularizeActivityInfo;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeActivityInfoConverter;
import cn.zhishush.finance.domainservice.repository.popularize.PopularizeActivityInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoRepositoryImpl.java, v0.1 2018/12/9 10:21 PM PM lili Exp $
 */
@Repository("popularizeActivityInfoRepository")
public class PopularizeActivityInfoRepositoryImpl implements PopularizeActivityInfoRepository {

    @Resource
    private PopularizeActivityInfoDAO popularizeActivityInfoDAO;

    @Override
    public List<PopularizeActivityInfo> query(String subModuleCode) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("subModuleCode", subModuleCode);
        return PopularizeActivityInfoConverter
            .convert2List(popularizeActivityInfoDAO.query(parameters));
    }
}
