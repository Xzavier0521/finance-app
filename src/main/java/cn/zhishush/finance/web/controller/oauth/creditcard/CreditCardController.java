package cn.zhishush.finance.web.controller.oauth.creditcard;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.request.CreditCardApplyInfoSaveRequest;
import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.api.model.response.CreditCardApplyInfoSaveResponse;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.creditCard.BankInfoVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardApplyInfoVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardDetailVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardInfoVO;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.exception.BizException;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.repository.account.BankInfoRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CreditCardInfoRepository;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardQueryService;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardService;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.web.controller.response.CreditCardQueryBuilder;

/**
 * <p>信用卡</p>
 *
 * @author lili
 * @version 1.0: CreditCardController.java, v0.1 2018/11/28 10:17 PM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/creditCard")
public class CreditCardController {

    @Resource
    private BankInfoRepository       bankInfoRepository;

    @Resource
    private CreditCardInfoRepository creditCardInfoRepository;

    @Resource
    private JwtService               jwtService;

    @Resource
    private CreditCardService        creditCardService;
    @Resource
    private CreditCardQueryService   creditCardQueryService;

    @GetMapping("getBankList")
    public ResponseResult<Page<BankInfoVO>> queryBankList(@RequestParam("pageSize") int pageSize,
                                                          @RequestParam("pageNum") int pageNum) {

        ResponseResult<Page<BankInfoVO>> response;
        log.info("[开始分页查询信用卡银行列表],请求参数,pageSize:{},pageNum:{}", pageNum, pageSize);
        try {
            Page<BankInfoVO> page = CreditCardQueryBuilder
                .build(bankInfoRepository.query(pageSize, pageNum));
            response = ResponseResult.success(page);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[分页查询信用卡银行列]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束分页查询信用卡银行列表],请求参数,pageSize:{},pageNum:{},返回结果:{}", pageNum, pageSize,
            response);
        return response;
    }

    @GetMapping("getCreditCardList")
    public ResponseResult<Page<CreditCardInfoVO>> queryCreditCardList(@RequestParam("pageSize") int pageSize,
                                                                      @RequestParam("pageNum") int pageNum,
                                                                      @Param("cardCode") String cardCode,
                                                                      @Param("bankCode") String bankCode) {

        ResponseResult<Page<CreditCardInfoVO>> response;
        log.info("[开始分页查询信用卡列表],请求参数,pageSize:{},pageNum:{},cardCode:{},bankCode:{}", pageNum,
            pageSize, cardCode, bankCode);
        try {
            Page<CreditCardInfoVO> page = CreditCardQueryBuilder.build4CreditCardInfo(
                creditCardInfoRepository.query(pageSize, pageNum, cardCode, bankCode));
            response = ResponseResult.success(page);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[分页查询信用卡列表]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束分页查询信用卡列表],请求参数,pageSize:{},pageNum:{},cardCode{},bankCode:{},返回结果:{}",
            pageNum, pageSize, cardCode, bankCode, response);
        return response;
    }

    @GetMapping("getCreditCardDetail")
    public ResponseResult<CreditCardDetailVO> queryCreditCardDetail(@RequestParam("cardCode") String cardCode) {
        ResponseResult<CreditCardDetailVO> response;
        log.info("[开始查询信用卡详情],请求参数,cardCodee:{}", cardCode);
        try {
            response = ResponseResult
                .success(creditCardQueryService.queryCreditCardDetail(cardCode));
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询信用卡详情]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询信用卡详情],请求参数,cardCode:{},返回结果:{}", cardCode, response);
        return response;
    }

    @PostMapping("/saveApplyInfo")
    public ResponseResult<CreditCardApplyInfoSaveResponse> save(@RequestBody CreditCardApplyInfoSaveRequest request) {
        ResponseResult<CreditCardApplyInfoSaveResponse> response;
        log.info("[开始保存信用卡申请记录],请求参数:{}", request);
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            BasicResponse basicResponse = creditCardService.saveApplyInfo(userInfo,
                request.getProductCode());
            if (basicResponse.isSuccess()) {
                response = ResponseResult.success(null);
            } else {
                response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            }
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[保存信用卡申请记录]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束保存信用卡申请记录],请求参数:{},返回结果:{}", request, response);
        return response;
    }

    @GetMapping("/getApplyInfo")
    public ResponseResult<Page<CreditCardApplyInfoVO>> queryApplyInfo(@RequestParam("pageSize") int pageSize,
                                                                      @RequestParam("pageNum") int pageNum) {
        ResponseResult<Page<CreditCardApplyInfoVO>> response;
        log.info("[开始查询信用卡申请记录]");
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            Page<CreditCardApplyInfoVO> page = creditCardQueryService
                .queryApplyInfo(userInfo.getId(), pageSize, pageNum);
            response = ResponseResult.success(page);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询信用卡申请记录]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询信用卡申请记录]，返回结果:{}", response);
        return response;
    }
}
