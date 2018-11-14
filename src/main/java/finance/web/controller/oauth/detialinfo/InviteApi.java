package finance.web.controller.oauth.detialinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.condition.BarrageMessageQueryCondition;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.InviteOrdersVo;
import finance.api.model.vo.userAccount.InviteInfoAndIncomeVo;
import finance.core.common.enums.CodeEnum;
import finance.domain.BarrageMessage;
import finance.domain.InviteInfoAndIncome;
import finance.domain.UserInfo;
import finance.domain.UserInviteInfo;
import finance.domainservice.repository.BarrageMessageRepository;
import finance.domainservice.service.invitefriends.WakeupFriendsService;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.user.query.UserInviteQueryService;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.model.po.FinanceUserInfo;
import finance.web.controller.oauth.detialinfo.builder.InviteInfoAndIncomeBuilder;

/**
 * 邀请、推广相关服务.
 *
 * @author hewenbin
 * @version v1.0 2018年8月15日 下午3:58:07 hewenbin
 */
@Slf4j
@RequestMapping("invite")
@RestController
public class InviteApi {

    @Resource
    private UserInfoBiz              userInfoBiz;

    @Resource
    private JwtService               jwtService;
    @Resource
    private UserInviteQueryService   userInviteQueryService;

    @Resource
    private WakeupFriendsService     wakeupFriendsService;

    @Resource
    private BarrageMessageRepository barrageMessageRepository;

    /**
     * <pre>
     * @api {GET} invite/rankingList 查询邀请人数排行榜
     * @apiName queryRankingList
     * @apiGroup INVITE
     * @apiVersion 0.1.0
     * @apiDescription 对所有的邀请人数量做排序，查询前30个
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {Array} data.list
     * @apiSuccess {String} list.realName 姓名
     * @apiSuccess {Int} list.inviteNum 邀请人数（推广量）
     * @apiSuccess {Int} list.orderNum 名次
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"list":[
     *   		{"realName":"张三","inviteNum":1000,"orderNum":"1"},
     *   		{"realName":"李四","inviteNum":999,"orderNum":"2"},
     *   		{"realName":"王五","inviteNum":998,"orderNum":"3"},
     *   	]
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * </pre>
     *
     * @author hewenbin
     * @version InviteApi.java, v1.0 2018年8月15日 下午4:02:40 hewenbin
     */
    @GetMapping("rankingList")
    public ResponseResult<Map<String, List<InviteOrdersVo>>> queryRankingList() {
        List<InviteOrdersVo> orders = userInfoBiz.queryInviteOrders();
        Map<String, List<InviteOrdersVo>> res = new HashMap<>();
        res.put("list", orders);
        return ResponseResult.success(res);
    }

    /**
     * @api {GET} invite/getUserInviteList 查询用户的好友列表带收益
     * @apiName getUserInviteList
     * @apiGroup INVITE
     * @apiVersion 0.1.0
     * @apiDescription 查询用户的好友列表带收益
     * @apiParam {int} pageNum 页数
     * @apiParam {int} pageSize 每页条数
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *{
     *     "errorCode": "0000000",
     *     "errorMessage": "success",
     *     "data": {
     *         "pageSize": 10,
     *         "pageNum": 1,
     *         "totalCount": 1,
     *         "totalPage": 1,
     *         "dataList": [
     *             {
     *                 "parentUserId": 821,
     *                 "userId": 8,
     *                 "phoneNumber": "13761207492",
     *                 "registerDate": "2018/07/20",
     *                 "totalIncome": 0,
     *                 "predictIncome": 10.005
     *             }
     *         ],
     *         "beginNum": 0
     *     },
     *     "succeed": true
     * }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * */
    @GetMapping("getUserInviteList")
    public ResponseResult<Page<InviteInfoAndIncomeVo>> queryInviteList(@RequestParam("pageNum") int pageNum,
                                                                       @RequestParam("pageSize") int pageSize) {
        log.info("[查询用户的邀请好友列表带收益]，请求参数:pageNum:{},pageSize:{}", pageNum, pageSize);
        ResponseResult<Page<InviteInfoAndIncomeVo>> pageResponseResult;
        try {
            FinanceUserInfo userInfo = jwtService.getUserInfo();
            Long userId = userInfo.getId();
            Page<InviteInfoAndIncome> resultPage = userInviteQueryService
                .queryInviteInfoAndIncome(userId, pageSize, pageNum);
            Page<InviteInfoAndIncomeVo> responsePage = InviteInfoAndIncomeBuilder.build(resultPage);
            pageResponseResult = ResponseResult.success(responsePage);
        } catch (final Exception e) {
            pageResponseResult = ResponseResult.error(CodeEnum.systemError);
            log.error("[查询用户的邀请好友列表带收益]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[查询用户的邀请好友列表带收益]，请求参数:pageNum:{},pageSize:{},返回结果:{}", pageNum, pageSize,
            pageResponseResult);
        return pageResponseResult;
    }

    /**
      * @api {POST} invite/wakeup 唤醒好友
      * @apiName  wakeup
      * @apiGroup INVITE
      * @apiVersion 0.1.0
      * @apiDescription 唤醒好友
      * @apiParam {Long} userId 需要唤醒的用户id
      * @apiSuccess {Boolean} succeed 是否成功
      * @apiSuccess {String} errorCode 结果码
      * @apiSuccess {String} errorMessage 消息说明
      * @apiSuccessExample {JSON} Success-Response
      *  HTTP/1.1 200 OK
      * {
     *     "errorCode": "0000000",
     *     "errorMessage": "success",
     *     "data": {
     *         "id": 10,
     *         "mobileNum": "17192197807",
     *         "inviteCode": "5dc6efac793b4c54a67f4b3a2f0c55aa",
     *         "status": "1",
     *         "isDelete": 0,
     *         "creator": "",
     *         "updator": "",
     *         "version": 1,
     *         "createTime": "2018-07-20T10:54:03.000+0000",
     *         "updateTime": "2018-07-20T10:54:03.000+0000"
     *     },
     *     "succeed": true
     * }
      * @apiError 0000000 成功
      * @apiError 9999999 网络返回错误
      * */
    @PostMapping("/wakeup")
    public ResponseResult<UserInfo> wakeup(@RequestBody UserInviteInfo userInviteInfo) {
        log.info("[唤醒好友]，请求参数:xMap:{}", userInviteInfo.toString());
        ResponseResult<UserInfo> response;
        try {
            FinanceUserInfo parentUserInfo = jwtService.getUserInfo();
            response = wakeupFriendsService.wakeup(parentUserInfo.getId(),
                userInviteInfo.getUserId(), 50);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.error("[唤醒好友],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[唤醒好友]，请求参数:xMap:{},返回结果:{}", userInviteInfo.toString(), response);
        return response;
    }

    /**
      * @api {GET} invite/getBarrageMessageList 查询弹幕消息
      * @apiName getBarrageMessageList
      * @apiGroup INVITE
      * @apiVersion 0.1.0
      * @apiDescription 查询弹幕消息,目前只支持[用户邀请注册]
      *
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {JSON} data.dataList
     *  HTTP/1.1 200 OK
     *{
     *     "errorCode": "0000000",
     *     "errorMessage": "success",
     *     "data": {
     *         "pageSize": 10,
     *         "pageNum": 1,
     *         "totalCount": 1,
     *         "totalPage": 1,
     *         "dataList": [
     *             {
     *                 "id": 1,
     *                 "messageCode": "user_invite_register",
     *                 "messageDesc": "用户181****5678 刚邀请好友获得0.88元",
     *                 "creator": "system",
     *                 "updator": "system",
     *                 "version": 1,
     *                 "createTime": "2018-09-29T05:33:41.000+0000",
     *                 "updateTime": "2018-09-29T05:35:10.000+0000"
     *             }
     *         ],
     *         "beginNum": 0
     *     },
     *     "succeed": true
     * }
      * @apiError 0000000 成功
      * @apiError 9999999 网络返回错误
      * */
    @GetMapping("/getBarrageMessageList")
    public ResponseResult<Page<BarrageMessage>> queryBarrageMessage(@RequestParam("pageNum") int pageNum,
                                                                    @RequestParam("pageSize") int pageSize) {
        log.info("[查询弹幕消息]，请求参数:pageNum:{},pageSize:{}", pageNum, pageSize);
        ResponseResult<Page<BarrageMessage>> response;
        try {
            response = ResponseResult
                .success(barrageMessageRepository.query(BarrageMessageQueryCondition.builder()
                    .pageNum(pageNum).pageSize(pageSize).build()));
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.info("[查询弹幕消息]，异常:", ExceptionUtils.getStackTrace(e));

        }
        log.info("[查询弹幕消息]，请求参数:pageNum:{},pageSize:{},返回结果:{}", pageNum, pageSize, response);
        return response;
    }
}
