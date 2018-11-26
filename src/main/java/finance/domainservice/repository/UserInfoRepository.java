package finance.domainservice.repository;

import java.util.List;

import finance.domain.user.UserInfo;

/**
 * <p>
 * 用户基本信息
 * </p>
 * 
 * @author lili
 * @version :1.0 UserInfoRepository.java.java, v 0.1 2018/9/27 下午8:40 lili Exp $
 */
public interface UserInfoRepository {

	/**
	 * 查询用户信息列表
	 * 
	 * @param ids
	 *            用户id
	 * @return List<UserInfoDO>
	 */
	List<UserInfo> queryByCondition(List<Long> ids);

	/**
	 * 根据邀请码查询用户信息
	 * 
	 * @param inviteCode
	 *            邀请码
	 * @return UserInfo
	 */
	UserInfo queryByCondition(String inviteCode);

	/**
	 * 根据手机号码查询用户信息
	 * 
	 * @param mobileNum
	 *            手机号码
	 * @return UserInfo
	 */
	UserInfo queryByMobileNum(String mobileNum);
}
