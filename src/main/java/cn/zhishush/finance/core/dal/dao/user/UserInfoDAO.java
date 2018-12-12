package cn.zhishush.finance.core.dal.dao.user;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: UserInfoDAO.java, v0.1 2018/11/14 1:01 PM lili Exp $
 */
// @CacheConfig(cacheNames = "userInfo")
public interface UserInfoDAO extends BaseDAO<UserInfoDO, Long> {

	// @Cacheable(key = "'useInfo_byId_'.concat(#a0)")
	@Override
	UserInfoDO selectByPrimaryKey(Long id);

	// @CachePut(key = "'useInfo_byId_'.concat(#a0.id)")
	@Override
	int updateByPrimaryKeySelective(UserInfoDO record);

	/**
	 * 通过手机号查询用户信息.
	 * 
	 * @param mobileNum
	 * @return
	 * @author hewenbin
	 * @version UserInfoDAO.java, v1.0 2018年7月9日 下午8:30:33 hewenbin
	 */
	// @Cacheable(key = "'useInfo_byMobile_'.concat(#a0)", unless = "#result ==
	// null")
	UserInfoDO selectByMobile(String mobileNum);

	/**
	 * 通过邀请码查询用户信息.
	 * 
	 * @param inviteCode
	 * @return
	 * @author hewenbin
	 * @version UserInfoDAO.java, v1.0 2018年7月12日 上午9:52:07 hewenbin
	 */
	// @Cacheable(key = "'useInfo_byInvite_'.concat(#a0)", unless = "#result ==
	// null")
	UserInfoDO selectByInviteCode(String inviteCode);

	List<UserInfoDO> selectByPrimaryKeys(@Param("ids") List<Long> ids);

	List<UserInfoDO> selectLikeMobile(@Param("mobileNum") String mobileNum);

	/**
	 * @author ：tangwei
	 * @Title: selectInviterByUserId
	 * @Description: 查询当前被邀请打卡人的上级
	 * @param userId
	 * @param signActivityCode
	 * @return
	 * @return: String
	 */
	Long selectInviterByUserId(@Param("userId") Long userId, @Param("signActivityCode") String signActivityCode);

	List<UserInfoDO> query(Map parameters);

	int count(Map parameters);
}