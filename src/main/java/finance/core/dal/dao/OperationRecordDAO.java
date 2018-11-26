package finance.core.dal.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.OperationRecordDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 注释
 * </p>
 * 
 * @author lili
 * @version :1.0 OperationRecordDAO.java.java, v 0.1 2018/9/28 下午1:37 lili Exp $
 */
public interface OperationRecordDAO extends BaseDAO<OperationRecordDO, Long> {

	/**
	 * 查询指定用户对指定产品的操作记录.
	 * 
	 * @param userIds
	 *            用户ID列表（如果==null，则不作为查询条件）
	 * @param productId
	 *            not null
	 * @param beginTime
	 *            not null
	 * @param endTime
	 *            not null
	 * @return
	 * @author hewenbin
	 * @version OperationRecordDAO.java, v1.0 2018年7月12日 下午7:33:14 hewenbin
	 */
	List<OperationRecordDO> selectCountByProductId(@Param("userIds") List<Long> userIds,
                                                   @Param("productId") Long productId, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/**
	 * 查询指定用户对指定产品的操作记录
	 * 
	 * @param parameters
	 *            查询参数
	 * @return List<OperationRecordDO>
	 */
	List<OperationRecordDO> query(Map<String, Object> parameters);

	/**
	 * 查询用户最新操作日志
	 * 
	 * @param userIds
	 *            用户id
	 * @return List<OperationRecord>
	 */
	List<OperationRecordDO> queryLatestLog(List<Long> userIds);
}