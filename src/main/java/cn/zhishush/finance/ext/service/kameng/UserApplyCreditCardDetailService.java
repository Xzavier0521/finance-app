package cn.zhishush.finance.ext.service.kameng;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import cn.zhishush.finance.ext.api.response.KaMengUserApplyCreditCardResponse;

/**
 * <p>注释</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardDetailService.java, v0.1 2018/11/19 下午6:03 PM user Exp $
 */
public interface UserApplyCreditCardDetailService {

    @GET("qckjgzhManager/DownUser/Add.do")
    Call<KaMengUserApplyCreditCardResponse> applyCreditCard(@QueryMap Map<String, Object> map);
}
