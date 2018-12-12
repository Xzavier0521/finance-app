package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.activity.ActivityCoinGameDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: ActivityCoinGameDAO.java, v0.1 2018/11/15 9:12 PM lili Exp $
 */
public interface ActivityCoinGameDAO {

    int insertSelective(ActivityCoinGameDO record);

    List<ActivityCoinGameDO> query(Map parameters);

    int count(Map parameters);

    ActivityCoinGameDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityCoinGameDO record);

}