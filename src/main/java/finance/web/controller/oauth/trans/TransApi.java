package finance.web.controller.oauth.trans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.financeProfit.FinanceProfitVO;
import finance.api.model.vo.transRecord.FinanceOrderVo;
import finance.core.common.enums.CodeEnum;
import finance.domain.dto.UserWithdrawDto;
import finance.domainservice.service.finance.order.OrderBiz;
import finance.domainservice.service.finance.trans.TransBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.sms.SmsBiz;
import finance.model.po.FinanceUserInfo;

@RestController
@RequestMapping("/finance")
public class TransApi {

    @Autowired
    public OrderBiz    order;
    @Autowired
    public TransBiz    trans;
    @Autowired
    public SmsBiz      sms;
    @Autowired
    private JwtService jwtService;

    /**
     * <pre>
     * @api {POST} /finance/withdraw 提现
     * @apiName withdraw
     * @apiGroup Trans
     * @apiVersion 0.1.0
     * @apiDescription 交易
     * @apiParam {String} money 提现金额
     * @apiParam {String} pwd 提现密码
     * @apiParam {Int=1,0} byCoin=0 是否使用金币抵扣 1：是，0：否
     * @apiParamExample {JSON} Request-example
     *   {
     *   	"money": "129.99",
     *   	"pwd": "11111",
     *   	"byCoin": 1
     *   }
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {NULL} data 空，忽略
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"success",
     *   "succeed",true,
     *   "data":"提交成功"
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0201000 提现参数不合法
     * @apiError 0401002 金币数不足
     * @apiError 0000010 失败
     * </pre>
     * @author yaolei
     */
    @PostMapping("withdraw")
    public ResponseResult<String> withdraw(@RequestBody XMap param) {
        UserWithdrawDto paramDto = param.toBean(UserWithdrawDto.class);
        paramDto.setByCoin(Integer.valueOf("0")); // XXX 金币抵扣手续费暂时不上
        if (!paramDto.validateParam()) {
            return ResponseResult.error(CodeEnum.withdrawParamInvalid);
        }
        FinanceUserInfo userInfo = jwtService.getUserInfo();

        ResponseResult<String> res = trans.withdraw(userInfo, paramDto);
        return res;
    }

    /**
     * <pre>
     * @api {GET} /finance/myProfit 我的返现记录
     * @apiName myProfit
     * @apiGroup Trans
     * @apiVersion 0.1.0
     * @apiDescription 交易
     * @apiParam {Integer} pageNum=1 页码
     * @apiParam {Integer} pageSize=300 每页显示条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {NULL} data 空，忽略
     * @apiSuccess {String} prodName  行为
     * @apiSuccess {String} terminalMoney 返现金额
     * @apiSuccess {String} createTime 时间
     * @apiSuccess {String} terminalName 用户
     * @apiSuccess {String} terminalPhone 手机号
     * @apiSuccessExample {JSON} Success-Response
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0000010 失败
     * </pre>
     * @author yaolei
     */
    @GetMapping("myProfit")
    public ResponseResult<Map<String, List<FinanceProfitVO>>> myProfit(@RequestParam(required = false, name = "pageSize", defaultValue = "300") Integer pageSize,
                                                                       @RequestParam(required = false, name = "pageNum", defaultValue = "1") Long pageNum) {
        FinanceUserInfo user = jwtService.getUserInfo();
        Page<FinanceProfitVO> page = new Page<>(pageSize, pageNum);
        List<FinanceProfitVO> list = trans.myProfit(user.getId(), page);
        Map<String, List<FinanceProfitVO>> resMap = new HashMap<>();
        resMap.put("dataList", list); // 重构分页之后使用 dataList，同时添加page其他参数
        resMap.put("list", list);
        return ResponseResult.success(resMap);
    }

    /**
     * <pre>
     * @api {GET} /finance/transRecords 提现记录
     * @apiName transRecords
     * @apiGroup Trans
     * @apiVersion 0.1.0
     * @apiDescription 提现记录，默认查询前200条
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {String} status 提现状态 00：提现中，01：审核成功，02：审核失败，03：提现成功，04:提现失败
     * @apiSuccess {String} bankCode 银行卡号后四位
     * @apiSuccess {NULL} data 空，忽略
     * @apiSuccessExample {JSON} Success-Response
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0000010 失败
     * </pre>
     * @author yaolei
     */
    @GetMapping("transRecords")
    public ResponseResult<Page<FinanceOrderVo>> transRecords(@RequestParam(name = "pageSize", defaultValue = "200") Integer pageSize,
                                                             @RequestParam(name = "pageNum", defaultValue = "1") Long pageNum) {
        Page<FinanceOrderVo> page = new Page<>(pageSize, pageNum);
        FinanceUserInfo user = jwtService.getUserInfo();
        trans.transRecords(user.getId(), page);
        return ResponseResult.success(page);
    }

}
