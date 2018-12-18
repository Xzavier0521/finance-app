package cn.zhishush.finance.web.controller.noauth.cashback;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.cashback.CashBackConfigRuleVO;
import cn.zhishush.finance.core.common.enums.ProductTypeEnum;
import cn.zhishush.finance.core.common.enums.ReturnCode;
import cn.zhishush.finance.core.common.util.PreconditionUtils;
import cn.zhishush.finance.core.common.util.ResponseResultUtils;
import cn.zhishush.finance.domainservice.service.cashback.CashBackConfigQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <p>返现规则</p>
 *
 * @author lili
 * @version 1.0: CashBackConfigController.java, v0.1 2018/12/14 5:16 PM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/cashBackConfig")
public class CashBackConfigController {

    @Resource
    private CashBackConfigQueryService cashBackConfigQueryService;

    @GetMapping("queryRuleList")
    public ResponseResult<Page<CashBackConfigRuleVO>> queryCashBackRule(@RequestParam("productType") ProductTypeEnum productType) {
        log.info("[开始查询返现规则列表],请求参数,productType:{}", productType);
        ResponseResult<Page<CashBackConfigRuleVO>> response;
        try {
            //ProductTypeEnum productTypeEnum = ProductTypeEnum.getByCode(productType);
            PreconditionUtils.checkArgument(Objects.nonNull(productType), ReturnCode.SYS_ERROR);
            Page<CashBackConfigRuleVO> page = cashBackConfigQueryService
                    .queryCashBackRule(productType);
            response = ResponseResultUtils.success(page);
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);
            log.error("[查询返现规则列表],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询返现规则列表],请求参数,productType:{},返回结果:{}", productType, response);
        return response;
    }
}
