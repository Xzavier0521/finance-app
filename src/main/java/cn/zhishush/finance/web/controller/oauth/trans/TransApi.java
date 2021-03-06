package cn.zhishush.finance.web.controller.oauth.trans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domain.dto.UserWithdrawDto;
import cn.zhishush.finance.domainservice.service.finance.order.OrderBiz;
import cn.zhishush.finance.domainservice.service.finance.trans.TransBiz;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.domainservice.service.sms.SmsBiz;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.base.XMap;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.financeProfit.FinanceProfitVO;
import cn.zhishush.finance.api.model.vo.transRecord.FinanceOrderVo;

@RestController
@RequestMapping("/finance")
public class TransApi {

	@Autowired
	public OrderBiz order;
	@Autowired
	public TransBiz trans;
	@Autowired
	public SmsBiz sms;
	@Autowired
	private JwtService jwtService;

	/**
	 * <pre>
	 * &#64;api {POST} /finance/withdraw 提现
	 * &#64;apiName withdraw
	 * &#64;apiGroup Trans
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 交易
	 * &#64;apiParam {String} money 提现金额
	 * &#64;apiParam {String} pwd 提现密码
	 * &#64;apiParam {Int=1,0} byCoin=0 是否使用金币抵扣 1：是，0：否
	 * &#64;apiParamExample {JSON} Request-example
	 *   {
	 *   	"money": "129.99",
	 *   	"pwd": "11111",
	 *   	"byCoin": 1
	 *   }
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {NULL} data 空，忽略
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"success",
	 *   "succeed",true,
	 *   "data":"提交成功"
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0000011 参数不合法
	 * &#64;apiError 0201000 提现参数不合法
	 * &#64;apiError 0401002 金币数不足
	 * &#64;apiError 0000010 失败
	 * </pre>
	 * 
	 * @author yaolei
	 */
	@PostMapping("withdraw")
	public ResponseResult<String> withdraw(@RequestBody XMap param) {
		UserWithdrawDto paramDto = param.toBean(UserWithdrawDto.class);
		paramDto.setByCoin(Integer.valueOf("0")); // XXX 金币抵扣手续费暂时不上
		if (!paramDto.validateParam()) {
			return ResponseResult.error(CodeEnum.withdrawParamInvalid);
		}
		UserInfoDO userInfo = jwtService.getUserInfo();

		ResponseResult<String> res = trans.withdraw(userInfo, paramDto);
		return res;
	}

	/**
	 * <pre>
	 * &#64;api {GET} /finance/myProfit 我的返现记录
	 * &#64;apiName myProfit
	 * &#64;apiGroup Trans
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 交易
	 * &#64;apiParam {Integer} pageNum=1 页码
	 * &#64;apiParam {Integer} pageSize=300 每页显示条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {NULL} data 空，忽略
	 * &#64;apiSuccess {String} prodName  行为
	 * &#64;apiSuccess {String} terminalMoney 返现金额
	 * &#64;apiSuccess {String} createTime 时间
	 * &#64;apiSuccess {String} terminalName 用户
	 * &#64;apiSuccess {String} terminalPhone 手机号
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"success",
	 *   "succeed",true,
	 *   "data":{
	 *    "dataList": [
	 *				{
	 *				"prodName": "新概念保险",
	 *				"terminalName": "关*",
	 *				"terminalPhone": "138*****918",
	 *				"terminalMoney": 2,
	 *				"createTime": "2018/07/16"
	 *				},{
	 *				"prodName": "新概念保险",
	 *				"terminalName": "关*",
	 *				"terminalPhone": "138*****918",
	 *				"terminalMoney": 2,
	 *				"createTime": "2018/07/16"
	 *				}
	 *    ]}
	 *
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0000011 参数不合法
	 * &#64;apiError 0000010 失败
	 * </pre>
	 * 
	 * @author yaolei
	 */
	@GetMapping("myProfit")
	public ResponseResult<Map<String, List<FinanceProfitVO>>> myProfit(
			@RequestParam(required = false, name = "pageSize", defaultValue = "300") Integer pageSize,
			@RequestParam(required = false, name = "pageNum", defaultValue = "1") Long pageNum) {
		UserInfoDO user = jwtService.getUserInfo();
		Page<FinanceProfitVO> page = new Page<>(pageSize, pageNum);
		List<FinanceProfitVO> list = trans.myProfit(user.getId(), page);
		Map<String, List<FinanceProfitVO>> resMap = new HashMap<>();
		resMap.put("dataList", list); // 重构分页之后使用 dataList，同时添加page其他参数
		resMap.put("list", list);
		return ResponseResult.success(resMap);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /finance/transRecords 提现记录
	 * &#64;apiName transRecords
	 * &#64;apiGroup Trans
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 提现记录，默认查询前200条
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {String} status 提现状态 00：提现中，01：审核成功，02：审核失败，03：提现成功，04:提现失败
	 * &#64;apiSuccess {String} bankCode 银行卡号后四位
	 * &#64;apiSuccess {NULL} data 空，忽略
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *    "errorCode": "0000000",
	 *    "errorMessage": "success",
	 *    "data": [
	 *     	"dataList": [
	 *    		{
	 *    		"money": 1,
	 *    		"bankCode": "258",
	 *    		"status": "00",
	 *    		"createTime": "2018-07-17"
	 *    		}
	 *       ]],
	 *    "succeed": true
	 *    }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0000011 参数不合法
	 * &#64;apiError 0000010 失败
	 * </pre>
	 * 
	 * @author yaolei
	 */
	@GetMapping("transRecords")
	public ResponseResult<Page<FinanceOrderVo>> transRecords(
			@RequestParam(name = "pageSize", defaultValue = "200") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum) {
		Page<FinanceOrderVo> page = new Page<>(pageSize, pageNum);
		UserInfoDO user = jwtService.getUserInfo();
		trans.transRecords(user.getId(), page);
		return ResponseResult.success(page);
	}

}
