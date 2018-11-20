package finance.ext.service.kameng;

import finance.ext.api.request.KaMengUserApplyCreditCardRequest;
import finance.ext.api.response.KaMengUserApplyCreditCardResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * <p>注释</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardDetailService.java, v0.1 2018/11/19 下午6:03 PM user Exp $
 */
public interface UserApplyCreditCardDetailService {

    @POST("qckjgzhManager/DownUser/Add.do")
    Call<KaMengUserApplyCreditCardResponse> applyCreditCard(@Body KaMengUserApplyCreditCardRequest request);
}
