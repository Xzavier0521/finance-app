package finance.domainservice.service.userinfo;

import java.util.Map;

import finance.api.model.response.ResponseResult;
import finance.domain.dto.ThirdLoginParamDto;

/**
 * 第三方账号绑定业务.
 * @author hewenbin
 * @version v1.0 2018年8月17日 上午10:41:50 hewenbin
 */
public interface ThirdBindBiz {

    /**
     * 绑定用户
     * @param userId
     * @param bindDto
     * @return
     */
    ResponseResult<Boolean> bindUser(Long userId, ThirdLoginParamDto bindDto);

    /**
     * 解绑第三方账户
     * @param userId
     * @param channel
     */
    void unBindUser(Long userId, String channel);

    /**
     * 获取openid
     * @param channel
     * @param code
     * @param openId
     * @return
     */
    ResponseResult<Map<String, String>> queryOpenId(String channel, String code, String openId);
}
