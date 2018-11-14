package finance.web.controller.oauth.coin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.CoinRecordVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.game.CoinBiz;
import finance.domainservice.service.jwt.JwtService;

/**
 * 一般金币服务.
 * @author hewenbin
 * @version v1.0 2018年8月15日 下午4:37:12 hewenbin
 */
@RequestMapping("coin")
@RestController
public class CoinApi {
    @Autowired
    private CoinBiz    coinBizImpl;
    @Autowired
    private JwtService jwtService;

    /**
     * <pre>
     * @api {GET} coin 查询个人金币详情
     * @apiName queryUserCoinInfo 
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 人金币详情
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Int} data.currentCoin 当前可用金币数量
     * @apiSuccess {Int} data.totalCoin 累计金币数量（所有获取的金币数）
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"currentCoin":100,
     *   	"totalCoin":1000
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version CoinApi.java, v1.0 2018年8月16日 上午11:30:03 hewenbin
     */
    @GetMapping
    public ResponseResult<Map<String, Object>> queryUserCoinInfo() {
        Long userId = jwtService.getUserInfo().getId();
        Map<String, Object> responseMap = coinBizImpl.queryUserCoinInfo(userId);
        return ResponseResult.success(responseMap);
    }

    /**
     * <pre>
     * @api {GET} coin/records 查询个人金币记录
     * @apiName queryUserCoinRecords 
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 增加、减少，只返回最近30条
     * @apiParam {String="in","out"} type 类型，增加：in，减少：out
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *   {
     *       "errorCode": "0000000",
     *       "errorMessage": "success",
     *       "succeed": true,
     *       "data": {
     *           "records": [
     *               {
     *                   "datetime": "2017-09-09 07:00:00",
     *                   "coinNum": 100,
     *                   "desc": "打卡获取金币"
     *               },
     *               {
     *                   "datetime": "2017-09-09 07:00:00",
     *                   "coinNum": 100,
     *                   "desc": "打卡获取金币"
     *               }
     *           ]
     *       }
     *   }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     * @author hewenbin
     * @version CoinApi.java, v1.0 2018年8月16日 上午10:12:46 hewenbin
     */
    @GetMapping("records")
    public ResponseResult<Map<String, List<CoinRecordVO>>> queryUserCoinRecords(@RequestParam("type") String type) {
        if (!("in".equals(type) || "out".equals(type))) {
            return ResponseResult.error(CodeEnum.gameParamInvalid);
        }

        Map<String, List<CoinRecordVO>> returnMap = new HashMap<>();
        List<CoinRecordVO> coinRecordVOList = coinBizImpl.queryUserCoinRecords(type);
        returnMap.put("records", coinRecordVOList);

        return ResponseResult.success(returnMap);
    }

}
