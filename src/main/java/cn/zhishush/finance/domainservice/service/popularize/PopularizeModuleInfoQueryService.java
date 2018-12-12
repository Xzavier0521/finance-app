package cn.zhishush.finance.domainservice.service.popularize;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.popularize.PopularizeModuleInfoVO;

/**
 * <p>推广模块信息查询</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoQueryService.java, v0.1 2018/12/9 10:33 PM PM lili Exp $
 */
public interface PopularizeModuleInfoQueryService {

    Page<PopularizeModuleInfoVO> query4Page(int pageSize, int pageNum);
}
