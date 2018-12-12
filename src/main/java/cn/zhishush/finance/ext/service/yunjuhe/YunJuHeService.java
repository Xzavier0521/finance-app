package cn.zhishush.finance.ext.service.yunjuhe;

import cn.zhishush.finance.ext.api.response.YunJuHeUnionLoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import cn.zhishush.finance.ext.api.request.YunJuHeUnionLoginRequest;

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
    @POST("app/uionLogin")
    Call<YunJuHeUnionLoginResponse> unionLogin(@Body YunJuHeUnionLoginRequest request);
}
