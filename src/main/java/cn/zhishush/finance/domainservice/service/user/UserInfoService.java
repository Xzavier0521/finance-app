package cn.zhishush.finance.domainservice.service.user;

import java.util.List;

import cn.zhishush.finance.api.model.vo.userinfo.UserInfoVo;

/**
 * 用户信息服务接口.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月12日 下午5:17:57 hewenbin
 */
public interface UserInfoService {

    List<UserInfoVo> queryUserInfos(List<Long> userIds);

}
