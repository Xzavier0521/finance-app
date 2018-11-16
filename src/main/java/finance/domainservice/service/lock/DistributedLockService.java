package finance.domainservice.service.lock;

import finance.api.model.response.BasicResponse;
import finance.domain.coin.PayCoinCondition;

/**
 * <p>支付金币进行业务操作</p>
 *
 * @author lili
 * @version 1.0: DistributedLockService.java, v0.1 2018/11/15 10:10 PM PM lili Exp $
 */
public interface DistributedLockService {

    /**
     *  支付金币进行业务操作
     * @param payCoinCondition 参数
     * @return  BasicResponse
     */
    BasicResponse process(PayCoinCondition payCoinCondition);
}
