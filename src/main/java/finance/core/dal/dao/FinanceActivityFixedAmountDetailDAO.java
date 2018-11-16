package finance.core.dal.dao;

import finance.core.dal.dataobject.FinanceActivityFixedAmountDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FinanceActivityFixedAmountDetailDAO extends BaseDAO<FinanceActivityFixedAmountDetail, Long> {

    /**
      *功能描述:查询活动的明细
      * @author: moruihai
      * @date: 2018/9/11 15:38
      * @param: [activityId]x
      * @return: java.util.List<finance.core.dal.dataobject.FinanceActivityFixedAmountDetail>
      */
    List<FinanceActivityFixedAmountDetail> selectByActivityId(@Param("activityId") Long activityId);
    /**
      *功能描述:查询无用户的活动
      * @author: moruihai
      * @date: 2018/9/13 11:26
      * @param: [activityId]
      * @return: java.util.List<finance.core.dal.dataobject.FinanceActivityFixedAmountDetail>
      */
    List<FinanceActivityFixedAmountDetail> selectNoUserByActivityId(@Param("activityId") Long activityId);



}