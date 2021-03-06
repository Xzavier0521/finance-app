package cn.zhishush.finance.web.controller.noauth.redenvelopeactivity;

import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.constants.RedEnvelopConstant;
import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.enums.LeaderBoardTypeEnum;
import cn.zhishush.finance.domain.activity.LeaderBoard;
import cn.zhishush.finance.domainservice.service.activity.query.LeaderBoardQueryService;
import cn.zhishush.finance.domainservice.service.activity.query.RedEnvelopeDetailQueryService;
import cn.zhishush.finance.web.controller.response.LeaderBoardResponseBuilder;
import cn.zhishush.finance.web.controller.response.RedEnvelopeDetailQueryResponseBuilder;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.condition.RedEnvelopeDetailQueryCondition;
import cn.zhishush.finance.api.model.request.RedEnvelopeDetailQueryRequest;
import cn.zhishush.finance.api.model.response.LeaderBoardQueryResponse;
import cn.zhishush.finance.api.model.response.RedEnvelopeDetailQueryResponse;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.redenvelope.ParticipantInfoVO;

/**
 * <p>红包活动-排行榜</p>
 * 
 * @author lili
 * @version $Id: RedEnvelopeLeaderBoardController.java, v0.1 2018/10/19 5:02 PM lili Exp $
 */

@Slf4j
@RequestMapping("redEnvelope")
@RestController
public class RedEnvelopeLeaderBoardController {

    @Resource
    private LeaderBoardQueryService leaderBoardQueryService;
    @Resource
    private RedEnvelopeDetailQueryService redEnvelopeDetailQueryService;

    /**
     * 红包活动排行榜查询
     * 
     * @param type
     *            活动类型
     * @return ResponseResult<LeaderBoardQueryResponse>
     */
    @GetMapping("/getLeaderBoard")
    public ResponseResult<LeaderBoardQueryResponse> queryLeaderBoard(@RequestParam("leaderBoardType") String type,
                                                                     @RequestParam("num") Integer num) {
        log.info("[开始查询红包活动排行榜],请求参数:leaderBoardType:{},num:{}", type, num);
        ResponseResult<LeaderBoardQueryResponse> response;
        LeaderBoardTypeEnum leaderBoardType = LeaderBoardTypeEnum.getByRanking(type);
        try {
            if (Objects.isNull(num)) {
                num = RedEnvelopConstant.LEADER_BOARD_NUM;
            }
            List<LeaderBoard> leaderBoardList = leaderBoardQueryService.queryByType(leaderBoardType,
                RedEnvelopConstant.ACTIVITY_CODE, num);
            response = ResponseResult.success(LeaderBoardResponseBuilder.build(leaderBoardList));
        } catch (final Exception e) {
            log.error("查询红包活动排行榜,异常:{}", ExceptionUtils.getStackTrace(e));
            response = ResponseResult.error(CodeEnum.systemError);
        }
        log.info("[结束查询红包活动排行榜],请求参数:{},返回结果:{}", leaderBoardType, response);
        return response;
    }

    /**
     * 查询红包活动明细
     * 
     * @return ResponseResult<RedEnvelopeDetailQueryResponse>
     */
    @PostMapping("/getRedEnvelopeDetailList")
    public ResponseResult<RedEnvelopeDetailQueryResponse> queryRedEnvelopeDetail(@RequestBody RedEnvelopeDetailQueryRequest request) {
        log.info("[开始查询红包活动明细],请求参数:{}", request);
        ResponseResult<RedEnvelopeDetailQueryResponse> response;
        try {
            RedEnvelopeDetailQueryResponse queryResponse = new RedEnvelopeDetailQueryResponse();
            queryResponse.setActivityCode(RedEnvelopConstant.ACTIVITY_CODE);
            Long joinNumbers = redEnvelopeDetailQueryService.queryJoinNum(RedEnvelopConstant.ACTIVITY_CODE);
            queryResponse.setJoinNumbers(joinNumbers);
            //
            RedEnvelopeDetailQueryCondition condition = new RedEnvelopeDetailQueryCondition();
            condition.setActivityCode(RedEnvelopConstant.ACTIVITY_CODE);
            condition.setPageSize(request.getPageSize());
            condition.setCurrentPage(request.getCurrentPage());
            Page<ParticipantInfoVO> page = RedEnvelopeDetailQueryResponseBuilder
                .build(redEnvelopeDetailQueryService.queryDetail4Page(condition));
            queryResponse.setItems(page);
            response = ResponseResult.success(queryResponse);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.error("{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询红包活动明细],请求参数:{},返回结果:{}", request, response);
        return response;
    }

}
