package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.CreditCardInfoDO;

/**
 * <p>注释</p>
 * @author lili
 * @version 1.0: CreditCardInfoDAO.java, v0.1 2018/11/29 12:54 AM lili Exp $
 */
public interface CreditCardInfoDAO {

    List<CreditCardInfoDO> query(Map parameters);

    int count(Map parameters);

    CreditCardInfoDO selectByPrimaryKey(Long id);

     CreditCardInfoDO selectBycardId(String cardId);
    List<CreditCardInfoDO> selectByBankcode(String codeId);


}