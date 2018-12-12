package cn.zhishush.finance.core.dal.dao.activity;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import cn.zhishush.finance.core.dal.dataobject.activity.StepRewardsAmountDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: StepRewardsAmountDAO.java, v0.1 2018/11/14 12:58 PM lili Exp $
 */
public interface StepRewardsAmountDAO extends BaseDAO<StepRewardsAmountDO, Long> {

	StepRewardsAmountDO selectByPrimaryKeyForUpdate(Long id);
}