package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.CashBackConfigDO;

/**
 * <p>返佣配置</p>
 * @author lili
 * @version 1.0: CashBackConfigDAO.java, v0.1 2018/11/29 2:08 AM lili Exp $
 */
public interface CashBackConfigDAO {

    List<CashBackConfigDO> query(Map parameters);

    int count(Map parameters);

    CashBackConfigDO selectByPrimaryKey(Long id);
}