package cn.zhishush.finance.web.controller.noauth.financeproduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domainservice.service.financeproduct.LoanProductBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductDetailVO;
import cn.zhishush.finance.api.model.vo.financeproduct.LoanProductListVO;
import cn.zhishush.finance.core.dal.dataobject.product.ProductMain;

/**
 * @program: finance-app
 * @description: 贷款产品操作API
 * @author: MORUIHAI
 * @create: 2018-07-06 14:18
 **/
@RestController
@RequestMapping("loan")
public class LoanApi {
	@Autowired
	private LoanProductBiz loanProductBizImpl;

	/**
	 * <pre>
	 * &#64;api {GET} /loan/loanList 我要贷款主页面
	 * &#64;apiName loanList
	 * &#64;apiGroup FinanceProduct
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 我要贷款主页面分页展示所有产品
	 * &#64;apiParam {Long} pageNum 页数
	 * &#64;apiParam {Long} pageSize 每页条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object[]} data.list 理财产品数组
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "productList": [
	 *              {
	 *                  "id": 104,
	 *                  "productName": "民生银行",
	 *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
	 *                  "totalBonus": "2.5%"
	 *              },
	 *              {
	 *                  "id": 105,
	 *                  "productName": "平安银行",
	 *                  "logoUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
	 *                  "totalBonus": "2.5%"
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0303001 参数不合法
	 * </pre>
	 *
	 * @author
	 */
	@GetMapping(value = "loanList")
	public ResponseResult<Map<String, List<LoanProductListVO>>> loanList(@RequestParam("pageNum") Long pageNum,
			@RequestParam("pageSize") Long pageSize) {
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
			return ResponseResult.error(CodeEnum.loanParamNUll);
		}

		Page<ProductMain> financeProductPage = new Page<>(pageSize.intValue(), pageNum);
		List<LoanProductListVO> lProductList = loanProductBizImpl.findProductList(financeProductPage);
		Map<String, List<LoanProductListVO>> returnMap = new HashMap();
		returnMap.put("productList", lProductList);

		return ResponseResult.success(returnMap);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /loan/loanDetail 我要贷款详情页
	 * &#64;apiName loanDetail
	 * &#64;apiGroup FinanceProduct
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 我要贷款主页产品详情页
	 * &#64;apiParam {Long} productId 产品ID
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object} data 贷款产品
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "id": 101,
	 *          "productName": "民生银行",
	 *          "mark1": "最快1小时放款",
	 *          "mark2": "月利率0.78%",
	 *          "amountScope": "2000-15000元",
	 *          "dateScope": "12-36月",
	 *          "logoUrl": null,
	 *          "terminalBonus": "2%",
	 *          "directBonus": "0.5%",
	 *          "indirectBonus": "0.5%",
	 *          "cashbackDate": "每月的15号",
	 *          "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
	 *          "productDesc": "贷款放款快，返现多",
	 *          "promotionUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
	 *          "amountType":"2(金额类型，1-金额值，2-百分比)"
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0303001 参数不合法
	 * </pre>
	 *
	 * @author
	 */
	@GetMapping(value = "loanDetail")
	public ResponseResult<LoanProductDetailVO> loanDetail(@RequestParam("productId") Long productId) {
		if (StringUtils.isEmpty(productId)) {
			return ResponseResult.error(CodeEnum.loanParamNUll);
		}
		LoanProductDetailVO lpd = loanProductBizImpl.findProductDetailByProductId(productId);
		return ResponseResult.success(lpd);
	}
}
