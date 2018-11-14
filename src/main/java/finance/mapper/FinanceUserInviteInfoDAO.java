package finance.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.api.model.vo.InviteOrdersVo;
import finance.api.model.vo.StepRewardsDetailVo;
import finance.model.po.FinanceUserInviteInfo;

/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceUserInviteInfoDAO.java.java, v 0.1 2018/9/28 下午8:51 lili Exp $
 */
public interface FinanceUserInviteInfoDAO extends BaseDAO<FinanceUserInviteInfo, Long> {

    /**
     * @param parentUserIds
     * @param page == null 是不分页，查全表
     * @param beginTime ==null 时表示不限制
     * @return
     * @author hewenbin
     * @version FinanceUserInviteInfoDAO.java, v1.0 2018年7月18日 上午10:46:04 hewenbin
     */
    List<FinanceUserInviteInfo> selectByParentIds(@Param("parentIds") List<Long> parentUserIds,
                                                  @Param("page") Page<FinanceUserInviteInfo> page,
                                                  @Param("beginTime") String beginTime);

    /**
     * 查询一级邀请人数
     * @param userIds
     * @param beginTime  ==null 时表示不限制
     * @return
     * @author hewenbin
     * @version FinanceUserInviteInfoDAO.java, v1.0 2018年7月18日 上午10:46:29 hewenbin
     */
    Long selectCountByParentId(@Param("parentIds") List<Long> userIds,
                               @Param("beginTime") String beginTime);

    Long countFirstInviteNum(@Param("parentIds") List<Long> userIds);

    List<FinanceUserInviteInfo> selectFirstInviteUsers(@Param("parentIds") List<Long> userIds);

    /**
     * 查询二级人数
     * @param userIds
     * @param beginTime  ==null 时表示不限制
     * @return
     * @author hewenbin
     * @version FinanceUserInviteInfoDAO.java, v1.0 2018年7月18日 上午10:46:29 hewenbin
     */
    Long selectCountByGrandParentId(@Param("grandParentIds") List<Long> userIds,
                                    @Param("beginTime") String beginTime);

    /**
     * 查询二级人数
     * @param userIds
     * @param beginTime  ==null 时表示不限制
     * @return List<FinanceUserInviteInfo>
     */
    List<FinanceUserInviteInfo> selectByGrandParentId(@Param("grandParentIds") List<Long> userIds,
                                                      @Param("beginTime") String beginTime,
                                                      @Param("page") Page<FinanceUserInviteInfo> page);

    /**
      *功能描述:根据用户Id和当前时间查询最新一条推广人员
      * @author: moruihai
      * @date: 2018/8/16 14:20
      * @param: [userId, searchDate]
      * @return: finance.model.po.FinanceUserInviteInfo
      */
    FinanceUserInviteInfo selectByParentUserIdAndDate(@Param("userId") Long userId,
                                                      @Param("searchDate") String searchDate);

    /**
     * 查询邀请人数排行榜.
     * <pre>注意：目前是当月榜，如果后期有时间调整，可以将时间抽取出来作为参数</>
     * @param page
     * @return
     * @author hewenbin
     * @version FinanceUserInviteInfoDAO.java, v1.0 2018年8月19日 上午10:11:22 hewenbin
     */
    List<InviteOrdersVo> selectInviteOrders(@Param("page") Page<InviteOrdersVo> page);

    /**
     * 根据邀请类型查询一级邀请人id
     * XXX注意大数据量问题
     * @param parentId
     * @param page
     * @return
     * @author panzhongkang
     * @version FinanceUserInviteInfoDAO.java, v1.0 2018年9月11日 上午15:46:29 panzhongkang
     */
    List<Long> selectListByType(@Param("parentId") Long parentId,
                                @Param("inviteType") Integer inviteType,
                                @Param("page") Page<StepRewardsDetailVo> page);

    /**
      * 根据userId查询用户的邀请信息
      * @param userId
      * @author panzhongkang
      * @date 2018/9/12 11:33
      */
    FinanceUserInviteInfo selectOneByUserId(@Param("userId") Long userId);

    /**
     * 分页查询邀请信息列表
     * @param parameters 查询参数
     * @return FinanceUserInviteInfo
     */
    List<FinanceUserInviteInfo> query(Map<String, Object> parameters);

    /**
     * 查询总记录数
     * @param parameters 查询参数
     * @return int
     */
    int count(Map<String, Object> parameters);

    /**
     * 更新是否支付金币标志
     * @param userId
     * @return
     */
    int updatePayCoinFlag(Long userId);

}