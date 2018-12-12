package cn.zhishush.finance.domainservice.service.third;

import cn.zhishush.finance.domain.product.ProductInfo;
import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>云聚合联合登陆</p>
 *
 * @author lili
 * @version 1.0: YunJuHeService.java, v0.1 2018/11/28 7:59 PM PM lili Exp $
 */
public interface YunJuHeService {

    BasicResponse unionLogin(UserInfo userInfo, String realName, ProductInfo productInfo);
}
