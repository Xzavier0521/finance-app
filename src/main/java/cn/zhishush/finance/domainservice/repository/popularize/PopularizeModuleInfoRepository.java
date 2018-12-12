package cn.zhishush.finance.domainservice.repository.popularize;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;

/**
 * <p>推广模块信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoRepository.java, v0.1 2018/12/7 12:16 AM PM lili Exp $
 */
public interface PopularizeModuleInfoRepository {

    Page<PopularizeModuleInfo> query4Page(int pageSize, int pageNum);
}
