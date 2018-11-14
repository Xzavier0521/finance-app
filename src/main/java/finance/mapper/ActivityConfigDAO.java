package finance.mapper;

import java.util.List;
import java.util.Map;

import finance.model.po.ActivityConfigDO;

/**
 * <p>活动推广配置</p>
 * @author lili
 * @version $Id: ActivityConfigDAO.java, v0.1 2018/10/11 10:07 AM lili Exp $
 */
public interface ActivityConfigDAO {

    /**
     *  保存记录
     * @param record 记录
     * @return int
     */
    int insertSelective(ActivityConfigDO record);

    /**
     * 查询记录-根据主键
     * @param id 主键
     * @return  ActivityConfigDO
     */
    ActivityConfigDO selectByPrimaryKey(Long id);

    /**
     * 更新记录
     * @param record 记录
     * @return int
     */
    int updateByPrimaryKeySelective(ActivityConfigDO record);

    /**
     * 查询(支持分页)
     * @param parameters 查询条件
     * @return List<ActivityConfigDO>
     */
    List<ActivityConfigDO> query(Map<String, Object> parameters);

    /**
     * 统计记录数
     * @param parameters 查询条件
     * @return int
     */
    int count(Map<String, Object> parameters);
}