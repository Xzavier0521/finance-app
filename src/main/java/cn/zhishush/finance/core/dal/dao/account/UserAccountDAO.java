package cn.zhishush.finance.core.dal.dao.account;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.account.UserAccountDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: UserAccountDAO.java, v0.1 2018/11/14 12:58 PM lili Exp $
 */
// @CacheConfig(cacheNames = "account")
public interface UserAccountDAO extends BaseDAO<UserAccountDO, Long> {

	/**
	 * 充值.
	 * 
	 * @param userId
	 * @param addMoney
	 *            需要充的钱.
	 * @return
	 * @author hewenbin
	 * @version UserAccountDAO.java, v1.0 2018年8月22日 下午3:20:43 hewenbin
	 */
	// @CacheEvict(key = "'account_'.concat(#a0)")
	int chargeMoney(@Param("userId") Long userId, @Param("money") BigDecimal addMoney);

	/**
	 * 扣减可提现金额.
	 * 
	 * @param userId
	 * @param subMoney
	 *            需要扣减的钱
	 * @return
	 * @version UserAccountDAO.java, v2.0 2018年8月22日 上午11:13:48 hewenbin
	 * @version UserAccountDAO.java, v1.0 2018年7月22日 上午11:13:48 yaolei
	 */
	// @CacheEvict(key = "'account_'.concat(#a0)")
	int countDownMoney(@Param("userId") Long userId, @Param("money") BigDecimal subMoney);

	// @Cacheable(key="'account_'.concat(#a0)", unless= "#result == null")
	UserAccountDO getAccountByUserId(Long userId);

	/**
	 * 查询用户账户信息(通用)
	 * 
	 * @param parameters
	 *            查询参数
	 * @return List<UserAccountDO>
	 */
	List<UserAccountDO> query(Map<String, Object> parameters);
}