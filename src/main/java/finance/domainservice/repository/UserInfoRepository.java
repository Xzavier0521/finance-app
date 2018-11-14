package finance.domainservice.repository;

import finance.domain.UserInfo;

import java.util.List;

/**
 *  <p>用户基本信息</p>
 * @author  lili
 * @version :1.0  UserInfoRepository.java.java, v 0.1 2018/9/27 下午8:40 lili Exp $
 */
public interface UserInfoRepository {

    /**
     * 查询用户信息列表
     * @param ids 用户id
     * @return List<FinanceUserInfo>
     */
    List<UserInfo> queryByCondition(List<Long> ids);

    /**
     * 根据邀请码查询用户信息
     * @param inviteCode 邀请码
     * @return UserInfo
     */
    UserInfo queryByCondition(String inviteCode);
}
