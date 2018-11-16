package finance.domainservice.service.game;

import java.util.List;
import java.util.Map;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.CoinRecordVO;
import finance.api.model.vo.EarlyClockPageVO;
import finance.api.model.vo.MyRecordVO;
import finance.api.model.vo.PushRewardVO;
import finance.core.dal.dataobject.FinanceCoinLog;

/**
 * @program: finance-server
 * @description: ${description}
 * @author: MORUIHAI
 * @create: 2018-08-21 16:31
 **/
public interface CoinBiz {

    /**
      *功能描述:早起打卡页面数据显示
      * @author: moruihai
      * @date: 2018/8/21 17:14
      * @param: []
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    ResponseResult<EarlyClockPageVO> getClockPageData();

    /**
      *功能描述:我的战绩
      * @author: moruihai
      * @date: 2018/8/22 14:02
      * @param: [financeCoinLogPage]
      * @return: finance.model.vo.MyRecordVO
      */
    ResponseResult<MyRecordVO> findMyRecordList(Page<FinanceCoinLog> financeCoinLogPage);

    /**
      *功能描述:参加早起打卡
      * @author: moruihai
      * @date: 2018/8/22 17:40
      * @param: []
      * @return: finance.model.finance.ResponseResult<java.lang.Boolean>
      */
    ResponseResult<Boolean> joinEarlyCoinGame();

    /**
      *功能描述:点击打卡
      * @author: moruihai
      * @date: 2018/8/22 19:35
      * @param: []
      * @return: finance.model.finance.ResponseResult<java.util.Map<java.lang.String,java.lang.Object>>
      */
    ResponseResult<Boolean> signEarlyCoinGame();

    /**
      *功能描述:查询个人金币详情
      * @author: moruihai
      * @date: 2018/8/30 14:27
      * @param: []
      * @return: java.util.Map<java.lang.String,java.lang.Object>
      */
    Map<String, Object> queryUserCoinInfo(Long userId);

    List<CoinRecordVO> queryUserCoinRecords(String type);

    void InviteUserCoinReward(Long userId);

    /**
     *@author ：tangwei
     * @Title: pushRewardMsg
     * @Description: TODO
     * @return
     * @return: ResponseResult<PushRewardVO>  
     */
    ResponseResult<PushRewardVO> pushRewardMsg();
}
