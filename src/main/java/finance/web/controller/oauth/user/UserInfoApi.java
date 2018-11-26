package finance.web.controller.oauth.user;

import java.util.Map;

import javax.annotation.Resource;

import finance.core.dal.dataobject.UserInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.userinfo.UserInfoDetailVo;
import finance.api.model.vo.userinfo.UserProfitInfoDetailVo;
import finance.api.model.vo.userAccount.FinanceUserAccountVo;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.ArithUtil;
import finance.core.dal.dataobject.UserAccountDO;
import finance.domain.dto.IdCardInfoDto;
import finance.domain.user.UserInfo;
import finance.domainservice.service.finance.tansAccount.TransAccBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.user.query.UserInfoQueryService;
import finance.domainservice.service.userinfo.UserInfoBiz;

/**
 * 用户基本信息接口.
 *
 * @author hewenbin
 * @version v1.0 2018年7月6日 下午2:35:25 hewenbin
 */
@Slf4j
@RestController
@RequestMapping("userinfo")
public class UserInfoApi {

	@Resource
	public TransAccBiz acc;
	@Resource
	public UserInfoBiz userInfoBiz;
	@Resource
	private JwtService jwtService;
	@Resource
	private UserInfoQueryService userInfoQueryService;

	/**
	 * <pre>
	 * &#64;api {GET} /user/myAccount 我的账户信息
	 * &#64;apiName myAccount
	 * &#64;apiGroup USERINFO
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 我的账户信息
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {String} data 我的账户信息
	 * &#64;apiSuccess {String} data.incomeMoney 冻结金额
	 * &#64;apiSuccess {String} data.canWithdrawMoney 可提现金额
	 * &#64;apiSuccess {String} data.withdrawedMoney 已提现金额
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":{"incomeMoney":11.000000,"canWithdrawMoney":10.000000,"withdrawedMoney":11.000000}
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author yaolei
	 * @version UserInfoApi.java, v1.0 2018年7月6日 下午2:41:47 yaolei
	 */
	@GetMapping("myAccount")
	public ResponseResult<FinanceUserAccountVo> myAccount() {
		UserInfoDO userInfo = jwtService.getUserInfo();
		UserAccountDO resAccount = acc.getAccountByUserId(userInfo.getId());
		FinanceUserAccountVo vo = new FinanceUserAccountVo();

		vo.setCanWithdrawMoney(ArithUtil.formatNum(resAccount.getCanWithdrawMoney()));
		vo.setIncomeMoney(ArithUtil.formatNum(resAccount.getIncomeMoney()));
		vo.setWithdrawedMoney(ArithUtil.formatNum(resAccount.getWithdrawedMoney()));

		return ResponseResult.success(vo);

	}

	/**
	 * <pre>
	 * &#64;api {GET} /user/baseInfo 获取个人基本信息
	 * &#64;apiName getBaseInfo
	 * &#64;apiGroup USERINFO
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 个人基本信息，该接口后续根据需求扩展字段
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccess {String} data.inviteCode 邀请码
	 * &#64;apiSuccess {String} data.mobileNum 个人手机号
	 * &#64;apiSuccess {Boolean} data.hasAccountPwd 是否已设置账户密码
	 * &#64;apiSuccess {String} data.realName 真实姓名
	 * &#64;apiSuccess {String} data.idNum 二代身份证号
	 * &#64;apiSuccess {Integer} data.authStatus 认证状态(0未完善，1已认证，(-userId)已保存)
	 * &#64;apiSuccess {String} data.bankName 银行全称
	 * &#64;apiSuccess {String} data.accountNo 银行卡号
	 * &#64;apiSuccess {Boolean} data.isBindQq 是否绑定QQ
	 * &#64;apiSuccess {Boolean} data.isBindWechat 是否绑定微信
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":{
	 *   	"inviteCode":"b86b2476629f436d8b4a56a96f95b32a",
	 *   	"mobileNum":"13589891234",
	 *   	"hasAccountPwd":true,
	 *   	"realName":"张飞",
	 *   	"idNum":"322122189910871234",
	 *   	"AuthStatus":1,
	 *   	"bankName":"上海银行",
	 *   	"accountNo":"2342973492734923",
	 *   	"isBindQq":true,
	 *   	"isBindWechat":true,
	 *   }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author hewenbin
	 * @version UserInfoApi.java, v1.0 2018年7月6日 下午4:31:20 hewenbin
	 */
	@GetMapping("baseInfo")
	public ResponseResult<UserInfoDetailVo> getBaseInfo() {
		UserInfoDO userInfo = jwtService.getUserInfo();
		Long userId = userInfo.getId();
		UserInfoDetailVo detail = userInfoBiz.queryUserInfo(userId);
		return ResponseResult.success(detail);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /user/myProfitInfo 获取我的收益信息
	 * &#64;apiName getMyProfitInfo
	 * &#64;apiGroup USERINFO
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 我的收益信息
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccess {int} data.availableCoin 可用金币数
	 * &#64;apiSuccess {int} data.todayInviteCount 今日邀请人数
	 * &#64;apiSuccess {int} data.totalInviteCount 累计邀请人数
	 * &#64;apiSuccess {float} data.canWithdrawMoney 可提前金额
	 * &#64;apiSuccess {float} data.totalIncome 累计收益
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":{
	 *      "availableCoin": 50,
	 *   	"todayInviteCount":98,
	 *   	"totalInviteCount":398,
	 *   	"canWithdrawMoney":8.88,
	 *   	"totalIncome":988.88
	 *   }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author panzhongkang
	 * @version UserInfoApi.java, v1.0 2018年9月7日 下午1:31:20 panzhongkang
	 */
	@GetMapping("myProfitInfo")
	public ResponseResult<UserProfitInfoDetailVo> getMyProfitInfo() {
		UserInfoDO userInfo = jwtService.getUserInfo();
		Long userId = userInfo.getId();
		UserProfitInfoDetailVo detail = userInfoBiz.queryUserProfitInfo(userId);
		return ResponseResult.success(detail);
	}

	/**
	 * <pre>
	 * &#64;api {GET} /user/teamInfo 获取我的团队信息
	 * &#64;apiName getTeamUserList
	 * &#64;apiGroup USERINFO
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 团队成员列表分两级，不分页
	 * &#64;apiParam {int=0,1,2} type 查询类型0:所有1:一级客户2:二级客户
	 * &#64;apiParam {int{1..2147483647}} [maxCount=1000] 团队成员数量查询上限，超过该上限的数据不返回
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 团队信息
	 * &#64;apiSuccess {Object[]} data 邀请人列表
	 * &#64;apiSuccess {String} inviteList 邀请人列表
	 * &#64;apiSuccess {String} realName 邀请人姓名
	 * &#64;apiSuccess {String} mobileNum 邀请人手机号
	 * &#64;apiSuccess {String} registerDate 邀请人注册日期
	 * &#64;apiSuccessExample {JSON} Success-Response-all
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "succeed": true,
	 *      "data": {
	 *           "levelCount": {
	 *             "allLevelCount": 6,
	 *             "firstLevelCount": 2,
	 *             "secondLevelCount": 4
	 *           },
	 *          "inviteList": [
	 *              {
	 *                  "mobileNum": "139****6787",
	 *                  "inviteList": [
	 *                      {
	 *                          "mobileNum": "139****0000",
	 *                          "name": "李*",
	 *                          "registerDate": "2018-07-08"
	 *                      },
	 *                      {
	 *                          "mobileNum": "139****0001",
	 *                          "name": "赵*",
	 *                          "registerDate": "2018-07-08"
	 *                      }
	 *                  ],
	 *                  "name": "张*",
	 *                  "registerDate": "2018-07-06"
	 *              },
	 *              {
	 *                  "mobileNum": "139****2222",
	 *                  "inviteList": [
	 *                      {
	 *                          "mobileNum": "139****0000",
	 *                          "name": "李*",
	 *                          "registerDate": "2018-07-08"
	 *                      },
	 *                      {
	 *                          "mobileNum": "139****0001",
	 *                          "name": "赵*",
	 *                          "registerDate": "2018-07-08"
	 *                      }
	 *                  ],
	 *                  "name": "王*",
	 *                  "registerDate": "2018-07-07"
	 *              }
	 *          ]
	 *      }
	 *  }
	 * &#64;apiSuccessExample {JSON} Success-Response-firstGrade
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "succeed": true,
	 *      "data": {
	 *          "inviteList": [
	 *              {
	 *                  "mobileNum": "139****6787",
	 *                  "name": "张*",
	 *                  "registerDate": "2018-07-06"
	 *              },
	 *              {
	 *                  "mobileNum": "139****2222",
	 *                  "name": "王*",
	 *                  "registerDate": "2018-07-07"
	 *              }
	 *          ]
	 *      }
	 *  }
	 * &#64;apiSuccessExample {JSON} Success-Response-secondGrade
	 *  HTTP/1.1 200 OK
	 *  {
	 *      "errorCode": "0000000",
	 *      "errorMessage": "success",
	 *      "succeed": true,
	 *      "data": {
	 *          "inviteList": [
	 *              {
	 *                  "mobileNum": "139****6787",
	 *                  "name": "张*",
	 *                  "registerDate": "2018-07-06"
	 *              },
	 *              {
	 *                  "mobileNum": "139****2222",
	 *                  "name": "王*",
	 *                  "registerDate": "2018-07-07"
	 *              }
	 *          ]
	 *      }
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author hewenbin
	 * @version UserInfoApi.java, v1.0 2018年7月6日 下午3:06:16 hewenbin
	 */
	@GetMapping("teamInfo")
	public ResponseResult<Map<String, Object>> getTeamUserList(
			@RequestParam(required = false, name = "maxCount", defaultValue = "1000") Integer maxCount,
			@RequestParam(name = "type") int type) {
		UserInfoDO userInfo = jwtService.getUserInfo();
		Long userId = userInfo.getId();
		Map<String, Object> dataMap = userInfoBiz.queryInviteListEx(userId, maxCount, type);
		return ResponseResult.success(dataMap);
	}

	/**
	 * <pre>
	 * &#64;api {POST} user/saveIdCardInfo 保存身份证信息
	 * &#64;apiName saveIdCardInfo
	 * &#64;apiGroup USERINFO
	 * &#64;apiVersion 0.1.0
	 * &#64;apiDescription 保存身份证信息
	 * &#64;apiParam {String} realName 真实姓名
	 * &#64;apiParam {String{18}} idNum 二代身份证号
	 * &#64;apiParamExample {JSON} Request-example
	 * {
	 * 	"realName":"张飞",
	 * 	"idNum":"322122189910871234"
	 * }
	 * &#64;apiSuccess {Boolean} succeed 是否成功
	 * &#64;apiSuccess {String} errorCode 结果码
	 * &#64;apiSuccess {String} errorMessage 消息说明
	 * &#64;apiSuccess {JSON} data 数据
	 * &#64;apiSuccessExample {JSON} Success-Response
	 *  HTTP/1.1 200 OK
	 *  {
	 *   "errorCode":"0000000",
	 *   "errorMessage":"成功",
	 *   "succeed",true,
	 *   "data":true
	 *  }
	 * &#64;apiError 0000000 成功
	 * &#64;apiError 0104005 参数不合法
	 * &#64;apiError 0104005 已经身份认证
	 * &#64;apiError 9999999 网络返回错误
	 * </pre>
	 *
	 * @author panzhongkang
	 * @version IdNumAuthApi.java, v1.0 2018年8月21日 下午14:34:42 panzhongkang
	 */
	@PostMapping("saveIdCardInfo")
	public ResponseResult<Boolean> saveIdCardInfo(@RequestBody XMap xmap) {
		IdCardInfoDto idCardInfoDto = xmap.toBean(IdCardInfoDto.class);
		// 数据检验
		if (!idCardInfoDto.validateParam()) {
			return ResponseResult.error(CodeEnum.idCardParamInvalid);
		}
		// 获取userId
		UserInfoDO userInfo = jwtService.getUserInfo();
		idCardInfoDto.setUserId(userInfo.getId());

		ResponseResult<Boolean> res = userInfoBiz.saveIdCardInfo(idCardInfoDto);
		return res;
	}

	/**
	 * @api {GET} user/getSleepUserList 查询休眠的好友列表
	 * @apiName getSleepUserList
	 * @apiGroup USERINFO
	 * @apiVersion 0.1.0
	 * @apiDescription 查询休眠的好友列表
	 * @apiParam {int} pageNum 页数
	 * @apiParam {int} pageSize 每页条数
	 * @apiSuccess {Boolean} succeed 是否成功
	 * @apiSuccess {String} errorCode 结果码
	 * @apiSuccess {String} errorMessage 消息说明
	 * @apiSuccessExample {JSON} Success-Response HTTP/1.1 200 OK { "errorCode":
	 *                    "0000000", "errorMessage": "success", "data": {
	 *                    "pageSize": 10, "pageNum": 1, "totalCount": 1,
	 *                    "totalPage": 1, "dataList": [ { "id": 10, "mobileNum":
	 *                    "171****7807", "inviteCode":
	 *                    "5dc6efac793b4c54a67f4b3a2f0c55aa", "registerDate":
	 *                    "2018/07/20", "status": "1", "isDelete": 0, "creator": "",
	 *                    "updator": "", "version": 1, "createTime":
	 *                    "2018-07-20T10:54:03.000+0000", "updateTime":
	 *                    "2018-07-20T10:54:03.000+0000", "payCoin": true } ],
	 *                    "beginNum": 0 }, "succeed": true }
	 * @apiError 0000000 成功
	 * @apiError 9999999 网络返回错误
	 */
	@GetMapping("getSleepUserList")
	public ResponseResult<Page<UserInfo>> querySleepUserList(@RequestParam("pageNum") int pageNum,
			@RequestParam("pageSize") int pageSize) {
		log.info("[查询休眠的好友列表]，请求参数:pageNum:{},pageSize:{}", pageNum, pageSize);
		ResponseResult<Page<UserInfo>> pageResponseResult;
		try {
			UserInfoDO userInfo = jwtService.getUserInfo();
			Long userId = userInfo.getId();
			Page<UserInfo> resultPage = userInfoQueryService.querySleepUserInfo(userId, pageNum, pageSize);
			pageResponseResult = ResponseResult.success(resultPage);
		} catch (final Exception e) {
			pageResponseResult = ResponseResult.error(CodeEnum.systemError);
			log.error("[查询休眠的好友列表],异常:{}", ExceptionUtils.getStackTrace(e));
		}
		log.info("[查询休眠的好友列表]，请求参数:pageNum:{},pageSize:{},返回结果:{}", pageNum, pageSize, pageResponseResult);
		return pageResponseResult;
	}
}
