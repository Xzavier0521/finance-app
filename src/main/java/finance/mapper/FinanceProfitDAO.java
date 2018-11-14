package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.api.model.vo.financeProfit.FinanceProfitVO;
import finance.model.po.FinanceProfit;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceProfitDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface FinanceProfitDAO extends BaseDAO<FinanceProfit, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(FinanceProfit record);

    int updateByPrimaryKey(FinanceProfit record);

    List<FinanceProfit> selectProfitsByUserId(@Param("userId") Long userId,
                                              @Param("page") Page<FinanceProfitVO> page);

}