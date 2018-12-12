package cn.zhishush.finance.core.dal.dao.third;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.third.ThirdChannelConfigDO;

/**
 * <p>第三方渠道配置</p>
 * @author lili
 * @version 1.0: ThirdChannelConfigDAO.java, v0.1 2018/12/5 6:15 PM lili Exp $
 */
public interface ThirdChannelConfigDAO {

    List<ThirdChannelConfigDO> query(Map parameters);

    int count(Map parameters);

    ThirdChannelConfigDO selectByPrimaryKey(Long id);

}