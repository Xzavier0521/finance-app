package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.UserBankCardInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserBankCardInfoDAO.java, v0.1 2018/11/14 12:59 PM lili Exp $
 */
// @CacheConfig(cacheNames = "bankCardInfo")
public interface UserBankCardInfoDAO extends BaseDAO<UserBankCardInfoDO, Long> {
	/**
	 * 根据userId更新银行卡信息.
	 * 
	 * @param record
	 * @return
	 * @author hewenbin
	 * @version UserBankCardInfoDAO.java, v1.0 2018年7月10日 下午7:51:54 hewenbin
	 */
	// @CacheEvict(key = "'bankCardInfo_'.concat(#a0.userId)")
	int updateByUserId(UserBankCardInfoDO record);

	/**
	 * 查询用户的默认银行卡信息.
	 * 
	 * @param userId
	 * @return
	 * @author hewenbin
	 * @version UserBankCardInfoDAO.java, v1.0 2018年7月10日 下午7:52:19 hewenbin
	 */
	// @Cacheable(key = "'bankCardInfo_'.concat(#a0)", unless = "#result == null")
	UserBankCardInfoDO selectDefaultBankCard(Long userId);

	/**
	 * 查询指定用户的银行卡
	 * 
	 * @param userId
	 *            not null
	 * @param accountNo
	 *            not null and ''
	 * @return
	 * @author hewenbin
	 * @version UserBankCardInfoDAO.java, v1.0 2018年7月10日 下午8:01:24 hewenbin
	 */
	UserBankCardInfoDO selectUserBankCard(@Param("userId") Long userId, @Param("accountNo") String accountNo);

	List<UserBankCardInfoDO> selectDefaultBankCardByUserIds(@Param("userIds") List<Long> userIds);

	/**
	 * 通过银行卡账户姓名的“姓”匹配用户的默认银行卡.
	 * 
	 * @param firstName
	 *            示例：李
	 * @return List<UserBankCardInfoDO>
	 */
	List<UserBankCardInfoDO> selectDefaultCardLikeFirstName(@Param("firstName") String firstName);
}