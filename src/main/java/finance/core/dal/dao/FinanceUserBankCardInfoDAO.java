package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import finance.core.dal.dataobject.FinanceUserBankCardInfo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserBankCardInfoDAO.java, v0.1 2018/11/14 12:59 PM lili Exp $
 */
@CacheConfig(cacheNames = "bankCardInfo")
public interface FinanceUserBankCardInfoDAO extends BaseDAO<FinanceUserBankCardInfo, Long> {
    /**
     * 根据userId更新银行卡信息.
     * @param record
     * @return
     * @author hewenbin
     * @version FinanceUserBankCardInfoDAO.java, v1.0 2018年7月10日 下午7:51:54 hewenbin
     */
    @CacheEvict(key = "'bankCardInfo_'.concat(#a0.userId)")
    int updateByUserId(FinanceUserBankCardInfo record);

    /**
     * 查询用户的默认银行卡信息.
     * @param userId
     * @return
     * @author hewenbin
     * @version FinanceUserBankCardInfoDAO.java, v1.0 2018年7月10日 下午7:52:19 hewenbin
     */
    @Cacheable(key = "'bankCardInfo_'.concat(#a0)", unless = "#result == null")
    FinanceUserBankCardInfo selectDefaultBankCard(Long userId);

    /**
     * 查询指定用户的银行卡
     * @param userId not null
     * @param accountNo not null and ''
     * @return
     * @author hewenbin
     * @version FinanceUserBankCardInfoDAO.java, v1.0 2018年7月10日 下午8:01:24 hewenbin
     */
    FinanceUserBankCardInfo selectUserBankCard(@Param("userId") Long userId,
                                               @Param("accountNo") String accountNo);

    List<FinanceUserBankCardInfo> selectDefaultBankCardByUserIds(@Param("userIds") List<Long> userIds);

    /**
     * 通过银行卡账户姓名的“姓”匹配用户的默认银行卡.
     * @param firstName 示例：李
     * @return   List<FinanceUserBankCardInfo>
     */
    List<FinanceUserBankCardInfo> selectDefaultCardLikeFirstName(@Param("firstName") String firstName);
}