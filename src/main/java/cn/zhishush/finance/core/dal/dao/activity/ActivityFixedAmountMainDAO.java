package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dataobject.activity.ActivityFixedAmountMainDO;

/**
 * <p>固定金额活动</p>
 * 
 * @author lili
 * @version $Id: ActivityFixedAmountMainDAO.java, v0.1 2018/11/14 12:24 PM lili
 *          Exp $
 */
public interface ActivityFixedAmountMainDAO extends BaseDAO<ActivityFixedAmountMainDO, Long> {
    /**
     * 功能描述:查询用户最近一次参加活动
     * 
     * @author: moruihai
     * @date: 2018/9/10 16:57
     * @param: [userId,
     *             financeActivityFixedAmountMainPage]
     * @return: ActivityFixedAmountMainDO
     */
    ActivityFixedAmountMainDO selectByUserId(@Param("userId") Long userId,
                                             @Param("page") Page<ActivityFixedAmountMainDO> financeActivityFixedAmountMainPage);

    /**
     * 功能描述:查询完成固定金额活动人数
     * 
     * @author: moruihai
     * @date: 2018/9/10 16:55
     * @param: [status]
     * @return: java.lang.Long
     */
    Long queryJoinFixedAmountActivityCount();

    /**
     * 功能描述:分页查询完成固定金额活动信息
     * 
     * @author: moruihai
     * @date: 2018/9/10 17:00
     * @param: [status,
     *             financeActivityFixedAmountMainPage]
     * @return: java.util.List<ActivityFixedAmountMainDO>
     */
    List<ActivityFixedAmountMainDO> queryJoinFixedAmountActivityInfo(@Param("page") Page<ActivityFixedAmountMainDO> financeActivityFixedAmountMainPage);

    /**
     * 功能描述:行级加锁
     * 
     * @author: moruihai
     * @date: 2018/9/12 15:31
     * @param: [userId,
     *             status]
     * @return: ActivityFixedAmountMainDO
     */
    ActivityFixedAmountMainDO selectForUpdate(@Param("id") Long userId);

}