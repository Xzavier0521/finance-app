package cn.zhishush.finance.core.dal.dao.account;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.account.FinanceThirdAccountInfo;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: FinanceThirdAccountInfoDAO.java, v0.1 2018/11/14 12:58 PM lili Exp $
 */
public interface FinanceThirdAccountInfoDAO extends BaseDAO<FinanceThirdAccountInfo, Long> {

    FinanceThirdAccountInfo selectOneBySelective(FinanceThirdAccountInfo record);

    List<FinanceThirdAccountInfo> selectListBySelective(FinanceThirdAccountInfo record);

    /**
     * 通过唯一索引更新status
     * @param record
     * @return
     */
    int updateByUniqKey(FinanceThirdAccountInfo record);
}