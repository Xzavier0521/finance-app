package finance.domainservice.service.third;

import finance.api.model.vo.third.LoanSupermarketStatisticsDataVO;
import finance.domain.user.UserInfo;

/**
 * <p>贷超数据查询</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketQueryService.java, v0.1 2018/12/5 6:42 PM PM lili Exp $
 */
public interface LoanSupermarketQueryService {

    LoanSupermarketStatisticsDataVO queryStatisticsData(UserInfo userInfo);
}
