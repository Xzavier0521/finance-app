package finance.domainservice.repository;

import finance.domain.OperationLog;
import java.util.List;

/**
 * <p>用户操作日志</p>
 *
 * @author lili
 * @version 1.0: OperationLogRepository.java, v 0.1 2018/9/28 下午4:08 lili Exp $
 */
public interface OperationLogRepository {

    /**
     *  保存数据
     * @param operationLog  记录
     * @return int
     */
    int save(OperationLog operationLog);

    /**
     * 批量保存
     * @param operationLogs 记录列表
     * @return int
     */
    int batchSave(List<OperationLog> operationLogs);

    /**
     * 查询用户最新操作日志
     * @param parentUserId 用户id列表
     * @param opCodes  操作码
     * @param pageNum 第几页
     * @param pageSize  每页记录数
     * @return   List<FinanceOperationLog>
     */
    List<OperationLog> queryLatestLog(Long parentUserId, List<String> opCodes, int pageNum, int pageSize);
}
