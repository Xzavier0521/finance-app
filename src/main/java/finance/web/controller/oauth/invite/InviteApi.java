package finance.web.controller.oauth.invite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.condition.BarrageMessageQueryCondition;
import finance.api.model.response.ResponseResult;
import finance.api.model.response.UserInviteInfoQueryResponse;
import finance.api.model.vo.invite.InviteOrdersVo;
import finance.api.model.vo.userAccount.InviteInfoAndIncomeVo;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseResultUtils;
import finance.core.dal.dataobject.UserInfoDO;
import finance.domain.log.BarrageMessage;
import finance.domain.team.InviteInfoAndIncome;
import finance.domain.user.UserInfo;
import finance.domain.user.UserInviteInfo;
import finance.domainservice.repository.BarrageMessageRepository;
import finance.domainservice.repository.UserInviteRepository;
import finance.domainservice.service.invitefriends.WakeupFriendsService;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.user.query.UserInviteQueryService;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.web.controller.oauth.invite.builder.InviteInfoAndIncomeBuilder;

/**
 * <p>邀请、推广相关服务.</p>
 * @author lili
 * @version 1.0: InviteApi.java, v0.1 2018/11/26 10:11 AM lili Exp $
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

    @Resource
    private UserInviteRepository     userInviteRepository;

    @GetMapping("rankingList")
    public ResponseResult<Map<String, List<InviteOrdersVo>>> queryRankingList() {
        List<InviteOrdersVo> orders = userInfoBiz.queryInviteOrders();
        Map<String, List<InviteOrdersVo>> res = new HashMap<>();
        res.put("list", orders);
        return ResponseResult.success(res);
    }

    @GetMapping("getUserInviteList")
    public ResponseResult<Page<InviteInfoAndIncomeVo>> queryInviteList(@RequestParam("pageNum") int pageNum,
                                                                       @RequestParam("pageSize") int pageSize) {
        log.info("[查询用户的邀请好友列表带收益]，请求参数:pageNum:{},pageSize:{}", pageNum, pageSize);
        ResponseResult<Page<InviteInfoAndIncomeVo>> pageResponseResult;
        try {
            UserInfoDO userInfo = jwtService.getUserInfo();
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

    @PostMapping("/wakeup")
    public ResponseResult<UserInfo> wakeup(@RequestBody UserInviteInfo userInviteInfo) {
        log.info("[唤醒好友]，请求参数:xMap:{}", userInviteInfo.toString());
        ResponseResult<UserInfo> response;
        try {
            UserInfoDO parentUserInfo = jwtService.getUserInfo();
            response = wakeupFriendsService.wakeup(parentUserInfo.getId(),
                userInviteInfo.getUserId(), 50);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.error("[唤醒好友],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[唤醒好友]，请求参数:xMap:{},返回结果:{}", userInviteInfo.toString(), response);
        return response;
    }

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

    @GetMapping("/getInviteInfo")
    public ResponseResult<UserInviteInfoQueryResponse> queryInviteInfo(@RequestParam("activityCode") String activityCode) {
        log.info("[开始查询用户邀请信息]，请求参数:activityCode:{}", activityCode);
        ResponseResult<UserInviteInfoQueryResponse> response;
        try {
            PreconditionUtils.checkArgument(StringUtils.isNotBlank(activityCode),
                ReturnCode.PARAM_EMPTY);
            UserInfoDO userInfo = jwtService.getUserInfo();
            Long userId = userInfo.getId();
            UserInviteInfoQueryResponse userInviteInfoQueryResponse = new UserInviteInfoQueryResponse();
            userInviteInfoQueryResponse
                .setFirstInviteNum(userInviteRepository.countFirstInviteNum(userId, activityCode));
            userInviteInfoQueryResponse.setMobileNum(userInfo.getMobileNum());
            userInviteInfoQueryResponse.setUserId(userId);
            userInviteInfoQueryResponse.setActivityCode(activityCode);
            response = ResponseResult.success(userInviteInfoQueryResponse);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.info("[查询用户邀请信息]，请求参数:activityCode:{},异常:{}", activityCode,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询用户邀请信息]，请求参数:activityCode:{},返回结果:{}", activityCode, response);
        return response;
    }
}
