package cn.zhishush.finance.web.controller.oauth.activity;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.request.ActivitySaveOperationEventRequest;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.activity.ActivityInviteInfoVO;
import cn.zhishush.finance.api.model.vo.activity.ActivityParticipantInfoVO;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.service.activity.AoMaiJiaActivityService;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;

/**
 * <p>奥买家活动</p>
 *
 * @author lili
 * @version 1.0: AoMaiJiaActivityController.java, v0.1 2018/12/18 11:16 AM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/activity/aomaijia")
public class AoMaiJiaActivityController {

    @Resource
    private AoMaiJiaActivityService aoMaiJiaActivityService;

    @Resource
    private JwtService              jwtService;

    /**
     * 查询活动参与者信息
     * @param inviteCode 邀请码
     * @param activityCode 活动代码
     * @return ResponseResult<ActivityParticipantInfoVO>
     */
    @GetMapping("getParticipantInfo")
    public ResponseResult<ActivityParticipantInfoVO> queryParticipantInfo(@RequestParam("inviteCode") String inviteCode,
                                                                          @RequestParam("activityCode") String activityCode) {
        log.info("[开始查询活动参与者信息],请求参数,inviteCode:{},activityCode:{}", inviteCode, activityCode);
        ResponseResult<ActivityParticipantInfoVO> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            ActivityParticipantInfoVO activityParticipantInfoVO = aoMaiJiaActivityService
                .queryParticipantInfo(userInfo, inviteCode, activityCode);
            response = ResponseResultUtils.success(activityParticipantInfoVO);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询活动参与者信息],请求参数,inviteCode:{},activityCode:{},异常:{}", inviteCode,
                activityCode, ExceptionUtils.getStackTrace(e));

        }
        log.info("[结束查询活动参与者信息],请求参数,inviteCode:{},activityCode:{}", inviteCode, activityCode);
        return response;
    }

    /**
     * 邀请数据查询
     * @param activityCode 活动代码
     */
    @GetMapping("getInviteInfo")
    public ResponseResult<ActivityInviteInfoVO> queryInviteInfo(@RequestParam("activityCode") String activityCode) {
        ResponseResult<ActivityInviteInfoVO> response;
        log.info("[开始查询邀请数据],请求参数,activityCode:{}", activityCode);
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            ActivityInviteInfoVO activityInviteInfoVO = aoMaiJiaActivityService
                .queryInviteInfo(userInfo, activityCode);
            response = ResponseResultUtils.success(activityInviteInfoVO);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询邀请数据],请求参数,activityCode:{},异常:{}", activityCode, activityCode,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询邀请数据],请求参数,activityCode:{},返回结果:{}", activityCode, response);
        return response;
    }

    /**
     * 保存用户操作事件
     * @param request 请求参数
     * @return ResponseResult<Void>
     */
    @PostMapping("saveOperationEvent")
    public ResponseResult<Void> saveOperationEvent(@RequestBody ActivitySaveOperationEventRequest request) {
        ResponseResult<Void> response;
        log.info("[开始保存用户操作事件],请求参数:{}", request);
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            aoMaiJiaActivityService.saveOperationEvent(userInfo, request.getActivityCode(),
                request.getEventType());
            response = ResponseResultUtils.success(null);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[保存用户操作事件],请求参数:{},异常:{}", request, ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束保存用户操作事件],请求参数:{},返回结果:{}", request, response);
        return response;
    }
}
