package finance.web.controller.oauth.coin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.EarlyClockPageVO;
import finance.api.model.vo.MyRecordVO;
import finance.api.model.vo.PushRewardVO;
import finance.core.common.constant.Constant;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.game.CoinBiz;
import finance.model.po.FinanceCoinLog;

/**
 * 金币游戏服务.
 * @author hewenbin
 * @version v1.0 2018年8月15日 下午4:37:12 hewenbin
 */
@RequestMapping("coin/game")
@RestController
public class CoinGameApi {
    @Autowired
    private CoinBiz coinBizImpl;

    /**
     * <pre>
     * @api {GET} coin/game 查询金币游戏详情
     * @apiName queryCoinGameInfo 
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 查询金币游戏详情
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Int} totalJoinNum 总参加打卡人数（报名参加明天的打卡）
     * @apiSuccess {Int} totalCoinNum 总金币数（报名参加明天打卡的金币）
     * @apiSuccess {Int} signNum 今日打卡人数
     * @apiSuccess {Int} noSignNum 昨天报名，但是今日未打卡人数
     * @apiSuccess {String} earliestMobile 最早打卡人手机号
     * @apiSuccess {String} earliestTime 最早打卡时间
     * @apiSuccess {String} maxCoinMobile 手气最好的手机号
     * @apiSuccess {Int} maxCoinNum 手气最好的金币数量
     * @apiSuccess {String} longestMobile 连续打卡天数最长的手机号
     * @apiSuccess {Int} longestNum 连续打卡最长天数
     * @apiSuccess {Boolean} yesterdayJoinTask 是否昨日参加活动
     * @apiSuccess {Boolean} todayJoinTask 是否今日参加活动
     * @apiSuccess {Boolean} clockTask 是否打卡
     * @apiSuccess {Int} yesterdayTotalJoinCoinNum 昨天报名总金币数
     * @apiSuccess {Int} yesterdayTotalJoinPersonNum 昨天报名总人数
     * @apiSuccess {Int} totalCanUserCoin 当前可用金额
     * @apiSuccess {Int} earlyCardUseCoinNum 报名需要金币数
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"totalJoinNum":100,
     *   	"totalCoinNum":10000,
     *   	"signNum":30,
     *   	"noSignNum":20,
     *   	"earliestMobile":"131****1111",
     *   	"earliestTime":"05:00:01",
     *   	"maxCoinMobile":"132****1111",
     *   	"maxCoinNum":30,
     *   	"longestMobile":"135****1111",
     *   	"longestNum":10,
     *   	"yesterdayJoinTask":true,
     *   	"todayJoinTask":true,
      *   	"clockTask":false,
     *      "yesterdayTotalJoinCoinNum": 100000000,
     *      "totalCanUserCoin": 24453,
     *      "earlyCardUseCoinNum": 100,
     *      "yesterdayTotalJoinPersonNum": 100
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version CoinApi.java, v1.0 2018年8月15日 下午4:46:23 hewenbin
     */
    @GetMapping
    public ResponseResult<EarlyClockPageVO> queryCoinGameInfo() {
        ResponseResult<EarlyClockPageVO> res = coinBizImpl.getClockPageData();
        return res;
    }

    /**
     * <pre>
     * @api {GET} coin/game/mine 查询我的金币游戏详细信息
     * @apiName queryUserCoinGameInfo 
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 我的战绩
     * @apiParam {Long} pageNum 页数
     * @apiParam {Long} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Int} data.totalOutCoin 累计投入金币总数
     * @apiSuccess {Int} data.totalInCoin 累计赚取金币总数
     * @apiSuccess {Int} data.totalSign 累计打卡天数
     * @apiSuccess {Array} data.records 投入、赚取记录
     * @apiSuccess {String="参加打卡活动","打卡获取金币"} records.desc 金币说明
     * @apiSuccess {String} records.datetime 金币记录时间
     * @apiSuccess {Int} records.coinNum 金币数量
     * @apiSuccess {String="in","out"} records.type 金币类型（投入：out、赚取：in）
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *   {
     *       "errorCode": "0000000",
     *       "errorMessage": "success",
     *       "succeed": true,
     *       "data": {
     *           "totalInCoin": 10000,
     *           "totalSign": 100,
     *           "totalOutCoin": 100,
     *           "records": [
     *               {
     *                   "datetime": "2017-09-09 07:00:00",
     *                   "coinNum": 100,
     *                   "type": "in",
     *                   "desc": "参加打卡活动"
     *               },
     *               {
     *                   "datetime": "2017-09-09 07:00:00",
     *                   "coinNum": 100,
     *                   "type": "out",
     *                   "desc": "打卡获取金币"
     *               }
     *           ]
     *       }
     *   }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version CoinApi.java, v1.0 2018年8月16日 上午9:02:21 hewenbin
     */
    @GetMapping("mine")
    public ResponseResult<MyRecordVO> queryUserCoinGameInfo(@RequestParam("pageNum") Long pageNum,
                                                            @RequestParam("pageSize") Long pageSize) {
        if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
            return ResponseResult.error(CodeEnum.gameParamInvalid);
        }
        Page<FinanceCoinLog> financeCoinLogPage = new Page<>(pageSize.intValue(), pageNum);
        return coinBizImpl.findMyRecordList(financeCoinLogPage);

    }

    /**
     * <pre>
     * @api {POST} coin/game 点击参加早起打卡活动和打卡
     * @apiName earlyCoinGame
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 参加早起打卡活动
     * @apiParam {String='join','sign'} method 参加活动,join:参加活动,sign:打卡
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": true,
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0401002 金币数不足
     * @apiError 0401003 非参加活动时间
     * @apiError 0401005 参加失败，请稍后重试
     * </pre>
     * @author moruihai
     * @version CoinApi.java, v1.0 2018年8月22日
     */
    @PostMapping
    public ResponseResult<Boolean> earlyCoinGame(@RequestBody XMap paramMap) {
        String method = (String) paramMap.get("method");
        if (Constant.JOIN.equals(method)) {
            return coinBizImpl.joinEarlyCoinGame();
        } else {
            return coinBizImpl.signEarlyCoinGame();
        }

    }

    @GetMapping("pushRewardMsg")
    public ResponseResult<PushRewardVO> pushRewardMsg() {
        return coinBizImpl.pushRewardMsg();

    }
}
