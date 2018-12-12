package cn.zhishush.finance.web.controller.oauth.activity;

import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.constants.RedEnvelopConstant;
import cn.zhishush.finance.core.common.enums.ActivityCodeEnum;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.enums.LeaderBoardTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.exception.BizException;
import cn.zhishush.finance.core.common.util.ConvertBeanUtil;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.core.common.util.ValidatorTools;
import cn.zhishush.finance.domain.activity.ActivityConfig;
import cn.zhishush.finance.domain.activity.LeaderBoard;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.service.activity.ActivityOperationRecordService;
import cn.zhishush.finance.domainservice.service.activity.query.ActivityConfigQueryService;
import cn.zhishush.finance.domainservice.service.activity.query.LeaderBoardQueryService;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import cn.zhishush.finance.core.dal.dataobject.user.UserInfoDO;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.request.ActivityRecordRequest;
import cn.zhishush.finance.api.model.response.ActivityRecordResponse;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.response.ValidateResponse;
import cn.zhishush.finance.api.model.vo.redenvelope.LeaderBoardVO;

/**
 * <p>推广活动</p>
 * 
 * @author lili
 * @version $Id: ActivityController.java, v0.1 2018/10/11 3:31 PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("activity")
public class ActivityController {

    @Resource
    private ActivityConfigQueryService activityConfigQueryService;

    @Resource
    private LeaderBoardQueryService leaderBoardQueryService;

    @Resource
    private JwtService jwtService;

    @Resource
    private ActivityOperationRecordService activityOperationRecordService;

    @GetMapping("getActivityConfig")
    public ResponseResult<ActivityConfig> getActivityConfig() {
        ActivityConfig activityConfig;
        UserInfoDO financeUserInfo = jwtService.getUserInfo();
        if (Objects.nonNull(financeUserInfo)) {
            UserInfo userInfo = UserInfoConverter.convert(financeUserInfo);
            activityConfig = activityConfigQueryService.querySpreadUrlByCode(userInfo,
                ActivityCodeEnum.EBAY_ONE_CENT);
            return ResponseResult.success(activityConfig);
        } else {
            return ResponseResult.error(CodeEnum.bankCardNotExist);
        }

    }

    @GetMapping("/queryCurrentLeaderBoard")
    public ResponseResult<LeaderBoardVO> queryCurrentLeaderBoard(@RequestParam("leaderBoardType") String type) {
        log.info("[开始查询用户当前排行榜],请求参数:type:{}", type);
        ResponseResult<LeaderBoardVO> response;
        try {
            UserInfoDO financeUserInfo = jwtService.getUserInfo();
            LeaderBoard leaderBoard = leaderBoardQueryService.queryCurrentLeaderBoard(
                UserInfoConverter.convert(financeUserInfo), LeaderBoardTypeEnum.getByRanking(type));
            LeaderBoardVO leaderBoardVO = LeaderBoardVO.builder().build();
            ConvertBeanUtil.copyBeanProperties(leaderBoard, leaderBoardVO);
            leaderBoardVO.setRanking(String.valueOf(leaderBoard.getRanking()));
            if (leaderBoard.getRanking() > RedEnvelopConstant.LEADER_BOARD_NUM) {
                leaderBoardVO.setRanking("1000+");
            }
            response = ResponseResult.success(leaderBoardVO);
        } catch (final Exception e) {
            log.error("[查询用户当前排行榜],异常:{}", ExceptionUtils.getStackTrace(e));
            response = ResponseResult.error(CodeEnum.systemError);
        }
        log.info("[结束查询用户当前排行榜],请求参数:type:{}，返回结果:{}", type, response);
        return response;
    }

    @PostMapping("saveRecord")
    public ResponseResult<ActivityRecordResponse> saveRecord(@RequestBody ActivityRecordRequest request) {
        ResponseResult<ActivityRecordResponse> response;
        log.info("[开始保存红包活动奖励记录],请求参数:{}", request);
        try {
            ValidateResponse validateResponse = ValidatorTools.validate(request);
            PreconditionUtils.checkArgument(validateResponse.isStatus(), ReturnCode.PARAM_EMPTY);
            UserInfoDO financeUserInfo = jwtService.getUserInfo();
            activityOperationRecordService.localData(UserInfoConverter.convert(financeUserInfo),
                request.getActivityCode(), request.getActivityRewardType());
            response = ResponseResultUtils.success(null);
        } catch (BizException bizEx) {
            ReturnCode code = ReturnCode.getByCode(bizEx.getErrorCode());
            if (Objects.nonNull(code)) {
                response = ResponseResultUtils.error(code);
            } else {
                response = ResponseResultUtils.error(bizEx.getErrorMsg());
            }

        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[保存红包活动奖励记录],请求参数:{},异常;{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束保存红包活动奖励记录],请求参数:{},返回结果:{}", request, response);
        return response;
    }
}
