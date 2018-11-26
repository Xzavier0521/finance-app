package finance.domainservice.service.jwt;

import finance.core.dal.dataobject.UserInfoDO;

/**
 * JWT服务接口.
 * 
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午2:24:17 hewenbin
 */
public interface JwtService {

	/**
	 * 保存jwt（header、redis）
	 * 
	 * @param userInfo
	 *            存在
	 * @return jwt字符串
	 * @author hewenbin
	 */
	String saveJwt(UserInfoDO userInfo);

	/**
	 * 刷新jwt缓存.
	 * 
	 * @param jwtKey
	 * @return 是否刷新成功
	 * @author hewenbin
	 */
	Boolean refreshJwt(String jwtKey);

	/**
	 * 判断该 jwt是否有效.
	 * 
	 * @param jwtKey
	 * @return
	 * @author hewenbin
	 */
	Boolean hasJwt(String jwtKey);

	/**
	 * 获取当前请求线程的用户信息.
	 * 
	 * @return
	 * @author hewenbin
	 */
	UserInfoDO getUserInfo();

	/**
	 * 移除内存中的缓存值.
	 * 
	 * <pre>
	 * 注意：业务不允许调用
	 * </pre>
	 * 
	 * @author hewenbin
	 */
	void removeUserInfo();
}
