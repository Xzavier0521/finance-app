package finance.domainservice.service.businessinformation;

import java.util.List;
import java.util.Map;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.ActivityListVO;
import finance.api.model.vo.StepRewardsDetailVo;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: ActivityBiz.java, v0.1 2018/11/14 1:53 PM lili Exp $
 */
public interface ActivityBiz {
   /**
     *功能描述:活动列表
     * @author: moruihai
     * @date: 2018/9/3 14:48
     * @param: []
     * @return: java.util.Map<java.lang.String,java.util.List<finance.model.vo.ActivityListVO>>
     */
    Map<String,List<ActivityListVO>>  getActivityList();

    /**
      * 查询阶梯红包信息
      * @author panzhongkang
      * @date 2018/9/11 17:23
      */
    StepRewardsDetailVo queryStepRewardsInfo(Long userId, Page<StepRewardsDetailVo> page);

    /**
      * 参加阶梯红包活动
      * @author panzhongkang
      * @date 2018/9/11 17:23
      */
    ResponseResult<Boolean> saveStepRewardsActivty(Long userId);

    /**
      * 查询阶梯奖励规则列表
      * @author panzhongkang
      * @date 2018/9/11 17:24
      */
    List<Map<String,String>> queryStepRewardRefList();
}
