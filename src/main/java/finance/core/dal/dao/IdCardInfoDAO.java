package finance.core.dal.dao;

import java.util.List;

import finance.core.dal.dataobject.IdCardInfoDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version $Id: IdCardInfoDAO.java, v0.1 2018/11/14 12:47 PM lili Exp $
 */
// @CacheConfig(cacheNames = "idCardInfo")
public interface IdCardInfoDAO extends BaseDAO<IdCardInfoDO, Long> {

    int insertSelective(IdCardInfoDO record);

    // @Cacheable(key = "'idCardInfo_byId_'.concat(#a0)", unless = "#result ==
    // null")
    IdCardInfoDO selectByPrimaryKey(Long id);

    /** 更新时userId必传 */
    int updateByPrimaryKeySelective(IdCardInfoDO record);

    /**
     * 根据userId查询身份证信息
     *
     * @param userId
     * @return
     * @throws @author
     *             panzhongkang
     * @date 2018/8/21 18:35
     */
    // @Cacheable(key = "'idCardInfo_byUserId_'.concat(#a0)", unless = "#result ==
    // null")
    IdCardInfoDO selectByUserId(@Param("userId") Long userId);

    /**
     * 根据多条userId批量查询身份证信息
     *
     * @param userIds
     * @return
     * @throws @author
     *             panzhongkang
     * @date 2018/8/21 18:37
     */
    List<IdCardInfoDO> selectByUserIdList(@Param("userIds") List<Long> userIds);

    /**
     * 查询已认证的身份证号的用户
     * 
     * @param idNum
     * @param authStatus
     * @return
     */
    IdCardInfoDO selectByAuthId(@Param("idNum") String idNum, @Param("authStatus") int authStatus);
}