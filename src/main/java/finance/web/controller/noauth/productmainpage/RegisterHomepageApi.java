package finance.web.controller.noauth.productmainpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.productmainpage.ProductMainpageVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.financeproduct.HomePageBiz;

/**
 * @program: finance-app
 *
 * @description: 注册宝主页操作API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 15:46
 **/
@RestController
@RequestMapping("RegisterHomePage")
public class RegisterHomepageApi {
    @Resource
    private HomePageBiz homePageBizImpl;

    /**
     * <pre>
     * @api {GET} /RegisterHomePage/registerHomeProductList 注册宝主页面
     * @apiName registerHomeProductList
     * @apiGroup RegisterHomePage
     * @apiVersion 0.1.0
     * @apiDescription 注册宝主页面分页展示所有产品
     * @apiParam {Long=3,4,5,6} productType 产品分类
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
     *                  "id": 109,
     *                  "productName": "360贷款",
     *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *                  "productDes": "1小时放款到账",
     *                  "maxAmount": "20000",
     *                  "feeRate": "5%",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg"
     *              },
     *              {
     *                  "id": 110,
     *                  "productName": "麦子贷款",
     *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *                  "productDes": "1小时放款到账",
     *                  "maxAmount": "30000",
     *                  "feeRate": "6%",
     *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0306001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "registerHomeProductList")
    public ResponseResult<Map<String, List<ProductMainpageVO>>> registerHomeProductList(@RequestParam("productType") Long productType,
                                                                                        @RequestParam("pageNum") Long pageNum,
                                                                                        @RequestParam("pageSize") Long pageSize) {
        if (StringUtils.isEmpty(productType) || StringUtils.isEmpty(pageNum)
            || StringUtils.isEmpty(pageSize)
            || (productType != 3 && productType != 4 && productType != 5 && productType != 6)) {

            return ResponseResult.error(CodeEnum.registerHomeParamInvalid);
        }

        Page<ProductMainpageVO> page = new Page<>(pageSize.intValue(), pageNum);
        List<ProductMainpageVO> lProductList = homePageBizImpl.findHomePageList(productType, page);

        Map<String, List<ProductMainpageVO>> returnMap = new HashMap();
        returnMap.put("productList", lProductList);

        return ResponseResult.success(returnMap);
    }
}
