package cn.zhishush.finance.core.dal.dao.product;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.CreditCardApplyInfoDO;

/**
 * <p>信用卡申请信息</p>
 * @author lili
 * @version 1.0: CreditCardApplyInfoDAO.java, v0.1 2018/11/29 5:19 PM lili Exp $
 */
public interface CreditCardApplyInfoDAO {

    int insertSelective(CreditCardApplyInfoDO record);

    List<CreditCardApplyInfoDO> query(Map parameters);

    int count(Map parameters);

    CreditCardApplyInfoDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CreditCardApplyInfoDO record);
}