package cn.zhishush.finance.core.dal.dao.order;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dataobject.order.OrderDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: OrderDAO.java, v0.1 2018/11/14 12:48 PM lili Exp $
 */
public interface OrderDAO extends BaseDAO<OrderDO, Long> {

	List<OrderDO> selectPageByOrder(@Param("page") Page<OrderDO> page, @Param("param") OrderDO order);

	Long selectCountByOrder(@Param("param") OrderDO order);

	Double sumIncome(@Param("userId") Long userId);
}