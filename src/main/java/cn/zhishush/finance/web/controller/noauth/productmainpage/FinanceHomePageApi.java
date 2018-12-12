package cn.zhishush.finance.web.controller.noauth.productmainpage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domainservice.service.financeproduct.HomePageBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.productmainpage.ProductMainpageVO;

/**
 * @program: finance-app
 *
 * @description: 金榕家集合页操作API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-07-06 15:38
 **/
@RestController
@RequestMapping("FinanceHomePage")
public class FinanceHomePageApi {
	@Autowired
	private HomePageBiz homePageBizImpl;

	/**
	 * <pre>
	 * &#64;api {GET} /FinanceHomePage/financeHomeProductList 金榕家集合主页面
	 * &#64;apiName financeHomeProductList
	 * &#64;apiGroup FinanceHomePage
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 金榕家集合主页面分页展示所有产品
	 * &#64;apiParam {Long=1,2} productType 产品分类
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
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0305001 参数不合法
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping(value = "financeHomeProductList")
	public ResponseResult<Map<String, List<ProductMainpageVO>>> financeHomeProductList(
			@RequestParam("productType") Long productType, @RequestParam("pageNum") Long pageNum,
			@RequestParam("pageSize") Long pageSize) {
		if (StringUtils.isEmpty(productType) || StringUtils.isEmpty(pageNum) || StringUtils.isEmpty(pageSize)
				|| (productType != 1 && productType != 2)) {
			return ResponseResult.error(CodeEnum.financeHomeParamInvalid);
		}

		Page<ProductMainpageVO> page = new Page<>(pageSize.intValue(), pageNum);
		List<ProductMainpageVO> lProductList = homePageBizImpl.findHomePageList(productType, page);

		Map<String, List<ProductMainpageVO>> returnMap = new HashMap();
		returnMap.put("productList", lProductList);

		return ResponseResult.success(returnMap);
	}

}
