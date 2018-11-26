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
import finance.api.model.vo.financeproduct.RebackCashRuleVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.financeproduct.InsuranceProductBiz;
import finance.core.dal.dataobject.ProductMain;

/**
 * @program: finance-app
 *
 * @description: 保险产品操作API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 14:57
 **/
@RestController
@RequestMapping("Insurance")
public class InsuranceApi {
	@Autowired
	private InsuranceProductBiz insuranceProductBizImpl;

	/**
	 * <pre>
	 * &#64;api {GET} /Insurance/rebackCashRuleList 我要保险返现规则
	 * &#64;apiName rebackCashRuleList
	 * &#64;apiGroup FinanceProduct
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 我要保险返现规则分页展示所有规则
	 * &#64;apiParam {Long} pageNum 页数
	 * &#64;apiParam {Long} pageSize 每页条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object[]} data.list 保险产品数组
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "productList": [
	 *              {
	 *                  "id": 107,
	 *                  "productName": "太平保险",
	 *                  "terminalBonus": "5%",
	 *                  "directBonus": "2.5%",
	 *                  "indirectBonus": "2.5%",
	 *                  "cashbackDate": "每月15号"
	 *              },
	 *              {
	 *                  "id": 107,
	 *                  "productName": "平安保险",
	 *                  "terminalBonus": "5%",
	 *                  "directBonus": "2.5%",
	 *                  "indirectBonus": "2.5%",
	 *                  "cashbackDate": "每月15号"
	 *              },
	 *              {
	 *                  "id": 107,
	 *                  "productName": "人寿保险",
	 *                  "terminalBonus": "5%",
	 *                  "directBonus": "2.5%",
	 *                  "indirectBonus": "2.5%",
	 *                  "cashbackDate": "每月15号"
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0304001 参数不合法
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping(value = "rebackCashRuleList")
	public ResponseResult<Map<String, List<RebackCashRuleVO>>> rebackCashRuleList(@RequestParam("pageNum") Long pageNum,
			@RequestParam("pageSize") Long pageSize) {
		if (StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)) {
			return ResponseResult.error(CodeEnum.rebackRuleParamNUll);
		}

		Page<ProductMain> financeProductPage = new Page<>(pageSize.intValue(), pageNum);
		List<RebackCashRuleVO> reRulelist = insuranceProductBizImpl.findProductList(financeProductPage);
		Map<String, List<RebackCashRuleVO>> returnMap = new HashMap();
		returnMap.put("productList", reRulelist);
		return ResponseResult.success(returnMap);
	}

}
