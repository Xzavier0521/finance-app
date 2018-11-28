package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.CreditCardInfoDO;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: CreditCardInfoDAO.java, v0.1 2018/11/29 12:54 AM lili Exp $
 */
public interface CreditCardInfoDAO {

    List<CreditCardInfoDO> query(Map parameters);

    int count(Map parameters);

    CreditCardInfoDO selectByPrimaryKey(Long id);

}