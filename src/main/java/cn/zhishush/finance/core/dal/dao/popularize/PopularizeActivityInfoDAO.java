package cn.zhishush.finance.core.dal.dao.popularize;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeActivityInfoDO;

/**
 * <p>推广活动信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeActivityInfoDAO.java, v0.1 2018/12/8 12:08 AM lili Exp $
 */
public interface PopularizeActivityInfoDAO {

    List<PopularizeActivityInfoDO> query(Map parameters);

    int count(Map parameters);

    PopularizeActivityInfoDO selectByPrimaryKey(Long id);
}