package finance.mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;

import finance.model.po.FinanceUserAccount;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserAccountDAO.java, v0.1 2018/11/14 12:58 PM lili Exp $
 */
@CacheConfig(cacheNames = "account")
public interface FinanceUserAccountDAO extends BaseDAO<FinanceUserAccount, Long> {

    /**
     * 充值.
     * @param userId
     * @param addMoney 需要充的钱.
     * @return
     * @author hewenbin
     * @version FinanceUserAccountDAO.java, v1.0 2018年8月22日 下午3:20:43 hewenbin
     */
    @CacheEvict(key = "'account_'.concat(#a0)")
    int chargeMoney(@Param("userId") Long userId, @Param("money") BigDecimal addMoney);

    /**
     * 扣减可提现金额.
     * @param userId
     * @param subMoney 需要扣减的钱
     * @return
     * @version FinanceUserAccountDAO.java, v2.0 2018年8月22日 上午11:13:48 hewenbin
     * @version FinanceUserAccountDAO.java, v1.0 2018年7月22日 上午11:13:48 yaolei
     */
    @CacheEvict(key = "'account_'.concat(#a0)")
    int countDownMoney(@Param("userId") Long userId, @Param("money") BigDecimal subMoney);

    // @Cacheable(key="'account_'.concat(#a0)", unless= "#result == null")
    FinanceUserAccount getAccountByUserId(Long userId);

    /**
     * 查询用户账户信息(通用)
     * @param parameters 查询参数
     * @return List<FinanceUserAccount>
     */
    List<FinanceUserAccount> query(Map<String, Object> parameters);
}