package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.LoanDetailsDO;

/**
 * <p>贷款产品明细</p>
 *
 * @author lili
 * @version 1.0: LoanDetailsDAO.java, v0.1 2018/11/29 2:38 PM lili Exp $
 */
public interface LoanDetailsDAO {

    List<LoanDetailsDO> query(Map parameters);

    int count(Map parameters);

    LoanDetailsDO selectByPrimaryKey(Long id);

}