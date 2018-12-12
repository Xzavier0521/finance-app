package cn.zhishush.finance.core.dal.dao.log;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dataobject.log.OperationLogDO;

/**
 * <p>注释</p>
 *
 * @author lili
 * @version 1.0: OperationLogDAO.java, v 0.1 2018/9/28 下午4:02 lili Exp $
 */
public interface OperationLogDAO {

    /**
     * 插入数据
     * @param operationLogDO 记录
     * @return int
     */
    int insertSelective(OperationLogDO operationLogDO);

    /**
     * 更新记录
     * @param operationLogDO 记录
     * @return int
     */
    int updateByPrimaryKeySelective(OperationLogDO operationLogDO);

    int batchInsert(List<OperationLogDO> operationLogDOS);

    /**
     * 查询用户最新操作日志
     * @param parentUserId 用户id
     * @param opCodes 操作码
     * @return List<OperationLogDO>
     */
    List<OperationLogDO> queryLatestLog(@Param("parentUserId") Long parentUserId,
                                        @Param("opCodes") List<String> opCodes,
                                        @Param("page") Page<OperationLogDO> page);
}
