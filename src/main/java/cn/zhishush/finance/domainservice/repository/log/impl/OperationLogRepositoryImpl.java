package cn.zhishush.finance.domainservice.repository.log.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.core.dal.dao.log.OperationLogDAO;
import cn.zhishush.finance.core.dal.dataobject.log.OperationLogDO;
import cn.zhishush.finance.domain.log.OperationLog;
import cn.zhishush.finance.domainservice.converter.log.OperationLogConverter;
import cn.zhishush.finance.domainservice.repository.log.OperationLogRepository;

/**
 * <p>用户操作日志</p>
 *
 * @author lili
 * @version 1.0: OperationLogRepositoryImpl.java, v 0.1 2018/9/28 下午4:11 lili Exp $
 */
@Repository("operationLogRepository")
public class OperationLogRepositoryImpl implements OperationLogRepository {
    @Resource
    private OperationLogDAO financeOperationLogMapper;

    /**
     * 保存数据
     * @param operationLog 记录
     * @return int
     */
    @Override
    public int save(OperationLog operationLog) {
        return financeOperationLogMapper
            .insertSelective(OperationLogConverter.convert(operationLog));
    }

    /**
     * 批量保存
     * @param operationLogs 记录列表
     * @return int
     */
    @Override
    public int batchSave(List<OperationLog> operationLogs) {
        return financeOperationLogMapper
            .batchInsert(OperationLogConverter.convert2DoList(operationLogs));
    }

    /**
     * 查询用户最新操作日志
     * 
     * @param parentUserId 用户id列表
     * @param opCodes 操作码
     * @param pageNum 第几页
     * @param pageSize 每页记录数
     * @return List<OperationLogDO>
     */
    @Override
    public List<OperationLog> queryLatestLog(Long parentUserId, List<String> opCodes, int pageNum,
                                             int pageSize) {
        Page<OperationLogDO> page = new Page<>(pageSize, (long) pageNum);
        return OperationLogConverter
            .convert2List(financeOperationLogMapper.queryLatestLog(parentUserId, opCodes, page));
    }
}
