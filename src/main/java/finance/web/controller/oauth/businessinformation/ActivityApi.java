package finance.web.controller.oauth.businessinformation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.activity.ActivityListVO;
import finance.api.model.vo.activity.FixedAmountPageVO;
import finance.api.model.vo.activity.StepRewardsDetailVo;
import finance.api.model.vo.redenvelope.ParticipantInfoVO;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.activity.ParticipantInfo;
import finance.domain.dto.FixedAmountPageDto;
import finance.domainservice.repository.RedEnvelopeRepository;
import finance.domainservice.service.businessinformation.ActivityBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.trans.InviteActivityService;
import finance.core.dal.dataobject.UserInfoDO;

/**
 * @program: finance-server
 *
 * @description: 活动接口
 *
 * @author: MORUIHAI
 *
 * @create: 2018-09-03 14:25
 **/

@Slf4j
@RestController
@RequestMapping("activity")
public class ActivityApi {
	@Resource
	private JwtService jwtService;
	@Resource
	private ActivityBiz activityBizImpl;
	@Resource
	private InviteActivityService inviteActivityService;

	@Resource
	private RedEnvelopeRepository redEnvelopeRepository;

	/**
	 * <pre>
	 * &#64;api {GET} /activity/activityList 活动列表
	 * &#64;apiName activityList
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 公告展示
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Object[]} data.list 公告数组
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "activityList": [
	 *              {
	 *                  "id": 10,
	 *                  "taskName": "xxxx",
	 *                  "logoUrl": "http://jingrongjia-oss.oss-cn-hangzhou.aliyuncs.com/C%3A%5CUsers%5CAdministrator%5CDesktop%5Ccat.jpg?Expires=5135955392&OSSAccessKeyId=LTAIEEZtE9nJG6UW&Signature=IW6VfFW9HwZO5hqlDEyQWc2eR60%3D",
	 *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg"
	 *              },
	 *              {
	 *                  "id": 12,
	 *                  "taskName": "幸运大转盘",
	 *                  "logoUrl": "http://jingrongjia-oss.oss-cn-hangzhou.aliyuncs.com/C%3A%5CUsers%5CAdministrator%5CDesktop%5Ccat.jpg?Expires=5135960538&OSSAccessKeyId=LTAIEEZtE9nJG6UW&Signature=WPHVJ0v9EVrzHAQfR2%2BRHwXknTc%3D",
	 *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg"
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * &#64;apiError 0302001 参数不合法
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping("activityList")
	public ResponseResult<Map<String, List<ActivityListVO>>> getActivityList() {
		Map<String, List<ActivityListVO>> returnMap = activityBizImpl.getActivityList();
		return ResponseResult.success(returnMap);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/stepRewardsInfo 阶梯红包信息
	 * &#64;apiName stepRewardsInfo
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 阶梯红包信息
	 * &#64;apiParam {Long} [pageNum=1] 页数
	 * &#64;apiParam {Long} [pageSize=5] 每页条数
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {String} isEnd 红包活动是否结束(0否，1是)
	 * &#64;apiSuccess {String} isNew 是否新参加红包活动(0否，1是)
	 * &#64;apiSuccess {BigDecimal} poolAmount 奖池金额
	 * &#64;apiSuccess {Integer} inviteNum 已邀请人数(isNew=0)
	 * &#64;apiSuccess {Integer} joinNum 已参加人数(isNew=1)
	 * &#64;apiSuccess {Integer} stepNo 当前阶梯序号(isNew=0)
	 * &#64;apiSuccess {Integer} thisStepNum 当前阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} thisStepMoney 当前阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {Integer} nextStepNum 下一个阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} nextStepMoney 下一个阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {Integer} nextTwoStepNum 下两个阶梯所需人数(isNew=0)
	 * &#64;apiSuccess {BigDecimal} nextTwoStepMoney 下两个阶梯红包金额(isNew=0)
	 * &#64;apiSuccess {BigDecimal} haveAmount 已获得金额(isNew=0)
	 * &#64;apiSuccess {Object[]} joinList 已参加用户列表(isNew=1)
	 * &#64;apiSuccess {String} joinList.mobileNum 已参加用户列表.手机号(isNew=1)
	 * &#64;apiSuccess {Integer} joinList.inviteNum 已参加用户列表.邀请人数(isNew=1)
	 * &#64;apiSuccess {BigDecimal} joinList.rewardAmount 已参加用户列表.奖励金额(isNew=1)
	 * &#64;apiSuccess {Object[]} inviteList 已邀请用户列表(isNew=0)
	 * &#64;apiSuccess {String} inviteList.mobileNum 已邀请用户列表手机号(isNew=0)
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *     "errorCode": "0000000",
	 *     "errorMessage": "success",
	 *     "data": {
	 *         "isEnd": "0",
	 *         "isNew": "0",
	 *         "poolAmount":200000,
	 *         "inviteNum": 101,
	 *         "joinNum": 1,
	 *         "stepNo": 1,
	 *         "thisStepNum": 200,
	 *         "thisStepMoney": 600,
	 *         "nextStepNum": 300,
	 *         "nextStepMoney": 900,
	 *         "nextTwoStepNum": 400,
	 *         "nextTwoStepMoney": 1200,
	 *         "haveAmount": 300,
	 *         "joinList": [
	 *         {
	 *             "mobileNum":"158****3850",
	 *             "inviteNum":101,
	 *             "rewardAmount":300
	 *         }
	 *         ],
	 *         "inviteList": [
	 *         "158****3850",
	 *         "158****3851",
	 *         "158****3852",
	 *         "158****3853"
	 *         ]
	 *     },
	 *     "succeed": true
	 * }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 */
	@GetMapping("stepRewardsInfo")
	public ResponseResult<StepRewardsDetailVo> queryStepRewardsInfo(
			@RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(name = "pageNum", defaultValue = "1") Long pageNum) {
		Long userId = jwtService.getUserInfo().getId();
		Page<StepRewardsDetailVo> page = new Page<>(pageSize, pageNum);
		StepRewardsDetailVo stepRewardsDetailVo = activityBizImpl.queryStepRewardsInfo(userId, page);
		return ResponseResult.success(stepRewardsDetailVo);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/joinStepReward 参加阶梯红包活动
	 * &#64;apiName joinStepReward
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 参加阶梯红包活动
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {Boolean} data 返回数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 * {
	 *     "errorCode": "0000000",
	 *     "errorMessage": "success",
	 *     "data": true,
	 *     "succeed": true
	 * }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 0601001 已有参加的活动
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 */
	@GetMapping("joinStepReward")
	public ResponseResult<Boolean> joinStepReward() {
		Long userId = jwtService.getUserInfo().getId();
		ResponseResult<Boolean> res = activityBizImpl.saveStepRewardsActivty(userId);
		return res;
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/fixedAmount/entry 活动列表
	 * &#64;apiName fixedAmountEntry
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 固定金额活动入口
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccess {Int} joinStatus 参与状态,1:首次参加,2:参加未完成,3:参加已完成
	 * &#64;apiSuccess {Int} activityId 活动id
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "data": {
	 *          "activityId": null,
	 *          "joinStatus": 1
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping("fixedAmount/entry")
	public ResponseResult<Map<String, Object>> fixedAmountEntry() {
		Map<String, Object> returnMap = inviteActivityService.getFixedAmountEntry();
		return ResponseResult.success(returnMap);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /activity/fixedAmount/page 固定金额活动页面
	 * &#64;apiName fixedAmountPageInfo
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 固定金额活动页面
	 * &#64;apiParam {Long} pageNum 页数
	 * &#64;apiParam {Long} pageSize 每页条数
	 * &#64;apiParam {String} pageType 页数 total:汇总页面,detail:活动明细页面,other:其他页面
	 * &#64;apiParam {Long} activityId 活动id
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
	 *          "totalJoinNum": null,
	 *          "redPacketTotalAmount": 13.15,
	 *          "redPacketSponsorAmount": 3.82,
	 *          "remainingAmount": 0,
	 *          "openedRedPacketNum": 5,
	 *          "unopenedRedPacketNum": 0,
	 *          "sponsorMobilNum": null,
	 *          "invitedInfoList": [
	 *              {
	 *                  "mobileNum": "15821863835",
	 *                  "inviteNum": 5,
	 *                  "getAmount": 13.15
	 *              }
	 *          ],
	 *          "openedRedPacketList": [
	 *              {
	 *                  "mobileNum": "15821863806",
	 *                  "openedAmount": 1.53
	 *              },
	 *              {
	 *                  "mobileNum": "15821863807",
	 *                  "openedAmount": 0.76
	 *              },
	 *              {
	 *                  "mobileNum": "15821863808",
	 *                  "openedAmount": 2.45
	 *              },
	 *              {
	 *                  "mobileNum": "15821863809",
	 *                  "openedAmount": 3.52
	 *              },
	 *              {
	 *                  "mobileNum": "15821863810",
	 *                  "openedAmount": 1.07
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@GetMapping("fixedAmount/page")
	public ResponseResult<FixedAmountPageVO> fixedAmountPageInfo(@RequestParam("pageNum") Long pageNum,
			@RequestParam("pageSize") Long pageSize, @RequestParam("pageType") String pageType,
			@RequestParam("activityId") Long activityId) {

		FixedAmountPageDto fixedAmountPageDto = new FixedAmountPageDto();
		fixedAmountPageDto.setActivityId(activityId);
		fixedAmountPageDto.setPageNum(pageNum);
		fixedAmountPageDto.setPageSize(pageSize.intValue());
		fixedAmountPageDto.setPageType(pageType);

		FixedAmountPageVO returnMap = inviteActivityService.queryFixedAmountPageInfo(fixedAmountPageDto);
		return ResponseResult.success(returnMap);
	}

	/**
	 * <pre>
	 * &#64;api {POST} /activity/fixedAmount/join 固定金额活动点击参加
	 * &#64;apiName fixedAmountJoinInfo
	 * &#64;apiGroup Activity
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 固定金额活动点击参加
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
	 *          "totalJoinNum": null,
	 *          "redPacketTotalAmount": 13.15,
	 *          "redPacketSponsorAmount": 3.82,
	 *          "remainingAmount": 0,
	 *          "openedRedPacketNum": 0,
	 *          "unopenedRedPacketNum": 0,
	 *          "sponsorMobilNum": null,
	 *          "invitedInfoList": [
	 *              {
	 *                  "mobileNum": "15821863835",
	 *                  "inviteNum": 5,
	 *                  "getAmount": 13.15
	 *              }
	 *          ],
	 *          "openedRedPacketList": [
	 *              {
	 *                  "mobileNum": "15821863806",
	 *                  "openedAmount": 1.53
	 *              },
	 *              {
	 *                  "mobileNum": "15821863807",
	 *                  "openedAmount": 0.76
	 *              },
	 *              {
	 *                  "mobileNum": "15821863808",
	 *                  "openedAmount": 2.45
	 *              },
	 *              {
	 *                  "mobileNum": "15821863809",
	 *                  "openedAmount": 3.52
	 *              },
	 *              {
	 *                  "mobileNum": "15821863810",
	 *                  "openedAmount": 1.07
	 *              }
	 *          ]
	 *      },
	 *      "succeed": true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 * 
	 * @author
	 */
	@PostMapping("fixedAmount/join")
	public ResponseResult<FixedAmountPageVO> fixedAmountJoinInfo() {
		ResponseResult<FixedAmountPageVO> returnMap = inviteActivityService.joinFixedAmountActivity();
		return returnMap;
	}

	/**
	 * 查询用户红包互动明细
	 * 
	 * @return ResponseResult<ParticipantInfoVO>
	 */
	@GetMapping("getRedEnvelopSingleDetail")
	public ResponseResult<ParticipantInfoVO> queryRedEnvelopDetail() {
		ResponseResult<ParticipantInfoVO> response;
		try {
			UserInfoDO userInfo = jwtService.getUserInfo();
			if (Objects.nonNull(userInfo)) {
				Long userId = userInfo.getId();
				ParticipantInfo participantInfo = redEnvelopeRepository.querySingleDetail(userId, "0001");
				ParticipantInfoVO participantInfoVO = new ParticipantInfoVO();
				if (Objects.nonNull(participantInfo)) {
					ConvertBeanUtil.copyBeanProperties(participantInfo, participantInfoVO);
				} else {
					participantInfoVO.setSecondInviteNum(0L);
					participantInfoVO.setSecondRewardAmount(BigDecimal.ZERO);
					participantInfoVO.setSecondUnitPrice(BigDecimal.valueOf(0.2));
				}
				response = ResponseResult.success(participantInfoVO);
			} else {
				response = ResponseResult.error(CodeEnum.bankCardNotExist);
			}
		} catch (final Exception e) {
			response = ResponseResult.error(CodeEnum.systemError);
			log.error("[查询用户红包互动明细], 异常:{}", ExceptionUtils.getStackTrace(e));
		}

		return response;
	}
}
