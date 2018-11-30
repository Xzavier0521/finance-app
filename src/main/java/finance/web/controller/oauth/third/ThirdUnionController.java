package finance.web.controller.oauth.third;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.request.ThirdJointLoginRequest;
import finance.api.model.response.BasicResponse;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.third.ThirdUserInfoVO;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;
import finance.core.common.util.ResponseResultUtils;
import finance.core.dal.dataobject.UserInfoDO;
import finance.domain.product.ProductInfo;
import finance.domain.third.ThirdUnionLoginLog;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.repository.ThirdUnionLoginLogRepository;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.third.YunJuHeService;

/**
 * <p>第三方联合登陆</p>
 *
 * @author lili
 * @version 1.0: ThirdUnionController.java, v0.1 2018/11/28 6:12 PM PM lili Exp $
 */
@Slf4j
@RequestMapping("api/thirdUnionLogin")
@RestController
public class ThirdUnionController {

    @Resource
    private JwtService                   jwtService;

    @Resource
    private YunJuHeService               yunJuHeService;

    @Resource
    private ThirdUnionLoginLogRepository thirdUnionLoginLogRepository;

    @PostMapping("yunjuhe")
    public ResponseResult<ThirdUserInfoVO> login(@RequestBody ThirdJointLoginRequest request) {
        ResponseResult<ThirdUserInfoVO> response;
        try {
            UserInfoDO userInfo = jwtService.getUserInfo();
            ProductInfo productInfo = new ProductInfo();
            productInfo.setProductCode(request.getProductCode());
            productInfo.setProductName(request.getProductName());
            BasicResponse basicResponse = yunJuHeService.unionLogin(
                UserInfoConverter.convert(userInfo), request.getRealName(), productInfo);
            if (basicResponse.isSuccess()) {
                ThirdUserInfoVO thirdUserInfoVO = new ThirdUserInfoVO();
                thirdUserInfoVO.setThirdUserId(basicResponse.getReturnMessage());
                response = ResponseResult.success(thirdUserInfoVO);
            } else {
                response = ResponseResultUtils.error(basicResponse.getReturnMessage());
            }
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
        }
        return response;
    }

    public ResponseResult<Page<ThirdUnionLoginLog>> query4Page(@RequestParam("pageSize") int pageSize,
                                                               @RequestParam("pageNum") int pageNum) {

        ResponseResult<Page<ThirdUnionLoginLog>> response;
        log.info("[开始分页查询用户第三方联合登陆日志],请求参数,pageSize:{},pageNum:{}", pageNum, pageSize);
        try {
            UserInfoDO userInfo = jwtService.getUserInfo();
            response = ResponseResult
                .success(thirdUnionLoginLogRepository.query(userInfo.getId(), pageSize, pageNum));
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[分页查询用户第三方联合登陆日志]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束分页查询用户第三方联合登陆日志],请求参数,pageSize:{},pageNum:{},返回结果:{}", pageNum, pageSize,
            response);
        return null;
    }
}
