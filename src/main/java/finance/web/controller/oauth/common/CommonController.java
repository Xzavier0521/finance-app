package finance.web.controller.oauth.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.common.CommonInfoVO;
import finance.core.common.util.NetUtils;

/**
 * <p>公共控制器</p>
 *
 * @author lili
 * @version 1.0: CommonController.java, v0.1 2018/11/27 6:05 PM PM lili Exp $
 */
@Slf4j
@RestController
@RequestMapping("api/common")
public class CommonController {

    @GetMapping("getIpAddress")
    public ResponseResult<CommonInfoVO> getIpAddress(HttpServletRequest req,
                                                     HttpServletResponse resp) {
        log.info("[开始获取用户ip信息],请求参数:{}", req);
        CommonInfoVO commonInfoVO = new CommonInfoVO();
        commonInfoVO.setIpAddress(NetUtils.getIpAdrress(req));
        ResponseResult<CommonInfoVO> responseResult = ResponseResult.success(commonInfoVO);
        log.info("[开始获取用户ip信息],请求参数:{},返回结果:{}", req, resp);
        return responseResult;
    }
}
