package cn.zhishush.finance.core.dal.dao.log;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.log.BarrageMessageDO;

/**
 * <p>弹幕消息</p>
 *
 * @author lili
 * @version 1.0: BarrageMessageDAO.java, v 0.1 2018/9/29 上午10:08 lili Exp $
 */
public interface BarrageMessageDAO extends BaseDAO<BarrageMessageDO, Long> {

    /**
     * 查询总记录数
     * @param parameters 查询参数
     * @return int
     */
    int count(Map<String, Object> parameters);

    /**
     * 分页查询 List<BarrageMessageDO>
     * @param parameters 查询参数 	
     * @return List<BarrageMessageDO>
     */
    List<BarrageMessageDO> query(Map<String, Object> parameters);
}
