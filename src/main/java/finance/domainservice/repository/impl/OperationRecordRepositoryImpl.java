package finance.domainservice.repository.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import finance.domain.log.OperationRecord;
import finance.domainservice.converter.OperationRecordConverter;
import finance.core.dal.dao.OperationRecordDAO;
import finance.core.dal.dataobject.OperationRecordDO;
import finance.domainservice.repository.OperationRecordRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 操作记录
 * </p>
 *
 * @author lili
 * @version :1.0 OperationRecordRepositoryImpl.java.java, v 0.1 2018/9/27 下午8:41
 *          lili Exp $
 */
@Repository("operationRecordRepository")
public class OperationRecordRepositoryImpl implements OperationRecordRepository {

	@Resource
	private OperationRecordDAO financeOperationRecordMapper;

	/**
	 * 查询用户操作记录
	 *
	 * @param userIds
	 *            用户id
	 * @return List<OperationRecordDO>
	 */
	@Override
	public List<OperationRecordDO> query(List<Long> userIds) {
		List<OperationRecordDO> operationRecordDOList = Lists.newArrayList();
		Map<String, Object> parameters = Maps.newHashMap();
		if (CollectionUtils.isNotEmpty(userIds)) {
			parameters.put("userIds", userIds);
			operationRecordDOList = financeOperationRecordMapper.query(parameters);
		}
		return operationRecordDOList;
	}

	/**
	 * 查询用户最新操作日志
	 *
	 * @param userIds
	 *            用户id
	 * @return List<OperationRecord>
	 */
	@Override
	public List<OperationRecord> queryLatestLog(List<Long> userIds) {
		return OperationRecordConverter.convert2List(financeOperationRecordMapper.queryLatestLog(userIds));
	}
}
