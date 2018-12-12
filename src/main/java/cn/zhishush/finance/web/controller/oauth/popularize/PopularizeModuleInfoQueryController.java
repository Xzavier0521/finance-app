package cn.zhishush.finance.web.controller.oauth.popularize;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.popularize.PopularizeModuleInfoVO;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domainservice.service.popularize.PopularizeModuleInfoQueryService;

/**
 * <p>推广模块信息查询控制器</p>
 *
 * @author lili
 * @version 1.0: PopularizeModuleInfoQueryController.java, v0.1 2018/12/11 2:31 PM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/popularize")
public class PopularizeModuleInfoQueryController {

    @Resource
    private PopularizeModuleInfoQueryService popularizeModuleInfoQueryService;

    @GetMapping("getModuleDetail")
    public ResponseResult<Page<PopularizeModuleInfoVO>> query4Page(@RequestParam("pageSize") int pageSize,
                                                                   @RequestParam("pageNum") int pageNum) {
        log.info("[开始查询推广模块信息],请求参数:pageSize:{},pageNum:{}", pageSize, pageNum);
        ResponseResult<Page<PopularizeModuleInfoVO>> response;
        try {
            Page<PopularizeModuleInfoVO> page = popularizeModuleInfoQueryService
                .query4Page(pageSize, pageNum);
            response = ResponseResultUtils.success(page);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("查询推广模块信息],请求参数:pageSize:{},pageNum:{},异常:{}", pageSize, pageNum,
                ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询推广模块信息],请求参数:pageSize:{},pageNum:{},返回结果:{}", pageSize, pageNum, response);
        return response;
    }
}
