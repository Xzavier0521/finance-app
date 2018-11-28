package finance.web.controller.oauth.third;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.ThirdLoginChannel;
import finance.core.dal.dataobject.UserInfoDO;
import finance.domain.dto.ThirdLoginParamDto;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.userinfo.ThirdBindBiz;

/**
 * 第三方账号服务.
 * 
 * @author hewenbin
 * @version v1.0 2018年8月15日 上午10:58:04 hewenbin
 */
@RequestMapping("thirdLogin")
@RestController
public class ThirdBindApi {

    @Resource
    private ThirdBindBiz thirdBindBiz;
    @Resource
    private JwtService   jwtService;

    /**
     * <pre>
     * &#64;api {POST} thirdLogin/bind 第三方账号绑定
     * &#64;apiName thirdLoginBind 
     * &#64;apiGroup LOGIN
     * &#64;apiVersion 0.1.0
     * &#64;apiDescription 需要先登录
     * &#64;apiParam {String=1} zsAppId 应用ID ，1：金融家
     * &#64;apiParam {String="ios","android"} osType 系统类型
     * &#64;apiParam {String="qq","wechat"} channel 渠道
     * &#64;apiParam {String{1..100}} [code] 授权临时票据，channel=wechat 时必填
     * &#64;apiParam {String{1..100}} [openId]  第三方账户唯一标示，channel=qq时必填
     * &#64;apiParamExample {JSON} Request-example
     * {
     * 	"zsAppId":1,
     * 	"osType":"ios",
     * 	"channel":"qq",
     * 	"openId":"xxxx"
     * }
     * &#64;apiSuccess {Boolean} succeed 是否成功
     * &#64;apiSuccess {String} errorCode 结果码
     * &#64;apiSuccess {String} errorMessage 消息说明
     * &#64;apiSuccess {JSON} data 数据
     * &#64;apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":true
     *  }
     * &#64;apiError 0000000 成功
     * &#64;apiError 9999999 网络返回错误
     * &#64;apiError 0101005 参数不合法
     * &#64;apiError 0101004 获取第三方账号信息失败
     * &#64;apiError 0101007 该账号已被绑定
     * &#64;apiError 0101008 已绑定，xx
     * </pre>
     * 
     * @author hewenbin
     * @version ThirdLoginApi.java, v1.0 2018年8月15日 上午10:44:19 hewenbin
     */
    @PostMapping("bind")
    public ResponseResult<Boolean> thirdLoginBind(@RequestBody XMap paramMap) {
        ThirdLoginParamDto bindDto = paramMap.toBean(ThirdLoginParamDto.class);
        if (!bindDto.validateParam()) {
            return ResponseResult.error(CodeEnum.thirdLoginValidateInvalid);
        }
        UserInfoDO userInfo = jwtService.getUserInfo();
        ResponseResult<Boolean> res = thirdBindBiz.bindUser(userInfo.getId(), bindDto);
        return res;
    }

    /**
     * <pre>
     * &#64;api {POST} thirdLogin/unbind 第三方账号解绑
     * &#64;apiName thirdLoginUnbind 
     * &#64;apiGroup LOGIN
     * &#64;apiVersion 0.1.0
     * &#64;apiDescription 需要先登录
     * &#64;apiParam {String="qq","wechat"} channel 渠道
     * &#64;apiParamExample {JSON} Request-example
     * {
     * 	"channel":"qq"
     * }
     * &#64;apiSuccess {Boolean} succeed 是否成功
     * &#64;apiSuccess {String} errorCode 结果码
     * &#64;apiSuccess {String} errorMessage 消息说明
     * &#64;apiSuccess {JSON} data 数据
     * &#64;apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":true
     *  }
     * &#64;apiError 0000000 成功
     * &#64;apiError 9999999 网络返回错误
     * &#64;apiError 0101006 参数不合法
     * </pre>
     * 
     * @author hewenbin
     * @version ThirdBindApi.java, v1.0 2018年8月16日 下午1:56:55 hewenbin
     */
    @PostMapping("unbind")
    public ResponseResult<Boolean> thirdLoginUnbind(@RequestBody XMap paramMap) {
        Object channelObj = paramMap.get("channel");
        if (channelObj == null || !ThirdLoginChannel.contains(channelObj.toString())) {
            return ResponseResult.error(CodeEnum.thirdUnbindValidateInvalid);
        }
        UserInfoDO userInfo = jwtService.getUserInfo();
        thirdBindBiz.unBindUser(userInfo.getId(), channelObj.toString());
        return ResponseResult.success(true);
    }

}
