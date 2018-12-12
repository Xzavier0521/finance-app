package cn.zhishush.finance.domainservice.repository.popularize;

import java.util.List;

import cn.zhishush.finance.domain.popularize.PopularizeProductInfo;

/**
 * <p>推广产品信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeProductInfoRepository.java, v0.1 2018/12/9 9:37 PM PM lili Exp $
 */
public interface PopularizeProductInfoRepository {

    List<PopularizeProductInfo> query(String subModuleCode);
}
