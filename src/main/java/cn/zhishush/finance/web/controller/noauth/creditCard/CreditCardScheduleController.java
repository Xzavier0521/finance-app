package cn.zhishush.finance.web.controller.noauth.creditCard;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.creditCard.CardParameter;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardScheduleVO;
import cn.zhishush.finance.api.model.vo.creditCard.CreditCardTeamVO;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domainservice.service.creditcard.CreditCardScheduleServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Map;

/**
 * @program: finance-app
 * @description: 信用卡进度查询
 * @author: Mr.Zhang
 * @create: 2018-12-25 10:39
 **/
@Slf4j
@RestController
@RequestMapping("api/creditCardProgressInquiry")
public class CreditCardScheduleController {
    @Resource
    private CreditCardScheduleServer creditCardScheduleServer;
    @GetMapping("queryTeamProgress")
public ResponseResult<Page<CreditCardTeamVO>> query4Page(@RequestParam Map<String, Object> params,
                                                         @RequestParam("pageSize") int pageSize,
                                                         @RequestParam("pageNum") Long pageNum) {
        log.info("[团队信用卡进度查询],请求参数params{}",params);
        ResponseResult<Page<CreditCardTeamVO>> response;
        try {
            Page<CreditCardTeamVO> page=creditCardScheduleServer.query4Page(params,pageSize,pageNum);

            response = ResponseResultUtils.success(page);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("查询推广模块信息],请求参数:params:{},异常:{}", params,
                    ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询推广模块信息],请求参数:params:{},返回结果:{}",params, response);
        return response;
    }
    @GetMapping("query")
    public  ResponseResult<Page<CreditCardScheduleVO>>query(@Valid CardParameter cardParameter,
                                                            BindingResult bindingResult){

        log.info("[信用卡进度查询],请求参数cardParameter{}",cardParameter);
        ResponseResult<Page<CreditCardScheduleVO>> response;
        if (bindingResult.hasErrors()) {
            log.error("【进度查询】参数不正确, cardParameter={}", cardParameter);
            throw null;
        }
        try {
            Page<CreditCardScheduleVO> page= creditCardScheduleServer.querySchedule(cardParameter);

            response = ResponseResultUtils.success(page);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("查询推广模块信息],请求参数:bindingResult:{},异常:{}", bindingResult,
                    ExceptionUtils.getStackTrace(e));
        }


        return response;

    }
    @GetMapping("getSmsCode")
    public  ResponseResult smsCode(@RequestParam Map<String, Object> params){

      return  ResponseResult .success();
    }


}
