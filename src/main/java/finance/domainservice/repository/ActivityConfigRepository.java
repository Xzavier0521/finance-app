package finance.domainservice.repository;

import java.util.List;

import finance.domain.ActivityConfig;

/**
 * <p>活动推广配置</p>
 * @author lili
 * @version $Id: ActivityConfigRepository.java, v0.1 2018/10/11 10:12 AM lili Exp $
 */
public interface ActivityConfigRepository {

    int save(ActivityConfig activityConfig);

    int update(ActivityConfig activityConfig);

    List<ActivityConfig> queryByCodes(Long agentId, List<String> activityCodes);

}
