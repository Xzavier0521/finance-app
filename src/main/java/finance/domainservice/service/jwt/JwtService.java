package finance.domainservice.service.jwt;

import finance.model.po.FinanceUserInfo;

/**
 * JWT服务接口.
 * @author hewenbin
 * @version v1.0 2018年7月9日 下午2:24:17 hewenbin
 */
public interface JwtService {

	
	/**
	 * 保存jwt（header、redis）
	 * @param userInfo 存在
	 * @return jwt字符串
	 * @author hewenbin
	 * @version JwtService.java, v1.0 2018年7月9日 下午2:28:10 hewenbin
	 */
	String saveJwt(FinanceUserInfo userInfo);
	
	
	/**
	 * 刷新jwt缓存.
	 * @param jwtKey
	 * @return 是否刷新成功
	 * @author hewenbin
	 * @version JwtService.java, v1.0 2018年7月9日 下午3:09:01 hewenbin
	 */
	Boolean refreshJwt(String jwtKey);
	
	/**
	 * 判断该 jwt是否有效.
	 * @param jwtKey
	 * @return
	 * @author hewenbin
	 * @version JwtService.java, v1.0 2018年7月9日 下午3:20:22 hewenbin
	 */
	Boolean hasJwt(String jwtKey);
	
	/**
	 * 获取当前请求线程的用户信息.
	 * @return
	 * @author hewenbin
	 * @version JwtService.java, v1.0 2018年7月9日 下午3:53:16 hewenbin
	 */
	FinanceUserInfo getUserInfo();

	/**
	 * 移除内存中的缓存值.
	 * <pre>注意：业务不允许调用</pre>
	 * @author hewenbin
	 * @version JwtService.java v1.0 2018年9月12日 下午4:59:26 hewenbin
	 */
	void removeUserInfo();
}
