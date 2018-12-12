package cn.zhishush.finance.core.dal.dao.order;

import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.dal.dataobject.product.LoanApplyInfoDO;
/**
 * <p>贷款申请记录</p>
 * @author lili
 * @version 1.0: LoanApplyInfoDAO.java, v0.1 2018/11/29 3:31 AM lili Exp $
 */
public interface LoanApplyInfoDAO {

    int insertSelective(LoanApplyInfoDO record);

    List<LoanApplyInfoDO> query(Map parameters);

    int count(Map parameters);

    LoanApplyInfoDO selectByPrimaryKey(Long id);

}