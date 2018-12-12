package cn.zhishush.finance.domainservice.service.finance.order.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.zhishush.finance.core.dal.dao.order.OrderDAO;
import cn.zhishush.finance.core.dal.dataobject.order.OrderDO;
import org.springframework.stereotype.Component;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.common.enums.OrderType;
import cn.zhishush.finance.domainservice.service.finance.order.OrderBiz;

/**
 * @author yaolei
 * @Title: OrderBizImpl
 * @ProjectName finance-app
 * @date 2018/7/6下午1:59
 */
@Component
public class OrderBizImpl implements OrderBiz {
	@Resource
	public OrderDAO orderMapper;

	@Override
	public void transRecords(Long userId, Page<OrderDO> page) {
		OrderDO order = new OrderDO();
		order.setUserId(userId);
		order.setTransType(OrderType.debit.getCode());
		List<OrderDO> dataList = orderMapper.selectPageByOrder(page, order);
		Long totalCount = orderMapper.selectCountByOrder(order);
		page.setDataList(dataList);
		page.setTotalCount(totalCount);
	}

}
