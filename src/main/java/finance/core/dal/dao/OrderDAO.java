package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.core.dal.dataobject.OrderDO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: OrderDAO.java, v0.1 2018/11/14 12:48 PM lili Exp $
 */
public interface OrderDAO extends BaseDAO<OrderDO, Long> {

	List<OrderDO> selectPageByOrder(@Param("page") Page<OrderDO> page, @Param("param") OrderDO order);

	Long selectCountByOrder(@Param("param") OrderDO order);

	Double sumIncome(@Param("userId") Long userId);
}