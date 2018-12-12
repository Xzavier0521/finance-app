package cn.zhishush.finance.domainservice.repository.popularize;

import java.util.List;

import cn.zhishush.finance.domain.popularize.PopularizeMaterialInfo;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoRepository.java, v0.1 2018/12/9 10:16 PM PM lili Exp $
 */
public interface PopularizeMaterialInfoRepository {

    List<PopularizeMaterialInfo> query(String subModuleCode);
}
