package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.LoanInfoDO;

/**
 * <p>贷款产品信息</p>
 * @author lili
 * @version 1.0: LoanInfoDAO.java, v0.1 2018/11/29 1:36 PM lili Exp $
 */
public interface LoanInfoDAO {

    List<LoanInfoDO> query(Map parameters);

    int count(Map parameters);

    LoanInfoDO selectByPrimaryKey(Long id);

}