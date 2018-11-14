package finance.domainservice.service.finance.profit;

import java.util.List;

import finance.api.model.base.Page;
import finance.api.model.vo.financeProfit.FinanceProfitVO;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ProfitBiz.java, v0.1 2018/11/14 1:54 PM lili Exp $
 */
public interface ProfitBiz {

    /**
     * 查询我的返现记录
     * @param userId
     * @return
     */
    List<FinanceProfitVO> myProfit(Long userId, Page<FinanceProfitVO> page);
}
