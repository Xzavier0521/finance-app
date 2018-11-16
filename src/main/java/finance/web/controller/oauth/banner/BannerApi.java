package finance.web.controller.oauth.banner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import finance.api.model.response.ResponseResult;
import finance.api.model.vo.BannerDetailVO;
import finance.core.common.enums.ActivityCodeEnum;
import finance.domain.activity.ActivityConfig;
import finance.domainservice.converter.UserInfoConverter;
import finance.domainservice.service.activity.query.ActivityConfigQueryService;
import finance.domainservice.service.banner.BannerBiz;
import finance.domainservice.service.jwt.JwtService;
import finance.core.dal.dataobject.FinanceUserInfo;

/**
 * @program: finance-server
 *
 * @description: banner查询页面
 *
 * @author: MORUIHAI
 *
 * @create: 2018-08-15 13:44
 **/
@Slf4j
@RestController
@RequestMapping("banner")
public class BannerApi {
    @Resource
    private BannerBiz                  bannerBizImpl;
    @Resource
    private JwtService                 jwtService;
    @Resource
    private ActivityConfigQueryService activityConfigQueryService;

    /**
     * <pre>
     * @api {GET} /banner/bannerDetail banner查询接口
     * @apiName bannerDetail
     * @apiGroup banner
     * @apiVersion 0.1.0
     * @apiDescription banner查询接口
     * @apiParam {Long=1,2,3,4} pageCode 页面代码1:主页2：排行榜3:发现4:首页
     * @apiParam {Long=1,2,3,4,5,6} bannerType banner类型
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {Object} data banner数据
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *      "errorCode": "0000000",
     *      "errorMessage": "success",
     *      "data": {
     *          "bannerList": [
     *              {
     *                  "id": 2,
     *                  "pageCode": 1,
     *                  "bannerType": 2,
     *                  "title": "主页底",
     *                  "seqNo": 1,
     *                  "bannerUrl": "https://static.oschina.net/uploads/space/2018/0701/090738_Vk72_2720166.jpg",
     *                  "redirectUrl": "https://www.baidu.com/index.php?tn=monline_3_dg"
     *              }
     *          ]
     *      },
     *      "succeed": true
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0302001 参数不合法
     * </pre>
     * @author
     */
    @GetMapping(value = "bannerDetail")
    public ResponseResult<Map<String, List<BannerDetailVO>>> bannerDetail(@RequestParam("pageCode") Long pageCode,
                                                                          @RequestParam("bannerType") Long bannerType) {
        Map<String, List<BannerDetailVO>> returnMap = new HashMap();
        List<BannerDetailVO> bannerList = bannerBizImpl.queryBannerByCodeAndType(pageCode,
            bannerType);
        //  bannerList = build(bannerList);
        returnMap.put("bannerList", bannerList);
        return ResponseResult.success(returnMap);
    }

    private List<BannerDetailVO> build(List<BannerDetailVO> froms) {
        List<BannerDetailVO> tos = Lists.newArrayList(froms);
        int ebayBannerId = 4;
        if (CollectionUtils.isNotEmpty(tos)) {
            tos.forEach(bannerDetail -> {
                if (bannerDetail.getId().intValue() == ebayBannerId) {
                    FinanceUserInfo userInfo = jwtService.getUserInfo();
                    ActivityConfig activityConfig = activityConfigQueryService.querySpreadUrlByCode(
                        UserInfoConverter.convert(userInfo), ActivityCodeEnum.EBAY_ONE_CENT);
                    if (Objects.nonNull(activityConfig)
                        && StringUtils.isNotBlank(activityConfig.getSpreadUrl())) {
                        bannerDetail.setRedirectUrl(activityConfig.getSpreadUrl());
                    }
                }
            });

        }
        return tos;
    }
}
