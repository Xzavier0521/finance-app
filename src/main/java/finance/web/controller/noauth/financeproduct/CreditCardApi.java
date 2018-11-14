package finance.web.controller.noauth.financeproduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.vo.financeproduct.CreditCardProductDetailVO;
import finance.api.model.vo.financeproduct.CreditCardProductListVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.financeproduct.CreditCardProductBiz;
import finance.model.po.FinanceProductMain;

/**
 * @program: finance-app
 * @description: 我要办卡操作API
 * @author: MORUIHAI
 * @create: 2018-07-06 13:03
 **/
@RestController
@RequestMapping("CreditCard")
public class CreditCardApi {
    @Resource
    private CreditCardProductBiz creditCardProductImpl;

    /**
     * <pre>
     * @api {GET} /CreditCard/creditCardList 我要办卡主页面
     * @apiName creditCardList
     * @apiGroup FinanceProduct
     * @apiVersion 0.1.0
     * @apiDescription 我要办卡主页面分页展示所有产品
     * @apiParam {Long} pageNum 页数
     * @apiParam {Long} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object[]} data.productList 理财产品数组
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "productList": [
     *              {
     *                  "id": 101,
     *                  "productName": "民生银行",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "totalBonus": "50",
     *                  "passRate": "80%",
     *                  "rebackCashDesc": "刷卡返现1",
     *                  "directBonus":"65",
     *                  "indirectBonus":"10"
     *              },
     *              {
     *                  "id": 102,
     *                  "productName": "浦发银行",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "totalBonus": "50",
     *                  "passRate": "80%",
     *                  "rebackCashDesc": "刷卡返现1",
     *                  "directBonus":"60",
     *                  "indirectBonus":"5"
     *              },
     *              {
     *                  "id": 103,
     *                  "productName": "上海银行",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "totalBonus": "50",
     *                  "passRate": "80%",
     *                  "rebackCashDesc": "刷卡返现1",
     *                  "directBonus":"65",
     *                  "indirectBonus":"5"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     *
     * @author
     */
    @GetMapping("creditCardList")
    public ResponseResult<Map<String, List<CreditCardProductListVO>>> getCreditCardList(@RequestParam("pageNum") Long pageNum,
                                                                                        @RequestParam("pageSize") Long pageSize) {

        if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
            return ResponseResult.error(CodeEnum.creditCardParamNUll);
        }

        Page<FinanceProductMain> financeProductPage = new Page<>(pageSize.intValue(), pageNum);
        List<CreditCardProductListVO> ccProductList = creditCardProductImpl
            .findProductList(financeProductPage);
        Map<String, List<CreditCardProductListVO>> returnMap = new HashMap();
        returnMap.put("productList", ccProductList);

        return ResponseResult.success(returnMap);
    }

    /**
     * <pre>
     * @api {GET} /CreditCard/creditCardDetail 我要办卡详情页
     * @apiName creditCardDetail
     * @apiGroup FinanceProduct
     * @apiVersion 0.1.0
     * @apiDescription 我要办卡主页产品详情页
     * @apiParam {Long} productId 产品ID
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object} data 理财产品
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "id": 101,
     *          "productName": "民生银行",
     *          "detailPageUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *          "terminalBonus": "50",
     *          "directBonus": "20",
     *          "indirectBonus": "10",
     *          "cashbackDate": "每月的15号",
     *          "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *          "productDesc": "信用卡申卡快，返现多",
     *          "promotionUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *          "passRate": "80%",
     *          "maxAmount": "10000元",
     *          "auditLength": "最快3秒"
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     *
     * @author
     */
    @GetMapping(value = "creditCardDetail")
    public ResponseResult<CreditCardProductDetailVO> creditCardDetail(@RequestParam("productId") Long productId) {
        if (StringUtils.isEmpty(productId)) {
            return ResponseResult.error(CodeEnum.creditCardParamNUll);
        }

        CreditCardProductDetailVO ccd = creditCardProductImpl
            .findProductDetailByProductId(productId);
        return ResponseResult.success(ccd);
    }
}
