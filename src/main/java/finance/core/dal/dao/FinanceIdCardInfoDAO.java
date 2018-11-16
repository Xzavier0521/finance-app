package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import finance.core.dal.dataobject.FinanceIdCardInfo;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceIdCardInfoDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
//@CacheConfig(cacheNames = "idCardInfo")
public interface FinanceIdCardInfoDAO extends BaseDAO<FinanceIdCardInfo, Long> {

    int insertSelective(FinanceIdCardInfo record);

    //@Cacheable(key = "'idCardInfo_byId_'.concat(#a0)", unless = "#result == null")
    FinanceIdCardInfo selectByPrimaryKey(Long id);

    /** 更新时userId必传*/
    int updateByPrimaryKeySelective(FinanceIdCardInfo record);

    /**
     * 根据userId查询身份证信息
     *
     * @param userId
     * @return
     * @throws
     * @author panzhongkang
     * @date 2018/8/21 18:35
     */
   // @Cacheable(key = "'idCardInfo_byUserId_'.concat(#a0)", unless = "#result == null")
    FinanceIdCardInfo selectByUserId(@Param("userId") Long userId);

    /**
     * 根据多条userId批量查询身份证信息
     *
     * @param userIds
     * @return
     * @throws
     * @author panzhongkang
     * @date 2018/8/21 18:37
     */
    List<FinanceIdCardInfo> selectByUserIdList(@Param("userIds") List<Long> userIds);

    /**
     * 查询已认证的身份证号的用户
     * @param idNum
     * @param authStatus
     * @return
     */
    FinanceIdCardInfo selectByAuthId(@Param("idNum") String idNum,
                                     @Param("authStatus") int authStatus);
}