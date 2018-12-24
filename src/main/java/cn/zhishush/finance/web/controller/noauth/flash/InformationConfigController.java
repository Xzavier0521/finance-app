package cn.zhishush.finance.web.controller.noauth.flash;

import cn.zhishush.finance.api.model.base.Page;
import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.api.model.vo.productmainpage.ProductMainpageVO;
import cn.zhishush.finance.domain.flash.InformationConfig;
import cn.zhishush.finance.domain.popularize.PopularizeModuleInfo;
import cn.zhishush.finance.domainservice.service.flash.InformationConfigServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @program: finance-app
 * @description:快讯
 * @author: Mr.Zhang
 * @create: 2018-12-22 16:07
 **/
@Slf4j
@RestController
@RequestMapping("/test")
public class InformationConfigController {
    @Resource
    private InformationConfigServer informationConfigServer;

    @GetMapping("openStepRewardsInfo")
    public ResponseResult<Page> queryStepRewardsInfo(
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum) {
            log.info("dd{}"+pageNum);
        Page<InformationConfig> page = informationConfigServer.query4Page(pageSize,pageNum);
        return ResponseResult.success(page);
    }



}
