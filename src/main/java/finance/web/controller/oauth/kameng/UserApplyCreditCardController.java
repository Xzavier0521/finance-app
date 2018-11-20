package finance.web.controller.oauth.kameng;

import finance.api.model.request.UserApplyCreditCardRequest;
import finance.api.model.response.ResponseResult;
import finance.api.model.response.UserApplyCreditCardResponse;
import finance.api.model.response.ValidateResponse;
import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;
import finance.core.common.util.NetUtils;
import finance.core.common.util.ResponseResultUtils;
import finance.core.common.util.ValidatorTools;
import finance.core.dal.dataobject.UserApplyCreditCardDetailDO;
import finance.domain.user.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.kameng.UserApplyCreditCardService;
import finance.ext.api.response.KaMengUserApplyCreditCardResponse;
import finance.ext.integration.kameng.UserApplyCreditCardDetailClient;
import lombok.extern.slf4j.Slf4j;
import nl.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

import static finance.core.common.util.PreconditionUtils.checkArgument;

/**
 * <p>用户申请信用卡-卡盟</p >
 *
 * @author liwei
 * @version $Id: UserApplyCreditCardController.java, v0.1 2018/11/20 上午9:58 PM user Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/kameng")
public class UserApplyCreditCardController {
    @Resource
    private JwtService                      jwtService;
    @Resource
    private UserApplyCreditCardService      userApplyCreditCardService;
    @Resource
    private UserApplyCreditCardDetailClient userApplyCreditCardDetailClient;

    @PostMapping("applyCreditCard")
    public ResponseResult<UserApplyCreditCardResponse> applyCreditCard(HttpServletRequest httpServletRequest,
                                                                       @RequestBody UserApplyCreditCardRequest request) {
        log.info("[开始申请信用卡-卡盟]，请求参数:{}", request);
        ResponseResult<UserApplyCreditCardResponse> response;
        try {
            ValidateResponse validateResponse = ValidatorTools.validate(request);
            checkArgument(validateResponse.isStatus(), validateResponse.getErrorMsg());
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
            String ip = NetUtils.getIpAdrress(httpServletRequest);
            String header = httpServletRequest.getHeader("User-Agent");
            UserAgent userAgent = UserAgent.parseUserAgentString(header);
            //获取用户实名信息
            UserApplyCreditCardDetailDO userApplyCreditCardDetailDO = userApplyCreditCardService
                .selectUserRealNameInfo(userInfo, userAgent, ip, request.getProductId());
            if (StringUtils.isBlank(userApplyCreditCardDetailDO.getUserName())
                || StringUtils.isBlank(userApplyCreditCardDetailDO.getIdNum())) {
                response = ResponseResultUtils.error(ReturnCode.ID_INFO_NOT_SAVE);
            } else if (null == userApplyCreditCardDetailDO.getProductId()) {
                response = ResponseResultUtils.error(ReturnCode.CREDIT_CARD_ID_NOT_EXIST);
            }
            //调用卡盟申请信用卡接口
            KaMengUserApplyCreditCardResponse kaMengResponse = userApplyCreditCardDetailClient
                .applyCreditCard(userApplyCreditCardDetailDO);
            userApplyCreditCardDetailDO.setStatus(kaMengResponse.getStatus());
            //保存用户申请信息
            userApplyCreditCardService.insertData(userApplyCreditCardDetailDO);
            UserApplyCreditCardResponse userApplyCreditCardResponse = new UserApplyCreditCardResponse();
            userApplyCreditCardResponse.setApplyStatus(kaMengResponse.getStatus());
            userApplyCreditCardResponse.setMessage(kaMengResponse.getMessage());
            response = ResponseResult.success(userApplyCreditCardResponse);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[用户申请信用卡结束],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束申请信用卡-卡盟]，请求参数:{},返回结果:{}", request, response);
        return response;
    }
}
