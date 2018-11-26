package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.FinanceThirdAccountInfo;
/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: FinanceThirdAccountInfoDAO.java, v0.1 2018/11/14 12:58 PM lili
 *          Exp $
 */
public interface FinanceThirdAccountInfoDAO extends BaseDAO<FinanceThirdAccountInfo, Long> {

	FinanceThirdAccountInfo selectOneBySelective(FinanceThirdAccountInfo record);

	List<FinanceThirdAccountInfo> selectListBySelective(FinanceThirdAccountInfo record);

	/**
	 * 通过唯一索引更新status.
	 * 
	 * @param record
	 *            channel_openId, channel_userId
	 * @return
	 * @author hewenbin
	 * @version FinanceThirdAccountInfoDAO.java, v1.0 2018年8月17日 上午9:53:19 hewenbin
	 */
	int updateByUniqKey(FinanceThirdAccountInfo record);
}