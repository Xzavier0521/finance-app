package finance.domainservice.repository;

import com.google.common.collect.Lists;
import finance.domain.OperationRecord;
import finance.model.po.FinanceOperationRecord;

import java.util.List;

/**
 *  <p>操作记录</p>
 * @author  lili
 * @version :1.0  OperationRecordRepository.java.java, v 0.1 2018/9/27 下午8:41 lili Exp $
 */
public interface OperationRecordRepository {

    /**
     *  查询用户操作记录
     * @param userIds 用户id
     * @return List<FinanceOperationRecord>
     */
    List<FinanceOperationRecord> query(List<Long> userIds);

    /**
     *  查询用户最新操作日志
     * @param userIds 用户id
     * @return List<OperationRecord>
     */
    List<OperationRecord> queryLatestLog(List<Long> userIds);


}
