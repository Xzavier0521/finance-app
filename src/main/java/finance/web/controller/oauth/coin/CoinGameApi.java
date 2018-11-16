package finance.web.controller.oauth.coin;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.Page;
import finance.api.model.base.XMap;
import finance.api.model.request.ActivityCoinGameQueryRequest;
import finance.api.model.request.BasicRequest;
import finance.api.model.request.PayCoinPlayGameRequest;
import finance.api.model.response.BasicResponse;
import finance.api.model.response.PayCoinPlayGameResponse;
import finance.api.model.response.ResponseResult;
import finance.api.model.response.ValidateResponse;
import finance.api.model.vo.EarlyClockPageVO;
import finance.api.model.vo.MyRecordVO;
import finance.api.model.vo.PushRewardVO;
import finance.api.model.vo.activity.CoinGameVO;
import finance.core.common.constant.Constant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.PreconditionUtils;
import finance.core.common.util.ResponseResultUtils;
import finance.core.common.util.ValidatorTools;
import finance.core.dal.dataobject.FinanceCoinLog;
import finance.domain.user.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.activity.ActivityCoinGameService;
import finance.domainservice.service.activity.query.ActivityCoinGameQueryService;
import finance.domainservice.service.game.CoinBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.web.controller.response.ActivityCoinGameQueryBuilder;

/**
 * <p>金币游戏服务</p>
 * @author lili
 * @version $Id: CoinGameApi.java, v0.1 2018/11/15 10:14 AM lili Exp $
 */
@Slf4j
@RequestMapping("coin/game")
@RestController
public class CoinGameApi {
    @Resource
    private CoinBiz                      coinBizImpl;
    @Resource
    private JwtService                   jwtService;
    @Resource
    private ActivityCoinGameQueryService activityCoinGameQueryService;
    @Resource
    private ActivityCoinGameService      activityCoinGameService;

    @GetMapping
    public ResponseResult<EarlyClockPageVO> queryCoinGameInfo() {
        return coinBizImpl.getClockPageData();
    }

    @GetMapping("mine")
    public ResponseResult<MyRecordVO> queryUserCoinGameInfo(@RequestParam("pageNum") Long pageNum,
                                                            @RequestParam("pageSize") Long pageSize) {
        if (Objects.isNull(pageNum) || Objects.isNull(pageSize)) {
            return ResponseResult.error(CodeEnum.gameParamInvalid);
        }
        Page<FinanceCoinLog> financeCoinLogPage = new Page<>(pageSize.intValue(), pageNum);
        return coinBizImpl.findMyRecordList(financeCoinLogPage);

    }

    @PostMapping
    public ResponseResult<Boolean> earlyCoinGame(@RequestBody XMap paramMap) {
        String method = (String) paramMap.get("method");
        if (Constant.JOIN.equals(method)) {
            return coinBizImpl.joinEarlyCoinGame();
        } else {
            return coinBizImpl.signEarlyCoinGame();
        }

    }

    @GetMapping("pushRewardMsg")
    public ResponseResult<PushRewardVO> pushRewardMsg() {
        return coinBizImpl.pushRewardMsg();
    }

    @PostMapping("payCoinPlayGame")
    public ResponseResult<PayCoinPlayGameResponse> payCoinPlayGame(@RequestBody PayCoinPlayGameRequest request) {
        log.info("[开始支付金币玩游戏]，请求参数:{}", request);
        ResponseResult<PayCoinPlayGameResponse> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            commonValidate(request, userInfo);
            BasicResponse basicResponse = activityCoinGameService.localData(userInfo,
                request.getActivityCode(), request.getGameCode(), request.getCoinNum());
            PreconditionUtils.checkArgument(basicResponse.isSuccess(),
                basicResponse.getReturnMessage());
            response = ResponseResult.success(null);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(e.getMessage());
            log.error("[支付金币玩游戏],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束支付金币玩游戏]，请求参数:{}", request, response);
        return response;
    }

    @PostMapping("queryCoinGameList")
    public ResponseResult<List<CoinGameVO>> queryCoinGameList(@RequestBody ActivityCoinGameQueryRequest request) {
        log.info("[查询用户是否支付金币玩游戏],请求参数:{}", request);
        ResponseResult<List<CoinGameVO>> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            commonValidate(request, userInfo);
            List<String> gameCodes = Arrays.asList(StringUtils.split(request.getGameCodes(), ","));
            response = ActivityCoinGameQueryBuilder.build(activityCoinGameQueryService
                .queryCoinGameList(userInfo, request.getActivityCode(), gameCodes));
        } catch (final Exception e) {
            if ("金币数不足".equals(e.getMessage())) {
                response = ResponseResultUtils.error(ReturnCode.COIN_NUM_NOT_ENOUGH);
            } else {
                response = ResponseResultUtils.error(e.getMessage());
            }
            log.info("[查询用户是否支付金币玩游戏],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[查询用户是否支付金币玩游戏],请求参数:{},返回结果:{}", request, response);
        return response;
    }

    private void commonValidate(BasicRequest request, UserInfo userInfo) {
        ValidateResponse validateResponse = ValidatorTools.validate(request);
        PreconditionUtils.checkArgument(validateResponse.isStatus(),
            validateResponse.getErrorMsg());
        PreconditionUtils.checkArgument(Objects.nonNull(userInfo), ReturnCode.USER_NOT_EXISTS);
    }

}
