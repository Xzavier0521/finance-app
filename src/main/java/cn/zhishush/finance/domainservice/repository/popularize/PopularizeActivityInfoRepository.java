package cn.zhishush.finance.domainservice.repository.popularize;

import java.util.List;

import cn.zhishush.finance.domain.popularize.PopularizeActivityInfo;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoRepository.java, v0.1 2018/12/8 12:20 AM PM lili Exp $
 */
public interface PopularizeActivityInfoRepository {

    List<PopularizeActivityInfo> query(String subModuleCode);
}
