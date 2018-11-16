package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import finance.core.dal.dataobject.FinanceUserInfo;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceUserInfoDAO.java, v0.1 2018/11/14 1:01 PM lili Exp $
 */
//@CacheConfig(cacheNames = "userInfo")
public interface FinanceUserInfoDAO extends BaseDAO<FinanceUserInfo, Long> {

   // @Cacheable(key = "'useInfo_byId_'.concat(#a0)")
    FinanceUserInfo selectByPrimaryKey(Long id);

   // @CachePut(key = "'useInfo_byId_'.concat(#a0.id)")
    int updateByPrimaryKeySelective(FinanceUserInfo record);

    /**
     * 通过手机号查询用户信息.
     * @param mobileNum
     * @return
     * @author hewenbin
     * @version FinanceUserInfoDAO.java, v1.0 2018年7月9日 下午8:30:33 hewenbin
     */
   // @Cacheable(key = "'useInfo_byMobile_'.concat(#a0)", unless = "#result == null")
    FinanceUserInfo selectByMobile(String mobileNum);

    /**
     * 通过邀请码查询用户信息.
     * @param inviteCode
     * @return
     * @author hewenbin
     * @version FinanceUserInfoDAO.java, v1.0 2018年7月12日 上午9:52:07 hewenbin
     */
  //  @Cacheable(key = "'useInfo_byInvite_'.concat(#a0)", unless = "#result == null")
    FinanceUserInfo selectByInviteCode(String inviteCode);

    List<FinanceUserInfo> selectByPrimaryKeys(@Param("ids") List<Long> ids);

    List<FinanceUserInfo> selectLikeMobile(@Param("mobileNum") String mobileNum);

    /**
     *@author ：tangwei
     * @Title: selectInviterByUserId
     * @Description: 查询当前被邀请打卡人的上级
     * @param userId
     * @param signActivityCode 
     * @return
     * @return: String  
     */
    Long selectInviterByUserId(@Param("userId") Long userId,
                               @Param("signActivityCode") String signActivityCode);

}