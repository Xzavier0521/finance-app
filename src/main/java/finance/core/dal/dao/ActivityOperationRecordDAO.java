package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.ActivityOperationRecordDO;

/**
 * <p>红包活动操作记录</p>
 *
 * @author lili
 * @version 1.0: ActivityOperationRecordDAO.java, v0.1 2018/11/26 1:56 PM lili Exp $
 */
public interface ActivityOperationRecordDAO {

    int insertSelective(ActivityOperationRecordDO record);

    List<ActivityOperationRecordDO> query(Map parameters);

    int count(Map parameters);

    ActivityOperationRecordDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityOperationRecordDO record);

}