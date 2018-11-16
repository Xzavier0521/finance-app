package finance.domainservice.service.coin;

import finance.api.model.response.BasicResponse;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: CoinPayService.java, v0.1 2018/11/15 9:56 PM PM lili Exp $
 */
public interface CoinPayService {
    BasicResponse payCoin(Long userId, String reason);
}
