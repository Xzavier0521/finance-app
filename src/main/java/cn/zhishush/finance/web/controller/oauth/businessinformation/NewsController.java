package cn.zhishush.finance.web.controller.oauth.businessinformation;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import cn.zhishush.finance.core.common.enums.CodeEnum;
import cn.zhishush.finance.core.common.enums.NewsTag1;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.exception.BizException;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domain.user.UserInfo;
import cn.zhishush.finance.domainservice.converter.user.UserInfoConverter;
import cn.zhishush.finance.domainservice.service.businessinformation.NewsBiz;
import cn.zhishush.finance.domainservice.service.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

import cn.zhishush.finance.api.model.request.NewsReadRecordSaveRequest;
import cn.zhishush.finance.api.model.response.BasicResponse;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.info.NewsDetailVO;
import cn.zhishush.finance.domainservice.service.news.NewsReadRecordService;

/**
 * <p>资讯查询</p>
 *
 * @author lili
 * @version 1.0: NewsApi.java, v0.1 2018/12/5 2:28 PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("news")
public class NewsController {

    @Resource
    private NewsBiz newsBiz;

    @Resource
    private JwtService jwtService;

    @Resource
    private NewsReadRecordService newsReadRecordService;

    @GetMapping(value = "newsDetail")
    public ResponseResult<Map<String, List<NewsDetailVO>>> queryNewsDetail(@RequestParam(required = false, name = "maxCount", defaultValue = "15") Integer maxCount,
                                                                           @RequestParam("newsType") String newsType) {
        log.info("[开始查询资讯信息],请求参数,maxCount:{},newsType:{}", maxCount, newsType);
        ResponseResult<Map<String, List<NewsDetailVO>>> response;
        try {
            if (!NewsTag1.contains(newsType)) {
                return ResponseResult.error(CodeEnum.newsParamInvalid);
            }
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            log.info("userInfo:{}",userInfo);
            Map<String, List<NewsDetailVO>> returnMap = newsBiz.queryNews(newsType, maxCount,
                userInfo);
            response = ResponseResult.success(returnMap);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.info("[查询资讯信息],请求参数,maxCount:{},newsType:{},异常:{}", maxCount, newsType,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[开始查询资讯信息],请求参数,maxCount:{},newsType:{},返回结果:{}", maxCount, newsType, response);
        return response;
    }

    @PostMapping("saveReadRecord")
    public ResponseResult<Void> saveReadRecord(@RequestBody NewsReadRecordSaveRequest request) {
        log.info("[开始保存资讯文章阅读记录],请求参数,newsId:{}", request.getNewsId());
        ResponseResult<Void> response;
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            BasicResponse basicResponse = newsReadRecordService.localData(request.getNewsId(),
                userInfo);
            if (basicResponse.isSuccess()) {
                response = ResponseResult.success(null);
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
            log.error("[保存资讯文章阅读记录],请求参数,newsId:{}，异常:{}", request.getNewsId(),
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束保存资讯文章阅读记录],请求参数,newsId:{},返回结果:{}", request.getNewsId(), response);
        return response;
    }

}
