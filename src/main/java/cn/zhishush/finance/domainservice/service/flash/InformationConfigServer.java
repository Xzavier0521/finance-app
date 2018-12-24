package cn.zhishush.finance.domainservice.service.flash;


import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.flash.InformationConfig;

/**
 * @program: finance-app
 * @description:快讯
 * @author: Mr.Zhang
 * @create: 2018-12-22 16:24
 **/
public interface InformationConfigServer {
    Page<InformationConfig> query4Page(Integer pageSize, Integer pageNum);
}
