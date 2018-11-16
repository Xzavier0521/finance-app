package finance.core.dal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import finance.api.model.base.Page;
import finance.api.model.vo.StepRewardsDetailVo;
import finance.core.dal.dataobject.FinanceStepRewardsActivity;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: FinanceStepRewardsActivityDAO.java, v0.1 2018/11/14 12:57 PM lili Exp $
 */
public interface FinanceStepRewardsActivityDAO extends BaseDAO<FinanceStepRewardsActivity, Long> {
    /**
      * 根据userId查询阶梯红包信息
      * @author panzhongkang
      * @date 2018/9/11 18:19
      */
    FinanceStepRewardsActivity selectOneByUserId(@Param("userId") Long userId);

    /**
      * 分页查询参与人员列表
      * @author panzhongkang
      * @date 2018/9/11 18:20
      */
    List<FinanceStepRewardsActivity> selectStepRewardList(@Param("page") Page<StepRewardsDetailVo> page);

    /**
      * 查询参与人数
      * @author panzhongkang
      * @date 2018/9/11 18:21
      */
    Long selectStepRewardsCount();

    /**
     * 根据userId查询阶梯红包信息ForUpdate
     * @author panzhongkang
     * @date 2018/9/12 11:19
     */
    FinanceStepRewardsActivity selectOneByUserIdForUpdate(@Param("userId") Long userId);

}