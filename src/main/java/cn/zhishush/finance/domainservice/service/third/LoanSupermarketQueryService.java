package cn.zhishush.finance.domainservice.service.third;

import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.api.model.vo.third.LoanSupermarketStatisticsDataVO;

/**
 * <p>贷超数据查询</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketQueryService.java, v0.1 2018/12/5 6:42 PM PM lili Exp $
 */
public interface LoanSupermarketQueryService {

    LoanSupermarketStatisticsDataVO queryStatisticsData(UserInfo userInfo);
}
