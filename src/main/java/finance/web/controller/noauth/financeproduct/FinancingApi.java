package finance.web.controller.noauth.financeproduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.financeproduct.FinancingProductDetailVO;
import finance.api.model.vo.financeproduct.FinancingProductListVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.financeproduct.FinanceProductBiz;
import finance.model.po.FinanceProductMain;

/**
 * @program: finance-app
 * @description: 理财产品API接口
 * @author: MORUIHAI
 * @create: 2018-07-05 15:24
 **/

@RestController
@RequestMapping("financing")
public class FinancingApi {
    @Autowired
    private FinanceProductBiz financialProductBizImpl;

    /**
     * <pre>
     * @api {GET} /financing/financingList 我要理财主页面
     * @apiName financingList
     * @apiGroup FinanceProduct
     * @apiVersion 0.1.0
     * @apiDescription 我要理财主页面分页展示所有产品
     * @apiParam {Long} pageNum 页数
     * @apiParam {Long} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object[]} data.list 理财产品数组
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "productList": [
     *              {
     *                  "id": 100,
     *                  "productName": "致青春理财",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "mark": [
     *                      "新手",
     *                      "首投"
     *                  ],
     *                  "aveRevenue": "12%",
     *                  "productBackground": "银行存管",
     *                  "totalBonus": "5%"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0301001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "financingList")
    public ResponseResult<Map<String, List<FinancingProductListVO>>> financingList(@RequestParam("pageNum") Long pageNum,
                                                                                   @RequestParam("pageSize") Long pageSize) {
        if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
            return ResponseResult.error(CodeEnum.financialParamNUll);
        }

        Map<String, List<FinancingProductListVO>> returnMap = new HashMap();
        Page<FinanceProductMain> financeProductPage = new Page<>(pageSize.intValue(), pageNum);
        List<FinancingProductListVO> financingProductList = financialProductBizImpl
            .findProductList(financeProductPage);
        returnMap.put("productList", financingProductList);
        return ResponseResult.success(returnMap);
    }

    /**
     * <pre>
     * @api {GET} /financing/financingProductDetail 我要理财详情页
     * @apiName financingProductDetail
     * @apiGroup FinanceProduct
     * @apiVersion 0.1.0
     * @apiDescription 我要理财主页产品详情页
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
     *          "id": 100,
     *          "productName": "致青春理财",
     *          "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *          "terminalBonus": "2%",
     *          "directBonus": "0.5%",
     *          "indirectBonus": "0.5%",
     *          "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *          "cashbackDate": "每月的15号",
     *          "level": "A",
     *          "backgroundStrength": "9.0",
     *          "riskControl": "9.0",
     *          "operationCapability": "9.0",
     *          "startAmount": ">=1000元",
     *          "startPeriod": ">=一个月",
     *          "rebackName": "额外返利",
     *          "rebackValue": "4%",
     *          "totalReturn": ">1040元",
     *          "aveRevenue": "11%",
     *          "cashbackRule": "年化利率",
     *          "productDesc": "理财申请快，返现多",
     *          "promotionUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg"
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0301001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "financingProductDetail")
    public ResponseResult<FinancingProductDetailVO> financingProductDetail(@RequestParam("productId") Long productId) {
        if (StringUtils.isEmpty(productId)) {
            return ResponseResult.error(CodeEnum.financialParamNUll);
        }

        FinancingProductDetailVO fProductDetail = financialProductBizImpl
            .findProductDetailByProductId(productId);
        return ResponseResult.success(fProductDetail);
    }
}
