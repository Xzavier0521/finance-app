package cn.zhishush.finance.domainservice.repository.popularize.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.core.dal.dao.popularize.PopularizeMaterialInfoDAO;
import cn.zhishush.finance.domain.popularize.PopularizeMaterialInfo;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeMaterialInfoConverter;
import cn.zhishush.finance.domainservice.repository.popularize.PopularizeMaterialInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoRepositoryImpl.java, v0.1 2018/12/9 10:17 PM PM lili Exp $
 */
@Repository("popularizeMaterialInfoRepository")
public class PopularizeMaterialInfoRepositoryImpl implements PopularizeMaterialInfoRepository {

    @Resource
    private PopularizeMaterialInfoDAO popularizeMaterialInfoDAO;

    @Override
    public List<PopularizeMaterialInfo> query(String subModuleCode) {
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("subModuleCode", subModuleCode);
        return PopularizeMaterialInfoConverter
            .convert2List(popularizeMaterialInfoDAO.query(parameters));
    }
}
