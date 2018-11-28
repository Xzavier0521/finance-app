package finance.ext.service.yunjuhe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import finance.ext.api.request.YunJuHeUnionLoginRequest;
import finance.ext.api.response.YunJuHeUnionLoginResponse;

/**
 * <p>云聚合</p>
 *
 * @author lili
 * @version 1.0: YunJuHeService.java, v0.1 2018/11/28 6:40 PM PM lili Exp $
 */
public interface YunJuHeService {

    /**
     * 联合登陆
     * @param request  请求参数
     * @return 返回结果 YunJuHeUnionLoginResponse
     */
    @POST("app/unionLogin")
    Call<YunJuHeUnionLoginResponse> unionLogin(@Body YunJuHeUnionLoginRequest request);
}
