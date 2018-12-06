package finance.web.controller.oauth.third;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.third.LoanSupermarketStatisticsDataVO;
import finance.core.common.enums.ReturnCode;
import finance.core.common.util.ResponseResultUtils;
import finance.domain.user.UserInfo;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.third.LoanSupermarketQueryService;

/**
 * <p>贷超</p>
 *
 * @author lili
 * @version 1.0: LoanSupermarketQueryController.java, v0.1 2018/12/5 6:04 PM PM lili Exp $
 */
@Slf4j
@RequestMapping("api/loanSupermarket")
@RestController
public class LoanSupermarketQueryController {
    @Resource
    private JwtService                  jwtService;

    @Resource
    private LoanSupermarketQueryService loanSupermarketQueryService;

    @GetMapping("getStatisticsData")
    public ResponseResult<LoanSupermarketStatisticsDataVO> queryStatisticsData() {
        ResponseResult<LoanSupermarketStatisticsDataVO> response;
        log.info("[开始查询贷超统计数据]");
        try {
            UserInfo userInfo = UserInfoConverter.convert(jwtService.getUserInfo());
            response = ResponseResult
                .success(loanSupermarketQueryService.queryStatisticsData(userInfo));
        } catch (final Exception e) {
            response = ResponseResultUtils.error(ReturnCode.SYS_ERROR);

        }
        log.info("[结束查询贷超统计数据],返回结果:{}", response);
        return response;
    }
}
