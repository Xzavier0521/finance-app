package cn.zhishush.finance.domainservice.service.wechat;

import cn.zhishush.finance.api.model.vo.weixin.WeiXinUserInfoDetailVO;
import cn.zhishush.finance.domain.user.UserInfo;

/**
 * <p>微信公众号用户信息查询</p>
 *
 * @author lili
 * @version 1.0: WeiXinUserInfoQueryService.java, v0.1 2018/12/2 12:39 PM PM lili Exp $
 */
public interface WeiXinUserInfoQueryService {

    WeiXinUserInfoDetailVO query(UserInfo userInfo);
    
}
