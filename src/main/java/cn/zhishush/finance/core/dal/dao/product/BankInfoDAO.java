package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.account.BankInfoDO;

/**
 * <p>银行信息</p>
 * @author lili
 * @version 1.0: BankInfoDAO.java, v0.1 2018/11/28 11:32 PM lili Exp $
 */
public interface BankInfoDAO {

    List<BankInfoDO> query(Map parameters);

    int count(Map parameters);

    BankInfoDO selectByPrimaryKey(Long id);

    /**
     * 根据银行code查找银行信息
     * @param bankcode
     * @return
     */
    BankInfoDO selectByPrimaryCode(String bankcode);

}