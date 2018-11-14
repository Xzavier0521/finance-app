package finance.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.model.po.FinanceActivityFixedAmountMain;

/**
 * <p>固定金额活动</p>
 * @author lili
 * @version $Id: FinanceActivityFixedAmountMainDAO.java, v0.1 2018/11/14 12:24 PM lili Exp $
 */
public interface FinanceActivityFixedAmountMainDAO extends
                                                   BaseDAO<FinanceActivityFixedAmountMain, Long> {
    /**
      *功能描述:查询用户最近一次参加活动
      * @author: moruihai
      * @date: 2018/9/10 16:57
      * @param: [userId, financeActivityFixedAmountMainPage]
      * @return: finance.model.po.FinanceActivityFixedAmountMain
      */
    FinanceActivityFixedAmountMain selectByUserId(@Param("userId") Long userId,
                                                  @Param("page") Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage);

    /**
      *功能描述:查询完成固定金额活动人数
      * @author: moruihai
      * @date: 2018/9/10 16:55
      * @param: [status]
      * @return: java.lang.Long
      */
    Long queryJoinFixedAmountActivityCount();

    /**
      *功能描述:分页查询完成固定金额活动信息
      * @author: moruihai
      * @date: 2018/9/10 17:00
      * @param: [status, financeActivityFixedAmountMainPage]
      * @return: java.util.List<finance.model.po.FinanceActivityFixedAmountMain>
      */
    List<FinanceActivityFixedAmountMain> queryJoinFixedAmountActivityInfo(@Param("page") Page<FinanceActivityFixedAmountMain> financeActivityFixedAmountMainPage);

    /**
      *功能描述:行级加锁
      * @author: moruihai
      * @date: 2018/9/12 15:31
      * @param: [userId, status]
      * @return: finance.model.po.FinanceActivityFixedAmountMain
      */
    FinanceActivityFixedAmountMain selectForUpdate(@Param("id") Long userId);

}