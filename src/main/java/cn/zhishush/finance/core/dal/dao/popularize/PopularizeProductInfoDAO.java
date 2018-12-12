package cn.zhishush.finance.core.dal.dao.popularize;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeProductInfoDO;

/**
 * <p>推广产品信息</p>
 * @author lili
 * @version 1.0: PopularizeProductInfoDAO.java, v0.1 2018/12/9 9:11 PM lili Exp $
 */
public interface PopularizeProductInfoDAO {

    List<PopularizeProductInfoDO> query(Map parameters);

    int count(Map parameters);

    PopularizeProductInfoDO selectByPrimaryKey(Long id);
}