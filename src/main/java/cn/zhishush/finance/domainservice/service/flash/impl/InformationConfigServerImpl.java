package cn.zhishush.finance.domainservice.service.flash.impl;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.flash.InformationConfigMapper;
import cn.zhishush.finance.core.dal.dataobject.flash.InformationConfigDO;
import cn.zhishush.finance.domain.flash.InformationConfig;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;
import cn.zhishush.finance.domainservice.converter.flash.InformationConfigConverter;
import cn.zhishush.finance.domainservice.converter.popularize.PopularizeModuleInfoConverter;
import cn.zhishush.finance.domainservice.service.flash.InformationConfigServer;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: finance-app
 * @description: 快讯相关
 * @author: Mr.Zhang
 * @create: 2018-12-22 16:27
 **/
@Service
@Slf4j
public class InformationConfigServerImpl implements InformationConfigServer {
    @Resource
    private InformationConfigMapper informationConfigMapper;
    @Override
    public Page<InformationConfig> query4Page(Integer pageSize, Integer beginNum) {
        Page<InformationConfig> page = new Page<>(pageSize, (long) beginNum);

            log.info("aaaaa{}",page);
        Map<String, Object> parameters = Maps.newHashMap();
        parameters.put("page",  page);
        int count = informationConfigMapper.count(parameters);
        page.setTotalCount((long) count);
         if (count > 0) {
             log.info("得到的 {}",parameters);
             List<InformationConfigDO>  list= informationConfigMapper.query(parameters);
             log.info("得到的结果为{}", parameters.get(page));


             page.setDataList(InformationConfigConverter.convert2List(list));
        }
        return page;
    }
}
