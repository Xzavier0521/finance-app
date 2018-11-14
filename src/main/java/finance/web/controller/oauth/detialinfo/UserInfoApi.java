package finance.web.controller.oauth.detialinfo;

import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.UserProfitInfoDetailVo;
import finance.api.model.vo.UserInfoDetailVo;
import finance.api.model.vo.userAccount.FinanceUserAccountVo;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.ArithUtil;
import finance.domain.UserInfo;
import finance.domain.dto.IdCardInfoDto;
import finance.domainservice.service.finance.tansAccount.TransAccBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.user.query.UserInfoQueryService;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.model.po.FinanceUserAccount;
import finance.model.po.FinanceUserInfo;

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
    public TransAccBiz           acc;
    @Resource
    public UserInfoBiz           userInfoBiz;
    @Resource
    private JwtService           jwtService;
    @Resource
    private UserInfoQueryService userInfoQueryService;

    /**
     * <pre>
     * @api {GET} /userinfo/myAccount 我的账户信息
     * @apiName myAccount
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 我的账户信息
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {String} data 我的账户信息
     * @apiSuccess {String} data.incomeMoney 冻结金额
     * @apiSuccess {String} data.canWithdrawMoney 可提现金额
     * @apiSuccess {String} data.withdrawedMoney 已提现金额
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{"incomeMoney":11.000000,"canWithdrawMoney":10.000000,"withdrawedMoney":11.000000}
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author yaolei
     * @version UserInfoApi.java, v1.0 2018年7月6日 下午2:41:47 yaolei
     */
    @GetMapping("myAccount")
    public ResponseResult<FinanceUserAccountVo> myAccount() {
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        FinanceUserAccount resAccount = acc.getAccountByUserId(userInfo.getId());
        FinanceUserAccountVo vo = new FinanceUserAccountVo();

        vo.setCanWithdrawMoney(ArithUtil.formatNum(resAccount.getCanWithdrawMoney()));
        vo.setIncomeMoney(ArithUtil.formatNum(resAccount.getIncomeMoney()));
        vo.setWithdrawedMoney(ArithUtil.formatNum(resAccount.getWithdrawedMoney()));

        return ResponseResult.success(vo);

    }

    /**
     * <pre>
     * @api {GET} /userinfo/baseInfo 获取个人基本信息
     * @apiName getBaseInfo
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 个人基本信息，该接口后续根据需求扩展字段
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {String} data.inviteCode 邀请码
     * @apiSuccess {String} data.mobileNum 个人手机号
     * @apiSuccess {Boolean} data.hasAccountPwd 是否已设置账户密码
     * @apiSuccess {String} data.realName 真实姓名
     * @apiSuccess {String} data.idNum 二代身份证号
     * @apiSuccess {Integer} data.authStatus 认证状态(0未完善，1已认证，(-userId)已保存)
     * @apiSuccess {String} data.bankName 银行全称
     * @apiSuccess {String} data.accountNo 银行卡号
     * @apiSuccess {Boolean} data.isBindQq 是否绑定QQ
     * @apiSuccess {Boolean} data.isBindWechat 是否绑定微信
     * @apiSuccessExample {JSON} Success-Response
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author hewenbin
     * @version UserInfoApi.java, v1.0 2018年7月6日 下午4:31:20 hewenbin
     */
    @GetMapping("baseInfo")
    public ResponseResult<UserInfoDetailVo> getBaseInfo() {
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        Long userId = userInfo.getId();
        UserInfoDetailVo detail = userInfoBiz.queryUserInfo(userId);
        return ResponseResult.success(detail);
    }

    /**
     * <pre>
     * @api {GET} /userinfo/myProfitInfo 获取我的收益信息
     * @apiName getMyProfitInfo
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 我的收益信息
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {int} data.availableCoin 可用金币数
     * @apiSuccess {int} data.todayInviteCount 今日邀请人数
     * @apiSuccess {int} data.totalInviteCount 累计邀请人数
     * @apiSuccess {float} data.canWithdrawMoney 可提前金额
     * @apiSuccess {float} data.totalIncome 累计收益
     * @apiSuccessExample {JSON} Success-Response
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author panzhongkang
     * @version UserInfoApi.java, v1.0 2018年9月7日 下午1:31:20 panzhongkang
     */
    @GetMapping("myProfitInfo")
    public ResponseResult<UserProfitInfoDetailVo> getMyProfitInfo() {
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        Long userId = userInfo.getId();
        UserProfitInfoDetailVo detail = userInfoBiz.queryUserProfitInfo(userId);
        return ResponseResult.success(detail);
    }

    /**
     * <pre>
     * @api {GET} /userinfo/teamInfo 获取我的团队信息
     * @apiName getTeamUserList
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 团队成员列表分两级，不分页
     * @apiParam {int=0,1,2} type 查询类型0:所有1:一级客户2:二级客户
     * @apiParam {int{1..2147483647}} [maxCount=1000] 团队成员数量查询上限，超过该上限的数据不返回
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 团队信息
     * @apiSuccess {Object[]} data 邀请人列表
     * @apiSuccess {String} inviteList 邀请人列表
     * @apiSuccess {String} realName 邀请人姓名
     * @apiSuccess {String} mobileNum 邀请人手机号
     * @apiSuccess {String} registerDate 邀请人注册日期
     * @apiSuccessExample {JSON} Success-Response-all
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
     * @apiSuccessExample {JSON} Success-Response-firstGrade
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
     * @apiSuccessExample {JSON} Success-Response-secondGrade
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
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author hewenbin
     * @version UserInfoApi.java, v1.0 2018年7月6日 下午3:06:16 hewenbin
     */
    @GetMapping("teamInfo")
    public ResponseResult<Map<String, Object>> getTeamUserList(@RequestParam(required = false, name = "maxCount", defaultValue = "1000") Integer maxCount,
                                                               @RequestParam(name = "type") int type) {
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        Long userId = userInfo.getId();
        Map<String, Object> dataMap = userInfoBiz.queryInviteListEx(userId, maxCount, type);
        return ResponseResult.success(dataMap);
    }

    /**
     * <pre>
     * @api {POST} userinfo/saveIdCardInfo 保存身份证信息
     * @apiName saveIdCardInfo
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 保存身份证信息
     * @apiParam {String} realName 真实姓名
     * @apiParam {String{18}} idNum 二代身份证号
     * @apiParamExample {JSON} Request-example
     * {
     * 	"realName":"张飞",
     * 	"idNum":"322122189910871234"
     * }
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":true
     *  }
     * @apiError 0000000 成功
     * @apiError 0104005 参数不合法
     * @apiError 0104005 已经身份认证
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author panzhongkang
     * @version IdNumAuthApi.java, v1.0 2018年8月21日 下午14:34:42 panzhongkang
     */
    @PostMapping("saveIdCardInfo")
    public ResponseResult<Boolean> saveIdCardInfo(@RequestBody XMap xmap) {
        IdCardInfoDto idCardInfoDto = xmap.toBean(IdCardInfoDto.class);
        //数据检验
        if (!idCardInfoDto.validateParam()) {
            return ResponseResult.error(CodeEnum.idCardParamInvalid);
        }
        //获取userId
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        idCardInfoDto.setUserId(userInfo.getId());

        ResponseResult<Boolean> res = userInfoBiz.saveIdCardInfo(idCardInfoDto);
        return res;
    }

    /**
     * @api {GET} userinfo/getSleepUserList 查询休眠的好友列表
     * @apiName getSleepUserList
     * @apiGroup USERINFO
     * @apiVersion 0.1.0
     * @apiDescription 查询休眠的好友列表
     * @apiParam {int} pageNum 页数
     * @apiParam {int} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccessExample {JSON} Success-Response
     * HTTP/1.1 200 OK
     * {
     * "errorCode": "0000000",
     * "errorMessage": "success",
     * "data": {
     * "pageSize": 10,
     * "pageNum": 1,
     * "totalCount": 1,
     * "totalPage": 1,
     * "dataList": [
     * {
     * "id": 10,
     * "mobileNum": "171****7807",
     * "inviteCode": "5dc6efac793b4c54a67f4b3a2f0c55aa",
     * "registerDate": "2018/07/20",
     * "status": "1",
     * "isDelete": 0,
     * "creator": "",
     * "updator": "",
     * "version": 1,
     * "createTime": "2018-07-20T10:54:03.000+0000",
     * "updateTime": "2018-07-20T10:54:03.000+0000",
     * "payCoin": true
     * }
     * ],
     * "beginNum": 0
     * },
     * "succeed": true
     * }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     */
    @GetMapping("getSleepUserList")
    public ResponseResult<Page<UserInfo>> querySleepUserList(@RequestParam("pageNum") int pageNum,
                                                             @RequestParam("pageSize") int pageSize) {
        log.info("[查询休眠的好友列表]，请求参数:pageNum:{},pageSize:{}", pageNum, pageSize);
        ResponseResult<Page<UserInfo>> pageResponseResult;
        try {
            FinanceUserInfo userInfo = jwtService.getUserInfo();
            Long userId = userInfo.getId();
            Page<UserInfo> resultPage = userInfoQueryService.querySleepUserInfo(userId, pageNum,
                pageSize);
            pageResponseResult = ResponseResult.success(resultPage);
        } catch (final Exception e) {
            pageResponseResult = ResponseResult.error(CodeEnum.systemError);
            log.error("[查询休眠的好友列表],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[查询休眠的好友列表]，请求参数:pageNum:{},pageSize:{},返回结果:{}", pageNum, pageSize,
            pageResponseResult);
        return pageResponseResult;
    }
}
