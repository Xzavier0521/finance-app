package finance.core.dal.dao;

import finance.core.dal.dataobject.UserPwdInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: UserPwdInfoDAO.java, v0.1 2018/11/14 1:02 PM lili Exp $
 */
public interface UserPwdInfoDAO extends BaseDAO<UserPwdInfoDO, Long> {

	/**
	 * @author hewenbin
	 * @version UserPwdInfoDAO.java, v1.0 2018年7月11日 下午2:27:48 hewenbin
	 */
	UserPwdInfoDO selectByUserId(@Param("userId") Long userId, @Param("pwdType") String pwdType);

}