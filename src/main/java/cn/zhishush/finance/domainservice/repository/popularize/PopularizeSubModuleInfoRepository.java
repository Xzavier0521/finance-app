package cn.zhishush.finance.domainservice.repository.popularize;

import java.util.List;

import cn.zhishush.finance.domain.popularize.PopularizeSubModuleInfo;

/**
 * <p>推广子模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeSubModuleInRepository.java, v0.1 2018/12/9 8:57 PM PM lili Exp $
 */
public interface PopularizeSubModuleInfoRepository {

    List<PopularizeSubModuleInfo> query(String moduleCode);
}
