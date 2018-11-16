package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.core.dal.dataobject.FinanceProductMain;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceProductMainDAO.java, v0.1 2018/11/14 12:54 PM lili Exp $
 */
public interface FinanceProductMainDAO extends BaseDAO<FinanceProductMain, Long> {

    List<FinanceProductMain> selectProductByType(@Param("typ") Integer typ,
                                                 @Param("page") Page<FinanceProductMain> page);

    //保险产品-返现规则查询
    List<FinanceProductMain> selectRebackCashRuleList(@Param("page") Page<FinanceProductMain> page);

    /**
     * 分页查询
     * @param parameters 查询参数
     * @return List<FinanceProductMain>
     */
    List<FinanceProductMain> query(Map<String, Object> parameters);

    /**
     * 统计收益
     * @param ids 产品id列表
     * @return FinanceProductMain
     */
    FinanceProductMain sumBonusByIds(@Param("ids") List<Long> ids);
}