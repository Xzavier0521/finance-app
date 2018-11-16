package finance.domainservice.service.trans;

import java.math.BigDecimal;

import finance.api.model.response.ResponseResult;
import finance.core.dal.dataobject.FinanceUserAccount;

/**
 * 账号服务类.
 * @author hewenbin
 * @version v1.0 2018年8月22日 下午2:52:04 hewenbin
 */
public interface AccountService {

    /**
     * 创建账户.
     * @param account
     * @return
     * @author hewenbin
     * @version AccountService.java, v1.0 2018年8月22日 下午2:53:31 hewenbin
     */
    ResponseResult<FinanceUserAccount> createAccountInfo(Long userId);

    /**
     * 充值.
     * @param reason 充值备注
     * @param userId
     * @param addMoney 充多少钱
     * @return
     * @author hewenbin
     * @version AccountService.java, v1.0 2018年8月22日 下午3:18:33 hewenbin
     */
    void charge(String reason, Long userId, BigDecimal addMoney, String mobileNum);

}
