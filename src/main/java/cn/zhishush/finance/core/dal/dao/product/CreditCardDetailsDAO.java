package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.CreditCardDetailsDO;
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