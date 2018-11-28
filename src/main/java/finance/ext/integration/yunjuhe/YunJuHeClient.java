package finance.ext.integration.yunjuhe;

import finance.ext.api.request.YunJuHeUnionLoginRequest;
import finance.ext.api.response.YunJuHeUnionLoginResponse;

/**
 * <p>云聚合</p>
 *
 * @author lili
 * @version 1.0: YunJuHeClient.java, v0.1 2018/11/28 7:15 PM PM lili Exp $
 */
public interface YunJuHeClient {

    YunJuHeUnionLoginResponse unionLogin(YunJuHeUnionLoginRequest request);
}
