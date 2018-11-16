package finance.web.controller.oauth.activity;

import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.redenvelope.LeaderBoardVO;
import finance.core.common.constants.RedEnvelopConstant;
import finance.core.common.enums.ActivityCodeEnum;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.LeaderBoardTypeEnum;
import finance.core.common.util.ConvertBeanUtil;
import finance.domain.activity.ActivityConfig;
import finance.domain.activity.LeaderBoard;
import finance.domain.user.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.activity.query.ActivityConfigQueryService;
import finance.domainservice.service.activity.query.LeaderBoardQueryService;
import finance.domainservice.service.jwt.JwtService;
import finance.core.dal.dataobject.FinanceUserInfo;

/**
 * <p>推广活动</p>
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
    private LeaderBoardQueryService    leaderBoardQueryService;

    @Resource
    private JwtService                 jwtService;

    @GetMapping("getActivityConfig")
    public ResponseResult<ActivityConfig> getActivityConfig() {
        ActivityConfig activityConfig;
        FinanceUserInfo financeUserInfo = jwtService.getUserInfo();
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
            FinanceUserInfo financeUserInfo = jwtService.getUserInfo();
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

}
