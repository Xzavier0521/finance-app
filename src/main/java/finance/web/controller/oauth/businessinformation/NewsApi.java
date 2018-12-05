package finance.web.controller.oauth.businessinformation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.info.NewsDetailVO;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.NewsTag1;
import finance.domainservice.service.businessinformation.NewsBiz;

/**
 * <p>资讯查询</p>
 *
 * @author lili
 * @version 1.0: NewsApi.java, v0.1 2018/12/5 2:28 PM lili Exp $
 */

@RestController
@RequestMapping("news")
public class NewsApi {

    @Resource
    private NewsBiz newsBiz;

    @GetMapping(value = "newsDetail")
    public ResponseResult<Map<String, List<NewsDetailVO>>> queryNewsDetail(@RequestParam(required = false, name = "maxCount", defaultValue = "15") Integer maxCount,
                                                                           @RequestParam("newsType") String newsType) {
        if (!NewsTag1.contains(newsType)) {
            return ResponseResult.error(CodeEnum.newsParamInvalid);
        }
        Map<String, List<NewsDetailVO>> returnMap = newsBiz.queryNews(newsType, maxCount);
        return ResponseResult.success(returnMap);
    }
}
