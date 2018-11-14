package finance.domainservice.service.finance.order;

import finance.api.model.base.Page;
import finance.model.po.FinanceOrder;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: OrderBiz.java, v0.1 2018/11/14 1:53 PM lili Exp $
 */
public interface OrderBiz {

    /**
     * 交易记录
     * @param userId
     * @return
     */
    void transRecords(Long userId, Page<FinanceOrder> page);

}
