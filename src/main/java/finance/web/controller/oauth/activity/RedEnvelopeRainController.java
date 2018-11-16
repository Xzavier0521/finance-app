package finance.web.controller.oauth.activity;

import static finance.core.common.util.PreconditionUtils.checkArgument;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.request.RedEnvelopeRainDataQueryRequest;
import finance.api.model.request.RedEnvelopeRainUserDataRequest;
import finance.api.model.response.BasicResponse;
import finance.api.model.response.RedEnvelopeRainUserDataResponse;
import finance.api.model.response.ResponseResult;
import finance.api.model.response.ValidateResponse;
import finance.api.model.vo.activity.RedEnvelopeRainDataVO;
import finance.api.model.vo.activity.UserCurrentRankingVO;
import finance.api.model.vo.activity.UserRedEnvelopeRainSummaryDataVO;
import finance.core.common.enums.RedEnvelopeRainTimeCodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.*;
import finance.domain.activity.RedEnvelopeRainData;
import finance.domain.activity.UserRedEnvelopeRainSummaryData;
import finance.domain.user.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.activity.RedEnvelopeRainDataService;
import finance.domainservice.service.activity.query.RedEnvelopeRainConfigQueryService;
import finance.domainservice.service.activity.query.RedEnvelopeRainDataQueryService;
import finance.domainservice.service.jwt.JwtService;
import finance.web.controller.response.RedEnvelopeRainDataBuilder;

/**
 * <p>红包雨活动</p>
 *
 * @author lili
 * @version 1.0: RedEnvelopeRainController.java, v0.1 2018/11/14 5:25 PM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/redEnvelopeRain")
public class RedEnvelopeRainController {
    @Resource
    private JwtService                        jwtService;

    @Resource
    private RedEnvelopeRainConfigQueryService redEnvelopeRainConfigQueryService;
    @Resource
    private RedEnvelopeRainDataService        redEnvelopeRainDataService;
    @Resource
    private RedEnvelopeRainDataQueryService   redEnvelopeRainDataQueryService;

    @PostMapping("saveUserData")
    public ResponseResult<RedEnvelopeRainUserDataResponse> saveData(@RequestBody RedEnvelopeRainUserDataRequest request) {
        log.info("[开始红包雨活动数据更新]，请求参数:{}", request);
        ResponseResult<RedEnvelopeRainUserDataResponse> response;
        try {
            ValidateResponse validateResponse = ValidatorTools.validate(request);
            checkArgument(validateResponse.isStatus(), validateResponse.getErrorMsg());
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
            // 根据服务器时间获取编码
            RedEnvelopeRainTimeCodeEnum timeCode = redEnvelopeRainConfigQueryService
                .queryTimeCode(request.getActivityCode(), LocalDateTime.now());
            PreconditionUtils.checkArgument(Objects.nonNull(timeCode),
                ReturnCode.RAIN_RED_ENVELOPE_UN_START);
            BasicResponse basicResponse = redEnvelopeRainDataService.localData(userInfo,
                request.getActivityCode(), request.getTimeCode(), request.getTotalNum(),
                request.getTotalAmount());
            checkArgument(basicResponse.isSuccess(), basicResponse.getReturnMessage());
            response = ResponseResult.success(null);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[红包雨活动数据更新],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束红包雨活动数据更新]，请求参数:{},返回结果:{}", request, response);
        return response;
    }

    @GetMapping("getSummaryData")
    public ResponseResult<UserRedEnvelopeRainSummaryDataVO> querySummaryData(@RequestParam("activityCode") String activityCode) {
        log.info("[开始查询红包雨活动汇总数据],请求参数:{}", activityCode);
        ResponseResult<UserRedEnvelopeRainSummaryDataVO> response;
        try {
            PreconditionUtils.checkArgument(StringUtils.isNotBlank(activityCode), "活动代码不能为空");
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
            UserRedEnvelopeRainSummaryData userRedEnvelopeRainDetail = redEnvelopeRainDataQueryService
                .querySummaryData(userInfo.getId(), activityCode,
                    DateUtils.getCurrentDay(LocalDate.now()));
            UserRedEnvelopeRainSummaryDataVO userRedEnvelopeRainDetailVO = new UserRedEnvelopeRainSummaryDataVO();
            ConvertBeanUtil.copyBeanProperties(userRedEnvelopeRainDetail,
                userRedEnvelopeRainDetailVO);
            response = ResponseResult.success(userRedEnvelopeRainDetailVO);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[查询红包雨活动汇总数据],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询红包雨活动汇总数据]，请求参数:{},返回结果:{}", activityCode, response);
        return response;
    }

    @PostMapping("query4Page")
    public ResponseResult<Page<RedEnvelopeRainDataVO>> query4Page(@RequestBody RedEnvelopeRainDataQueryRequest request) {
        log.info("[开始查询红包雨活动数据],请求参数:{}", request);
        ResponseResult<Page<RedEnvelopeRainDataVO>> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
            Page<RedEnvelopeRainData> dataPage = redEnvelopeRainDataQueryService.query4Page(
                request.getPageSize(), request.getPageNum(), request.getActivityCode(),
                request.getActivityDay(), request.getTimeCode());
            response = ResponseResult.success(RedEnvelopeRainDataBuilder.build(request, dataPage));
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[查询红包雨活动数据],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询红包雨活动数据]，返回结果:{}", response);
        return response;
    }

    @GetMapping("getUserCurrentRanking")
    public ResponseResult<UserCurrentRankingVO> queryUserCurrentRanking(@RequestParam("activityCode") String activityCode) {
        log.info("[开始查询红包雨活动-用户当前排名]，请求参数,activityCode:{}", activityCode);
        ResponseResult<UserCurrentRankingVO> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
            String ranking = redEnvelopeRainDataQueryService.queryUserCurrentRanking(activityCode,
                userInfo.getId());
            UserCurrentRankingVO userCurrentRankingVO = new UserCurrentRankingVO();
            userCurrentRankingVO.setRanking(ranking);
            userCurrentRankingVO.setUserId(userInfo.getId());
            response = ResponseResult.success(userCurrentRankingVO);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[查询红包雨活动-用户当前排名],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询红包雨活动-用户当前排名]，请求参数,activityCode:{}，返回结果:{}", activityCode);
        return response;
    }

    @GetMapping("getUserRankingList")
    public ResponseResult<Page<UserCurrentRankingVO>> queryUserRankingList(@Param("activityCode") String activityCode,
                                                                           @Param("pageSize") int pageSize,
                                                                           @Param("pageNum") int pageNum) {
        ResponseResult<Page<UserCurrentRankingVO>> response;
        try {
            List<RedEnvelopeRainData> redEnvelopeRainDataList = redEnvelopeRainDataQueryService
                .queryRankingList(activityCode, DateUtils.getCurrentDay(LocalDate.now()), pageSize,
                    pageNum);
            Page<UserCurrentRankingVO> page = RedEnvelopeRainDataBuilder.build(pageSize, pageNum,
                redEnvelopeRainDataList);
            response = ResponseResult.success(page);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
        }
        return response;
    }
}
