package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.core.dal.dataobject.FinanceOperationLog;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: FinanceOperationLogDAO.java, v 0.1 2018/9/28 下午4:02 lili Exp $
 */
public interface FinanceOperationLogDAO {

    /**
     * 插入数据
     * @param financeOperationLog 记录
     * @return int
     */
    int insertSelective(FinanceOperationLog financeOperationLog);

    /**
     * 更新记录
     * @param financeOperationLog 记录
     * @return int
     */
    int updateByPrimaryKeySelective(FinanceOperationLog financeOperationLog);

    int batchInsert(List<FinanceOperationLog> financeOperationLogs);

    /**
     * 查询用户最新操作日志
     *
     * @param parentUserId 用户id
     * @param opCodes 操作码
     * @return List<FinanceOperationLog>
     */
    List<FinanceOperationLog> queryLatestLog(@Param("parentUserId") Long parentUserId,
                                             @Param("opCodes") List<String> opCodes,
                                             @Param("page") Page<FinanceOperationLog> page);
}
