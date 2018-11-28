package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.LoanApplyInfoDO;
/**
 * <p>贷款申请记录</p>
 * @author lili
 * @version 1.0: LoanApplyInfoDAO.java, v0.1 2018/11/29 3:31 AM lili Exp $
 */
public interface LoanApplyInfoDAO {

    int insertSelective(LoanApplyInfoDO record);

    List<LoanApplyInfoDO> query(Map parameters);

    int count(Map parameters);

    LoanApplyInfoDO selectByPrimaryKey(Long id);

}