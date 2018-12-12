package cn.zhishush.finance.core.dal.dao.log;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.core.dal.dataobject.log.UserFirstLoginLogDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: UserFirstLoginLogDAO.java, v0.1 2018/11/14 1:00 PM lili Exp $
 */
// @CacheConfig(cacheNames = "firstLoginLog")
public interface UserFirstLoginLogDAO extends BaseDAO<UserFirstLoginLogDO, Long> {

    /**
     * 查询首次登录信息.
     * 
     * @param userId
     * @param platformCode
     * @return
     * @author hewenbin
     * @version UserFirstLoginLogDAO.java, v1.0 2018年7月9日 下午9:03:56 hewenbin
     */
    // @Cacheable(key="'firstLoginLog_'.concat(#a0).concat(#a1)", unless= "#result
    // == null")
    UserFirstLoginLogDO selectByUserId(@Param("userId") Long userId,
                                       @Param("platformCode") String platformCode);
}