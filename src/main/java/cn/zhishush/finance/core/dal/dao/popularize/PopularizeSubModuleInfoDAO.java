package cn.zhishush.finance.core.dal.dao.popularize;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeSubModuleInfoDO;

/**
 * <p>推广子模块信息</p>
 * @author lili
 * @version 1.0: PopularizeSubModuleInfoDAO.java, v0.1 2018/12/8 12:00 AM lili Exp $
 */
public interface PopularizeSubModuleInfoDAO {

    List<PopularizeSubModuleInfoDO> query(Map parameters);

    int count(Map parameters);

    PopularizeSubModuleInfoDO selectByPrimaryKey(Long id);

}