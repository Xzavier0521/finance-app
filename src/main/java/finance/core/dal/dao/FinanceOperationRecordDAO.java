package finance.core.dal.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import finance.core.dal.dataobject.FinanceOperationRecord;

/**
 *  <p>注释</p>
 * @author  lili
 * @version :1.0  FinanceOperationRecordDAO.java.java, v 0.1 2018/9/28 下午1:37 lili Exp $
 */
public interface FinanceOperationRecordDAO extends BaseDAO<FinanceOperationRecord, Long> {

    /**
     * 查询指定用户对指定产品的操作记录.
     * @param userIds 用户ID列表（如果==null，则不作为查询条件）
     * @param productId not null
     * @param beginTime not null
     * @param endTime not null
     * @return
     * @author hewenbin
     * @version FinanceOperationRecordDAO.java, v1.0 2018年7月12日 下午7:33:14 hewenbin
     */
    List<FinanceOperationRecord> selectCountByProductId(@Param("userIds") List<Long> userIds,
                                                        @Param("productId") Long productId,
                                                        @Param("beginTime") Date beginTime,
                                                        @Param("endTime") Date endTime);

    /**
     * 查询指定用户对指定产品的操作记录
     * @param parameters 查询参数
     * @return List<FinanceOperationRecord>
     */
    List<FinanceOperationRecord> query(Map<String, Object> parameters);

    /**
     *  查询用户最新操作日志
     * @param userIds 用户id
     * @return List<OperationRecord>
     */
    List<FinanceOperationRecord> queryLatestLog(List<Long> userIds);
}