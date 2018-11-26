package finance.core.dal.dao;

import java.util.List;
import java.util.Map;

import finance.core.dal.dataobject.AgentConfigDO;

/**
 * <p>
 * 注释代理配置
 * </p>
 *
 * @author lili
 * @version $Id: AgentConfigDAO.java, v0.1 2018/10/11 10:43 AM lili Exp $
 */
public interface AgentConfigDAO {

	/**
	 * 查询记录数
	 * 
	 * @param parameters
	 *            查询参数
	 * @return int
	 */
	int count(Map<String, Object> parameters);

	/**
	 * 查询-支持分页
	 * 
	 * @param parameters
	 *            查询参数
	 * @return List<AgentConfigDO>
	 */
	List<AgentConfigDO> query(Map<String, Object> parameters);

	/**
	 * 插入记录
	 * 
	 * @param record
	 *            记录
	 * @return int
	 */
	int insertSelective(AgentConfigDO record);

	/**
	 * 查询记录-根据主键
	 * 
	 * @param id
	 *            主键
	 * @return AgentConfigDO
	 */
	AgentConfigDO selectByPrimaryKey(Long id);

	/**
	 * 更新记录
	 * 
	 * @param record
	 *            记录
	 * @return int
	 */
	int updateByPrimaryKeySelective(AgentConfigDO record);
}