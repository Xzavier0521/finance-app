package finance.web.controller.oauth.coin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.ExchangeGoodsVO;
import finance.domainservice.service.gift.GiftBiz;
import finance.model.po.FinanceGiftInfo;

/**
 * @program: finance-server
 *
 * @description: 金币兑换API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 15:22
 **/
@RestController
@RequestMapping("coin/exchange")
public class CoinExchangeApi {
    @Autowired
    private GiftBiz giftBizImpl;

    /**
     * <pre>
     * @api {GET} /coin/exchange/goodsList 所有可兑换商品查询
     * @apiName coinGoodsList
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 可兑换商品查询
     * @apiParam {Long} pageNum 页数
     * @apiParam {Long} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object} data 商品数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "goodsList": [
     *              {
     *                  "goodsName": "爱奇艺VIP月卡",
     *                  "goodsId": 1,
     *                  "bannerUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "needCoinCount": 100,
     *                  "thumbnailUrl": "http://jingrongjia-oss.oss-cn-hangzhou.aliyuncs.com/C%3A%5CUsers%5CAdministrator%5CDesktop%5Cavatar2.jpg?Expires=5136032989&OSSAccessKeyId=LTAIEEZtE9nJG6UW&Signature=DoR1eYqcvz%2F9sTHyj8cFjg%2BE93s%3D"
     *              },
     *              {
     *                  "goodsName": "优酷VIP月卡",
     *                  "goodsId": 2,
     *                  "bannerUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "needCoinCount": 100,
     *                  "thumbnailUrl": "http://jingrongjia-oss.oss-cn-hangzhou.aliyuncs.com/C%3A%5CUsers%5CAdministrator%5CDesktop%5Cavatar2.jpg?Expires=5136032989&OSSAccessKeyId=LTAIEEZtE9nJG6UW&Signature=DoR1eYqcvz%2F9sTHyj8cFjg%2BE93s%3D"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "goodsList")
    public ResponseResult<Map<String, List<ExchangeGoodsVO>>> coinGoodsList(@RequestParam("pageNum") Long pageNum,
                                                                            @RequestParam("pageSize") Long pageSize) {
        Page<FinanceGiftInfo> financeGiftInfoPage = new Page<>(pageSize.intValue(), pageNum);
        Map<String, List<ExchangeGoodsVO>> returnMap = new HashMap();
        List<ExchangeGoodsVO> exchangeGoodsVOList = giftBizImpl
            .queryCoinGoodsList(financeGiftInfoPage);
        returnMap.put("goodsList", exchangeGoodsVOList);
        return ResponseResult.success(returnMap);
    }

    /**
     * <pre>
     * @api {POST} /coin/exchange/exchangeGoods 兑换商品
     * @apiName exchangeGoods
     * @apiGroup COIN
     * @apiVersion 0.1.0
     * @apiDescription 兑换商品
     * @apiParam {Long} goodsId 商品id
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object} data 商品数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data":true,
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     * @author
     */
    @PostMapping(value = "exchangeGoods")
    public ResponseResult<Boolean> exchangeGoods(@RequestBody XMap paramMap) {
        return giftBizImpl.exchangeGoods(paramMap);
    }
}
