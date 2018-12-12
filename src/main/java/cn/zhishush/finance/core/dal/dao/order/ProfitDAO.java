package cn.zhishush.finance.core.dal.dao.order;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.financeProfit.FinanceProfitVO;
import cn.zhishush.finance.core.dal.dataobject.order.ProfitDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: ProfitDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface ProfitDAO extends BaseDAO<ProfitDO, Long> {
    int deleteByPrimaryKey(Long id);

    int insert(ProfitDO record);

    int updateByPrimaryKey(ProfitDO record);

    List<ProfitDO> selectProfitsByUserId(@Param("userId") Long userId,
                                         @Param("page") Page<FinanceProfitVO> page);

}