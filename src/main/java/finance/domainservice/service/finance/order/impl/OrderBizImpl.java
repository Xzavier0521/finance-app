package finance.domainservice.service.finance.order.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import finance.api.model.base.Page;
import finance.core.common.enums.OrderType;
import finance.domainservice.service.finance.order.OrderBiz;
import finance.core.dal.dao.FinanceOrderDAO;
import finance.core.dal.dataobject.FinanceOrder;

/**
 * @author yaolei
 * @Title: OrderBizImpl
 * @ProjectName finance-app
 * @date 2018/7/6下午1:59
 */
@Component
public class OrderBizImpl implements OrderBiz {
    @Resource
    public FinanceOrderDAO orderMapper;

    @Override
    public void transRecords(Long userId, Page<FinanceOrder> page) {
        FinanceOrder order = new FinanceOrder();
        order.setUserId(userId);
        order.setTransType(OrderType.debit.getCode());
        List<FinanceOrder> dataList = orderMapper.selectPageByOrder(page, order);
        Long totalCount = orderMapper.selectCountByOrder(order);
        page.setDataList(dataList);
        page.setTotalCount(totalCount);
    }

}
