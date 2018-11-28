package finance.web.controller.oauth.creditcard;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.base.Page;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.creditCard.BankInfoVO;
import finance.api.model.vo.creditCard.CreditCardDetailVO;
import finance.api.model.vo.creditCard.CreditCardInfoVO;
import finance.core.common.enums.ReturnCode;
import finance.core.common.exception.BizException;
import finance.core.common.util.ResponseResultUtils;
import finance.domainservice.repository.BankInfoRepository;
import finance.domainservice.repository.CreditCardInfoRepository;
import finance.domainservice.service.creditcard.CreditCardQueryService;
import finance.web.controller.response.CreditCardQueryBuilder;

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
                                                                      @Param("cardCode") String cardCode) {

        ResponseResult<Page<CreditCardInfoVO>> response;
        log.info("[开始分页查询信用卡列表],请求参数,pageSize:{},pageNum:{}", pageNum, pageSize);
        try {
            Page<CreditCardInfoVO> page = CreditCardQueryBuilder
                .build4CreditCardInfo(creditCardInfoRepository.query(pageSize, pageNum));
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
        log.info("[结束分页查询信用卡列表],请求参数,pageSize:{},pageNum:{},返回结果:{}", pageNum, pageSize, response);
        return response;
    }

    @GetMapping("getCreditCardDetail")
    public ResponseResult<CreditCardDetailVO> getCreditCardDetail(@RequestParam("cardCode") String cardCode) {
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
}
