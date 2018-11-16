package finance.core.dal.dao;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceUserPwdInfo;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserPwdInfoDAO.java, v0.1 2018/11/14 1:02 PM lili Exp $
 */
public interface FinanceUserPwdInfoDAO extends BaseDAO<FinanceUserPwdInfo, Long> {
    
    /**
     * @author hewenbin
     * @version FinanceUserPwdInfoDAO.java, v1.0 2018年7月11日 下午2:27:48 hewenbin
     */
    FinanceUserPwdInfo selectByUserId(@Param("userId") Long userId, @Param("pwdType") String pwdType);
    
}