package finance.mapper;

import finance.model.po.FinanceStepRewardsAmount;
/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceStepRewardsAmountDAO.java, v0.1 2018/11/14 12:58 PM lili Exp $
 */
public interface FinanceStepRewardsAmountDAO extends BaseDAO<FinanceStepRewardsAmount, Long> {

    FinanceStepRewardsAmount selectByPrimaryKeyForUpdate(Long id);
}