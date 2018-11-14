package finance.web.controller.noauth.product;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ProductModuleQueryResponse;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.product.ProductModuleVO;
import finance.core.common.enums.CodeEnum;
import finance.domainservice.service.query.ProductModuleQueryService;

/**
 * <p>产品模块</p>
 * @author lili
 * @version $Id: ProductModuleController.java, v0.1 2018/11/8 1:38 PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/product")
public class ProductModuleController {

    @Resource
    private ProductModuleQueryService productModuleQueryService;

    @GetMapping("/getAllModule")
    public ResponseResult<ProductModuleQueryResponse> queryAllModule() {
        log.info("[开始查询首页产品模块]");
        ResponseResult<ProductModuleQueryResponse> response;
        try {
            List<ProductModuleVO> productModuleVOList = productModuleQueryService.queryAllModule();
            ProductModuleQueryResponse queryResponse = new ProductModuleQueryResponse();
            queryResponse.setItems(productModuleVOList);
            response = ResponseResult.success(queryResponse);
        } catch (final Exception e) {
            response = ResponseResult.error(CodeEnum.systemError);
            log.info("[查询首页产品模块],异常:{}", ExceptionUtils.getStackTrace(e));
        }
        log.info("[结束查询首页产品模块],返回结果:{}", response);
        return response;
    }

}
