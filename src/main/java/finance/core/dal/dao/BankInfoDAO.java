package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.BankInfoDO;

/**
 * <p>银行信息</p>
 * @author lili
 * @version 1.0: BankInfoDAO.java, v0.1 2018/11/28 11:32 PM lili Exp $
 */
public interface BankInfoDAO {

    List<BankInfoDO> query(Map parameters);

    int count(Map parameters);

    BankInfoDO selectByPrimaryKey(Long id);

}