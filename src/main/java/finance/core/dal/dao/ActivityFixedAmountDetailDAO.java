package finance.core.dal.dao;

import finance.core.dal.dataobject.ActivityFixedAmountDetailDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * <p>
 * 活动明细
 * </p>
 * 
 * @author lili
 * @version $Id: ActivityFixedAmountDetailDAO.java, v0.1 2018/11/25 10:50 AM
 *          lili Exp $
 */
public interface ActivityFixedAmountDetailDAO extends BaseDAO<ActivityFixedAmountDetailDO, Long> {

	/**
	 * 功能描述:查询活动的明细
	 * 
	 * @author: moruihai
	 * @date: 2018/9/11 15:38
	 * @param: [activityId]x
	 * @return: java.util.List<finance.core.dal.dataobject.ActivityFixedAmountDetailDO>
	 */
	List<ActivityFixedAmountDetailDO> selectByActivityId(@Param("activityId") Long activityId);
	/**
	 * 功能描述:查询无用户的活动
	 * 
	 * @author: moruihai
	 * @date: 2018/9/13 11:26
	 * @param: [activityId]
	 * @return: java.util.List<finance.core.dal.dataobject.ActivityFixedAmountDetailDO>
	 */
	List<ActivityFixedAmountDetailDO> selectNoUserByActivityId(@Param("activityId") Long activityId);

}