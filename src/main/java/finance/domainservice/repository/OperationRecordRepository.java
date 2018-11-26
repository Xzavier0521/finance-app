package finance.domainservice.repository;

import finance.domain.log.OperationRecord;
import finance.core.dal.dataobject.OperationRecordDO;

import java.util.List;

/**
 * <p>
 * 操作记录
 * </p>
 * 
 * @author lili
 * @version :1.0 OperationRecordRepository.java.java, v 0.1 2018/9/27 下午8:41
 *          lili Exp $
 */
public interface OperationRecordRepository {

	/**
	 * 查询用户操作记录
	 * 
	 * @param userIds
	 *            用户id
	 * @return List<OperationRecordDO>
	 */
	List<OperationRecordDO> query(List<Long> userIds);

	/**
	 * 查询用户最新操作日志
	 * 
	 * @param userIds
	 *            用户id
	 * @return List<OperationRecord>
	 */
	List<OperationRecord> queryLatestLog(List<Long> userIds);

}
