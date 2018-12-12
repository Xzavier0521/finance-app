package cn.zhishush.finance.core.dal.dao.popularize;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeModuleInfoDO;

/**
 * <p>推广模块信息</p>
 * @author lili
 * @version 1.0: PopularizeModuleInfoDAO.java, v0.1 2018/12/7 12:11 AM lili Exp $
 */
public interface PopularizeModuleInfoDAO {

    List<PopularizeModuleInfoDO> query(Map parameters);

    int count(Map parameters);

    PopularizeModuleInfoDO selectByPrimaryKey(Long id);

}