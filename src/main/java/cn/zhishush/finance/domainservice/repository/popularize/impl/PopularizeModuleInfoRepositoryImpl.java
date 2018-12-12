package cn.zhishush.finance.domainservice.repository.popularize.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.popularize.PopularizeModuleInfoDAO;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeModuleInfoConverter;
import cn.zhishush.finance.domainservice.repository.popularize.PopularizeModuleInfoRepository;

import com.google.common.collect.Maps;

/**
 * <p>推广模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoRepositoryImpl.java, v0.1 2018/12/9 10:18 PM PM lili Exp $
 */
@Repository("popularizeModuleInfoRepository")
public class PopularizeModuleInfoRepositoryImpl implements PopularizeModuleInfoRepository {

    @Resource
    private PopularizeModuleInfoDAO popularizeModuleInfoDAO;

    @Override
    public Page<PopularizeModuleInfo> query4Page(int pageSize, int pageNum) {
        Page<PopularizeModuleInfo> page = new Page<>(pageSize, (long) pageNum);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("page", page);
        int count = popularizeModuleInfoDAO.count(parameters);
        page.setTotalCount((long) count);
        if (count > 0) {
            page.setDataList(PopularizeModuleInfoConverter
                .convert2List(popularizeModuleInfoDAO.query(parameters)));
        }
        return page;
    }
}
