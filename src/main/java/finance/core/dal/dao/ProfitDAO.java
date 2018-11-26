package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.ProfitDO;
import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.api.model.vo.financeProfit.FinanceProfitVO;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: ProfitDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface ProfitDAO extends BaseDAO<ProfitDO, Long> {
	int deleteByPrimaryKey(Long id);

	int insert(ProfitDO record);

	int updateByPrimaryKey(ProfitDO record);

	List<ProfitDO> selectProfitsByUserId(@Param("userId") Long userId, @Param("page") Page<FinanceProfitVO> page);

}