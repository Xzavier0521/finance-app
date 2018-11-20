package finance.web.controller.noauth.weixin;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import finance.api.model.response.ResponseResult;
import finance.api.model.response.WeChatCreateQrResponse;
import finance.core.common.constants.WeChatConstant;
import finance.core.common.enums.CodeEnum;
import finance.domain.weixin.WeCharQrInfo;
import finance.domainservice.service.wechat.WeChatPubQrService;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: WeChatApiController.java, v0.1 2018/10/31 3:07 PM lili Exp $
 */
@Slf4j
@RequestMapping("/api/weChatPub")
@RestController
public class WeChatApiController {

    @Value("${weChat.default.inviteCode}")
    private String             defaultInviteCode;
    @Resource
    private WeChatPubQrService weChatPubQrService;

    @GetMapping("createTempQr")
    public ResponseResult<WeChatCreateQrResponse> createTempQr(@Param("activityCode") String activityCode,
                                                               @Param("inviteCode") String inviteCode) {

        log.info("[开始生成微信公众号临时二维码],activityCode:{},inviteCode:{}", activityCode, inviteCode);
        ResponseResult<WeChatCreateQrResponse> response;
        try {
            Preconditions.checkArgument(StringUtils.isNotBlank(activityCode));
            if (StringUtils.isBlank(inviteCode)) {
                inviteCode = defaultInviteCode;
            }
            WeCharQrInfo weCharQrInfo = weChatPubQrService.createTempQr(activityCode, inviteCode);
            String url = weCharQrInfo.getUrl();
            if (StringUtils.isNotBlank(url)) {
                response = ResponseResult.success(
                    WeChatCreateQrResponse.builder().url(url).ticket(weCharQrInfo.getTicket())
                        .expireSeconds(WeChatConstant.QR_EXPIRE_SECONDS).build());
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
}
