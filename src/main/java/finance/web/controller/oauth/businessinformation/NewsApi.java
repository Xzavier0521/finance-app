package finance.web.controller.oauth.businessinformation;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.NewsDetailVO;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.NewsTag1;
import finance.domainservice.service.businessinformation.NewsBiz;

/**
 * @program: finance-server
 *
 * @description: 资讯API
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 14:46
 **/

@RestController
@RequestMapping("news")
public class NewsApi {

    @Autowired
    private NewsBiz newsBiz;

    /**
     * <pre>
     * @api {GET} /news/newsDetail 资讯查询
     * @apiName newsDetail
     * @apiGroup news
     * @apiVersion 0.1.0
     * @apiDescription 资讯查询
     * @apiParam {int{1..2147483647}} [maxCount=15] 默认显示条数
     * @apiParam {String=all,creditCard,financing,loan,insurance} newsType 资讯类型all:所有creditCard:信用卡financing:理财loan:贷款insurance:保险
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object} data 资讯数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "newsList": [
     *              {
     *                  "title": "中国银行信用卡、京东支付达成战略关系",
     *                  "tag1": "信用卡,贷款",
     *                  "tag2": "资讯",
     *                  "bannerUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *                  "createTime": "2018-09-03"
     *              },
     *              {
     *                  "title": "中国银行信用卡、京东支付达成战略关系之二",
     *                  "tag1": "信用卡",
     *                  "tag2": "科普",
     *                  "bannerUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg",
     *                  "createTime": "2018-09-03"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0501001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "newsDetail")
    public ResponseResult<Map<String, List<NewsDetailVO>>> newsDetail(@RequestParam(required = false, name = "maxCount", defaultValue = "15") Integer maxCount,
                                                                      @RequestParam("newsType") String newsType) {
        if (!NewsTag1.contains(newsType)) {
            return ResponseResult.error(CodeEnum.newsParamInvalid);
        }
        Map<String, List<NewsDetailVO>> returnMap = newsBiz.queryNews(newsType, maxCount);
        return ResponseResult.success(returnMap);
    }
}
