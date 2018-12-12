package cn.zhishush.finance.web.controller.oauth.loan;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.request.LoadApplyInfoSaveRequest;
import cn.zhishush.finance.api.model.response.LoadApplyInfoSaveResponse;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.loan.LoanDetailsVO;
import cn.zhishush.finance.api.model.vo.loan.LoanInfoVO;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.exception.BizException;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domain.cashbak.CashBackConfig;
import cn.zhishush.finance.domain.loan.LoanApplyInfo;
import cn.zhishush.finance.domain.loan.LoanDetails;
import cn.zhishush.finance.domain.loan.LoanInfo;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.repository.third.impl.product.CashBackConfigRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanApplyInfoRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanDetailsRepository;
import cn.zhishush.finance.domainservice.repository.third.impl.product.LoanInfoRepository;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.web.controller.response.LoadInfoQueryBuilder;

/**
 * <p>贷款</p>
 *
 * @author lili
 * @version 1.0: LoadController.java, v0.1 2018/11/29 2:38 AM PM lili Exp $
 */

@Slf4j
@RequestMapping("api/loan/")
@RestController
public class LoadController {

    @Resource
    private JwtService               jwtService;

    @Resource
    private LoanApplyInfoRepository  loanApplyInfoRepository;

    @Resource
    private LoanInfoRepository       loanInfoRepository;

    @Resource
    private LoanDetailsRepository    loanDetailsRepository;

    @Resource
    private CashBackConfigRepository cashBackConfigRepository;

    @GetMapping("getLoanList")
    public ResponseResult<Page<LoanInfoVO>> queryLoanList(@RequestParam("pageSize") int pageSize,
                                                          @RequestParam("pageNum") int pageNum) {
        ResponseResult<Page<LoanInfoVO>> response;
        log.info("[开始分页查询贷款产品列表],请求参数,pageSize:{},pageNum:{}", pageNum, pageSize);
        try {
            Page<LoanInfoVO> page = LoadInfoQueryBuilder
                .build(loanInfoRepository.query(pageSize, pageNum));
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
            log.error("[分页查询贷款产品列表]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束分页查询贷款产品列表],请求参数,pageSize:{},pageNum:{},返回结果:{}", pageNum, pageSize, response);
        return response;
    }

    @GetMapping("getLoanDetail")
    public ResponseResult<LoanDetailsVO> queryLoanDetail(@RequestParam("productCode") String productCode) {
        ResponseResult<LoanDetailsVO> response;
        log.info("[开始查询贷款产品详情],请求参数,productCode:{}", productCode);
        try {
            LoanDetails loanDetails = loanDetailsRepository.query(productCode);
            LoanInfo loanInfo = loanInfoRepository.query(productCode);
            CashBackConfig cashBackConfig = new CashBackConfig();
            if (Objects.nonNull(loanDetails)) {
                cashBackConfig = cashBackConfigRepository.query(loanDetails.getCashbackConfigId());
            }
            LoanDetailsVO loanDetailsVO = LoadInfoQueryBuilder.build(loanInfo, loanDetails,
                cashBackConfig);
            response = ResponseResult.success(loanDetailsVO);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询贷款产品详情]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询贷款产品详情],请求参数,productCode:{},返回结果:{}", productCode, response);
        return response;
    }

    @PostMapping("saveApplyInfo")
    public ResponseResult<LoadApplyInfoSaveResponse> saveAppleInfo(@RequestBody LoadApplyInfoSaveRequest request) {
        ResponseResult<LoadApplyInfoSaveResponse> response;
        log.info("[开始保存贷款申请记录],请求参数:{}", request);
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            LoanApplyInfo loanApplyInfo = new LoanApplyInfo();
            ConvertBeanUtil.copyBeanProperties(request, loanApplyInfo);
            loanApplyInfo.setUserId(userInfo.getId());
            loanApplyInfo.setMobileNum(userInfo.getMobileNum());
            loanApplyInfoRepository.save(loanApplyInfo);
            response = ResponseResult.success(null);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[保存贷款申请记录]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束保存贷款申请记录],请求参数:{},返回结果:{}", request, response);
        return response;
    }

    @GetMapping("queryApplyInfo")
    public ResponseResult<Page<LoanApplyInfo>> queryApplyInfo(@RequestParam("pageSize") int pageSize,
                                                              @RequestParam("pageNum") int pageNum) {
        ResponseResult<Page<LoanApplyInfo>> response;
        log.info("[开始分页查询贷款申请记录],请求参数,pageSize:{},pageNum:{}", pageNum, pageSize);
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            Page<LoanApplyInfo> page = loanApplyInfoRepository.query(userInfo.getId(), pageSize,
                pageNum);
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
            log.error("[查询贷款申请记录]，异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束分页查询贷款申请记录],请求参数,pageSize:{},pageNum:{},返回结果:{}", pageNum, pageSize, response);
        return response;
    }
}
