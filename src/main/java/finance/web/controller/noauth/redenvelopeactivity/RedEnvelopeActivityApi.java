package finance.web.controller.noauth.redenvelopeactivity;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.FixedAmountPageVO;
import finance.api.model.vo.activity.StepRewardsDetailVo;
import finance.domain.dto.FixedAmountPageDto;
import finance.domainservice.service.businessinformation.ActivityBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.trans.InviteActivityService;

/**
 * 红包活动接口
 * 
 * @author panzhongkang
 * @date 2018/9/13 10:11
 */
@RestController
@RequestMapping("activity")
public class RedEnvelopeActivityApi {
	@Resource
	private JwtService jwtService;
	@Resource
	private ActivityBiz activityBizImpl;
	@Resource
	private InviteActivityService inviteActivityService;

	/**
	 * <pre>
	 * &#64;api {GET} /activity/openStepRewardsInfo 未登录展示阶梯红包信息
	 * &#64;apiName openStepRewardsInfo
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 未登录阶梯红包信息
	 * &#64;apiParam {Long} [pageNum=1] 页数
	 * &#64;apiParam {Long} [pageSize=5] 每页条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {String} isEnd 红包活动是否结束(0否，1是)
	 * &#64;apiSuccess {String} isNew = 1 是否新参加红包活动(0否，1是)
	 * &#64;apiSuccess {BigDecimal} poolAmount 奖池金额
	 * &#64;apiSuccess {Integer} inviteNum = null 已邀请人数
	 * &#64;apiSuccess {Integer} joinNum 已参加人数(isNew=1)
	 * &#64;apiSuccess {Integer} thisStepNum = null 当前阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} thisStepMoney = null 当前阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {Integer} nextStepNum = null 下一个阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} nextStepMoney = null 下一个阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {Integer} nextTwoStepNum = null 下两个阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} nextTwoStepMoney = null 下两个阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {BigDecimal} haveAmount = null 已获得金额(isNew=0)
	 * &#64;apiSuccess {Object[]} joinList 已参加用户列表(isNew=1)
	 * &#64;apiSuccess {String} joinList.mobileNum 已参加用户列表.手机号(isNew=1)
	 * &#64;apiSuccess {Integer} joinList.inviteNum 已参加用户列表.邀请人数(isNew=1)
	 * &#64;apiSuccess {BigDecimal} joinList.rewardAmount 已参加用户列表.奖励金额(isNew=1)
	 * &#64;apiSuccess {Object[]} inviteList = null 已邀请用户列表(isNew=0)
	 * &#64;apiSuccess {String} inviteList.mobileNum 已邀请用户列表手机号(isNew=0)
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *     "errorCode": "0000000",
	 *     "errorMessage": "success",
	 *     "data": {
	 *         "isEnd": "0",
	 *         "isNew": "1",
	 *         "poolAmount":200000,
	 *         "inviteNum": null,
	 *         "joinNum": 1,
	 *         "thisStepNum": null,
	 *         "thisStepMoney": null,
	 *         "nextStepNum": null,
	 *         "nextStepMoney": null,
	 *         "nextTwoStepNum": null,
	 *         "nextTwoStepMoney": null,
	 *         "haveAmount": null,
	 *         "joinList": [
	 *         {
	 *             "mobileNum":"158****3850",
	 *             "inviteNum":101,
	 *             "rewardAmount":300
	 *         }
	 *         ],
	 *         "inviteList": []
	 *     },
	 *     "succeed": true
	 * }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 */
	@GetMapping("openStepRewardsInfo")
	public ResponseResult<StepRewardsDetailVo> queryStepRewardsInfo(
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum) {
		Page<StepRewardsDetailVo> page = new Page<>(pageSize, pageNum);
		StepRewardsDetailVo stepRewardsDetailVo = activityBizImpl.queryStepRewardsInfo(null, page);
		return ResponseResult.success(stepRewardsDetailVo);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/stepRewardRefList 阶梯红包奖励规则列表
	 * &#64;apiName stepRewardRefList
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 阶梯红包奖励规则列表
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Boolean} data 返回数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 * {
	 *     "errorCode": "0000000",
	 *     "errorMessage": "success",
	 *     "data": [
	 *         {
	 *             "totalAmount": "8",
	 *             "stepNum": "第1等级",
	 *             "inviteNum": "5"
	 *         },
	 *         {
	 *             "totalAmount": "20",
	 *             "stepNum": "第2等级",
	 *             "inviteNum": "10"
	 *         },
	 *         ...
	 *         {
	 *             "totalAmount": "3000",
	 *             "stepNum": "第16等级",
	 *             "inviteNum": "1000"
	 *         }
	 *     ],
	 *     "succeed": true
	 * }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 */
	@GetMapping("stepRewardRefList")
	public ResponseResult<List<Map<String, String>>> stepRewardRefList() {
		List<Map<String, String>> resMapList = activityBizImpl.queryStepRewardRefList();
		return ResponseResult.success(resMapList);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/fixedAmount/loginPage 固定金额活动注册页面展示
	 * &#64;apiName fixedAmountLoginPageInfo
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 固定金额活动注册页面展示
	 * &#64;apiParam {Long} pageNum 页数
	 * &#64;apiParam {Long} pageSize 每页条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccess {Long} totalJoinNum 所有完成参与活动人数
	 * &#64;apiSuccess {Long} redPacketTotalAmount 固定金额活动金额
	 * &#64;apiSuccess {Long} redPacketSponsorAmount /活动发起人拆得金额
	 * &#64;apiSuccess {Long} remainingAmount 固定金额活动剩余金额
	 * &#64;apiSuccess {int} openedRedPacketNum 帮拆红包人数
	 * &#64;apiSuccess {int} unopenedRedPacketNum 还剩人数
	 * &#64;apiSuccess {String} sponsorMobilNum 发起活动人手机号
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "totalJoinNum": 1,
	 *          "redPacketTotalAmount": null,
	 *          "redPacketSponsorAmount": null,
	 *          "remainingAmount": null,
	 *          "openedRedPacketNum": 0,
	 *          "unopenedRedPacketNum": 0,
	 *          "sponsorMobilNum": null,
	 *          "invitedInfoList": [
	 *              {
	 *                  "mobileNum": "158****3835",
	 *                  "inviteNum": 5,
	 *                  "getAmount": 13.15
	 *              }
	 *          ],
	 *          "openedRedPacketList": []
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping("fixedAmount/loginPage")
	public ResponseResult<FixedAmountPageVO> fixedAmountLoginPageInfo(@RequestParam("pageNum") Long pageNum,
			@RequestParam("pageSize") Long pageSize) {

		FixedAmountPageDto fixedAmountPageDto = new FixedAmountPageDto();
		fixedAmountPageDto.setPageNum(pageNum);
		fixedAmountPageDto.setPageSize(pageSize.intValue());

		FixedAmountPageVO returnMap = inviteActivityService.queryFixedAmountLoginPageInfo(fixedAmountPageDto);
		return ResponseResult.success(returnMap);
	}

}
