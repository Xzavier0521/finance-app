package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.CreditCardDetailsDO;
/**
 * <p>信用卡明细</p>
 * @author lili
 * @version 1.0: CreditCardDetailsDAO.java, v0.1 2018/11/29 1:28 AM lili Exp $
 */
public interface CreditCardDetailsDAO {

    List<CreditCardDetailsDO> query(Map parameters);

    int count(Map parameters);

    CreditCardDetailsDO selectByPrimaryKey(Long id);

}