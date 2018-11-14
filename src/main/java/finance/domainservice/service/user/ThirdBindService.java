package finance.domainservice.service.user;

import finance.api.model.response.ResponseResult;
import finance.model.po.FinanceThirdAccountInfo;

/**
 * 第三方绑定服务.
 * @author hewenbin
 * @version v1.0 2018年8月14日 下午2:36:13 hewenbin
 */
public interface ThirdBindService {

    /**
     * 绑定用户.
     * @param userId
     * @param openId
     * @param channel 第三方账号渠道，qq、wechat
     * @author hewenbin
     * @version ThirdBindService.java, v1.0 2018年8月14日 下午2:41:09 hewenbin
     */
    ResponseResult<Boolean> bindUser(Long userId, String openId, String channel,
                                     String wechatPubName);

    /**
     * 解绑第三方账户.
     * @param userId
     * @param openId
     * @param channel
     * @author hewenbin
     * @version ThirdBindService.java, v1.0 2018年8月14日 下午2:42:30 hewenbin
     */
    void unBindUser(Long userId, String channel);

    /**
     * 查询绑定用户.
     * @param channel  第三方账号渠道，qq、wechat， 不能为空
     * @param openId userId 和openId 至少一个不为空
     * @param userId userId 和openId 至少一个不为空
     * @return
     * @author hewenbin
     * @version ThirdBindService.java, v1.0 2018年8月17日 上午9:18:43 hewenbin
     */
    FinanceThirdAccountInfo queryBindAccount(String channel, String openId, Long userId);

    /**
     * 查询第三方授权openId.
     * @param channel  第三方账号渠道，qq、wechat
     * @param code 临时票据
     * @return openId
     * @author hewenbin
     * @version ThirdBindService.java, v1.0 2018年8月17日 上午9:23:38 hewenbin
     */
    String queryOpenInfo(String channel, String code, String openId);

}
