package cn.zhishush.finance.web.controller.oauth.wechat;

import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.api.model.vo.weixin.WeiXinUserInfoDetailVO;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domain.weixin.WeCharQrInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.repository.user.UserInviteInfoRepository;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.domainservice.service.query.UserActivityWeChatPubInfoQueryService;
import cn.zhishush.finance.domainservice.service.wechat.WeChatPubQrService;
import cn.zhishush.finance.domainservice.service.wechat.WeiXinUserInfoQueryService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import com.google.common.base.Preconditions;

import cn.zhishush.finance.api.model.request.WeChatBindOpenInfoRequest;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.response.UserActivityWeChatPubInfoQueryResponse;
import cn.zhishush.finance.api.model.response.WeChatCreateQrResponse;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;

/**
 * <p>微信公众号接口</p>
 * 
 * @author lili
 * @version $Id: WeChatController.java, v0.1 2018/10/26 9:56 AM lili Exp $
 */
@Slf4j
@RequestMapping("/weChatPub")
@RestController
public class WeChatController {

    @Resource
    private JwtService jwtService;

    @Resource
    private WeChatPubQrService weChatPubQrService;

    @Resource
    private UserInviteInfoRepository userInviteInfoRepository;
    @Resource
    private UserActivityWeChatPubInfoQueryService userActivityWeChatPubInfoQueryService;

    @Resource
    private WeiXinUserInfoQueryService weiXinUserInfoQueryService;

    /**
     * 绑定open_id
     * 
     * @param request 请求参数-open_id
     * @return ResponseResult<String>
     */
    @PostMapping("bindOpenId")
    public ResponseResult<String> bindOpneId(@RequestBody WeChatBindOpenInfoRequest request) {
        log.info("[绑定用户open_id]，请求参数:{}", request);
        ResponseResult<String> response;
        try {
            UserInfoDO userInfo = jwtService.getUserInfo();
            response = ResponseResult.success("");
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
        }
        log.info("[绑定用户open_id]，请求参数:{},返回结果:{}", request, response);
        return response;
    }

    @GetMapping("createTempQr")
    public ResponseResult<WeChatCreateQrResponse> createTempQr(@Param("activityCode") String activityCode) {
        log.info("[开始生成微信公众号临时二维码],activityCode:{}", activityCode);
        ResponseResult<WeChatCreateQrResponse> response;
        try {
            UserInfoDO userInfo = jwtService.getUserInfo();
            Preconditions.checkArgument(Objects.nonNull(userInfo));
            Preconditions.checkArgument(StringUtils.isNotBlank(activityCode));
            Preconditions.checkArgument(StringUtils.isNotBlank(userInfo.getInviteCode()));
            WeCharQrInfo weCharQrInfo = weChatPubQrService.createTempQr(activityCode,
                userInfo.getInviteCode());
            String url = weCharQrInfo.getUrl();
            if (StringUtils.isNotBlank(url)) {
                response = ResponseResult.success(WeChatCreateQrResponse.builder().url(url)
                    .ticket(weCharQrInfo.getTicket()).qrUrl(weCharQrInfo.getQrUrl()).build());
            } else {
                response = ResponseResult.error(CodeEnum.systemError);
            }
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.error("[生成微信公众号临时二维码],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束生成微信公众号临时二维码],返回结果:{}", response);
        return response;
    }

    @GetMapping("getUserActivityWeChatPubInfo")
    public ResponseResult<UserActivityWeChatPubInfoQueryResponse> getWeChatPubInfo(@Param("activityCode") String activityCode) {
        log.info("[开始查询用户微信关注信息],请求参数,activityCode:{}", activityCode);
        ResponseResult<UserActivityWeChatPubInfoQueryResponse> response;
        try {
            UserActivityWeChatPubInfoQueryResponse queryResponse = new UserActivityWeChatPubInfoQueryResponse();
            UserInfoDO userInfo = jwtService.getUserInfo();
            Preconditions.checkArgument(Objects.nonNull(userInfo));
            Preconditions.checkArgument(StringUtils.isNotBlank(activityCode));
            Preconditions.checkArgument(StringUtils.isNotBlank(userInfo.getInviteCode()));
            Long unsubscribeNum = userActivityWeChatPubInfoQueryService
                .queryUnsubscribeNum(activityCode, userInfo.getInviteCode(), userInfo.getId());
            queryResponse.setActivityCode(activityCode);
            queryResponse.setUserId(userInfo.getId());
            queryResponse.setMobilePhone(userInfo.getMobileNum());
            Long firstInviteNum = userInviteInfoRepository.countFirstInviteNum(userInfo.getId());
            queryResponse
                .setHistorySubscribeNum(Objects.nonNull(firstInviteNum) ? firstInviteNum : 0L);
            queryResponse.setUnsubscribeNum(Objects.nonNull(unsubscribeNum) ? unsubscribeNum : 0L);
            response = ResponseResult.success(queryResponse);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
        }
        log.info("[结束查询用户微信关注信息],请求参数,activityCode:{},返回结果:{}", activityCode, response);
        return response;
    }

    @GetMapping("getWeiXinUserInfoDetail")
    public ResponseResult<WeiXinUserInfoDetailVO> queryWeiXinUserInfoDetail() {
        ResponseResult<WeiXinUserInfoDetailVO> response;
        log.info("[开始查询用户微信信息]");
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            WeiXinUserInfoDetailVO weiXinUserInfoDetailVO = weiXinUserInfoQueryService
                .query(userInfo);
            response = ResponseResult.success(weiXinUserInfoDetailVO);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
        }
        log.info("[结束查询用户微信信息],返回结果:{}", response);
        return response;
    }

}
