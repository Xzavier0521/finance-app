package cn.zhishush.finance.core.dal.dao.activity;

import java.util.List;

import cn.zhishush.finance.core.dal.dao.base.BaseDAO;
import org.apache.ibatis.annotations.Param;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.vo.activity.StepRewardsDetailVo;
import cn.zhishush.finance.core.dal.dataobject.activity.StepRewardsActivityDO;

/**
 * <p>注释</p>
 * 
 * @author lili
 * @version $Id: StepRewardsActivityDAO.java, v0.1 2018/11/14 12:57 PM lili Exp
 *          $
 */
public interface StepRewardsActivityDAO extends BaseDAO<StepRewardsActivityDO, Long> {
    /**
     * 根据userId查询阶梯红包信息
     * 
     * @author panzhongkang
     * @date 2018/9/11 18:19
     */
    StepRewardsActivityDO selectOneByUserId(@Param("userId") Long userId);

    /**
     * 分页查询参与人员列表
     * 
     * @author panzhongkang
     * @date 2018/9/11 18:20
     */
    List<StepRewardsActivityDO> selectStepRewardList(@Param("page") Page<StepRewardsDetailVo> page);

    /**
     * 查询参与人数
     * 
     * @author panzhongkang
     * @date 2018/9/11 18:21
     */
    Long selectStepRewardsCount();

    /**
     * 根据userId查询阶梯红包信息ForUpdate
     * 
     * @author panzhongkang
     * @date 2018/9/12 11:19
     */
    StepRewardsActivityDO selectOneByUserIdForUpdate(@Param("userId") Long userId);

}