package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.model.po.FinanceOrder;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceOrderDAO.java, v0.1 2018/11/14 12:48 PM lili Exp $
 */
public interface FinanceOrderDAO extends BaseDAO<FinanceOrder, Long> {

    List<FinanceOrder> selectPageByOrder(@Param("page") Page<FinanceOrder> page,
                                         @Param("param") FinanceOrder order);

    Long selectCountByOrder(@Param("param") FinanceOrder order);

    Double sumIncome(@Param("userId") Long userId);
}