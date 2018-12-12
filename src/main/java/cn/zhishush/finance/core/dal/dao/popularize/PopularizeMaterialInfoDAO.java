package cn.zhishush.finance.core.dal.dao.popularize;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.popularize.PopularizeMaterialInfoDO;

/**
 * <p>推广素材信息</p>
 *
 * @author lili
 * @version 1.0: PopularizeMaterialInfoDAO.java, v0.1 2018/12/9 9:41 PM lili Exp $
 */
public interface PopularizeMaterialInfoDAO {

    List<PopularizeMaterialInfoDO> query(Map parameters);

    int count(Map parameters);

    PopularizeMaterialInfoDO selectByPrimaryKey(Long id);
}