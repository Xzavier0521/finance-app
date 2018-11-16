package finance.domainservice.service.trans;

import java.util.Map;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.FixedAmountPageVO;
import finance.domain.dto.FixedAmountPageDto;
import finance.domain.dto.LoginParamDto;
import finance.core.dal.dataobject.FinanceUserInfo;

/**
  * 邀请红包活动Service
  * @author panzhongkang
  * @date 2018/9/10 14:37
  */
public interface InviteActivityService {

    /**
      * 阶梯红包活动奖励
      * @author panzhongkang
      * @date 2018/9/12 11:07
     * @param userInfo
     * @param paramDto
      */
    void stepRewards(FinanceUserInfo userInfo, LoginParamDto paramDto);

    /**
      *功能描述:用户进入固定金额活动入口判断
     * @author: moruihai
     * @date: 2018/9/10 15:29
     * @param: []
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> getFixedAmountEntry();

    /**
      *功能描述:首次参加页面数据
      * @author: moruihai
      * @date: 2018/9/10 16:34
      * @param: []
      * @return: java.util.Map<java.lang.String,finance.model.vo.FixedAmountPageVO>
      */
    FixedAmountPageVO queryFixedAmountPageInfo(FixedAmountPageDto fixedAmountPageDto);

    /**
      *功能描述:用户开启一轮活动
      * @author: moruihai
      * @date: 2018/9/12 9:30
      * @param: []
      * @return: finance.model.finance.ResponseResult<finance.model.vo.FixedAmountPageVO>
      */

    ResponseResult<FixedAmountPageVO> joinFixedAmountActivity();

    /**
     *功能描述:被邀请人注册逻辑
     * @author: moruihai
     * @date: 2018/9/12 16:49
     * @param: [userInfo, paramDto]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     */
    Map<String, Object> handleFixedAmountActivity(FinanceUserInfo userInfo, LoginParamDto paramDto);

    /**
      *功能描述:固定活动注册页面展示
      * @author: moruihai
      * @date: 2018/9/13 15:47
      * @param: [fixedAmountPageDto]
      * @return: finance.model.vo.FixedAmountPageVO
      */
    FixedAmountPageVO queryFixedAmountLoginPageInfo(FixedAmountPageDto fixedAmountPageDto);

}
