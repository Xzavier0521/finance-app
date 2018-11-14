package finance.web.controller.oauth.pwd;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import finance.api.model.response.ResponseResult;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.PwdType;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.userinfo.UserInfoBiz;

/**
 * 提现密码相关服务接口.
 * @author hewenbin
 * @version v1.0 2018年7月5日 下午3:14:11 hewenbin
 */
@RestController
@RequestMapping("pwd")
public class AccountPwdApi {

    @Autowired
    private UserInfoBiz userInfoBiz;
    @Autowired
    private JwtService  jwtService;

    /**
     * <pre>
     * @api {POST} /pwd/saveAccountPwd 保存平台账户密码
     * @apiName saveAccountPwd 
     * @apiGroup PASSWORD
     * @apiVersion 0.1.0
     * @apiDescription 如果原账户密码未设置，不需要短信验证码，否则需要短信验证码
     * @apiParam {String{32}} pwd 账户密码
     * @apiParam {String{4..6}} [mobileCode] 短信验证码（初始设置可不填）
     * @apiParamExample {JSON} Request-example
     *  {		
     *  	  "pwd": "698d51a19d8a121ce581499d7b701668",
     *  	  "mobileCode": "436512"
     *  }
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {NULL} data 忽略，使用succeed判断
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":null
     *  }
     * @apiError 0000000 成功
     * @apiError 9999999 网络返回错误
     * @apiError 0000011 参数不合法
     * @apiError 0000010 失败
     * </pre>
     * @author hewenbin
     * @version PayPwdApi.java, v1.0 2018年7月5日 下午3:18:59 hewenbin
     */
    @PostMapping("saveAccountPwd")
    public ResponseResult<String> saveAccountPwd(@RequestBody Map<String, String> param) {
        String pwd = param.get("pwd");
        String mobileCode = param.get("mobileCode");
        if (StringUtils.isEmpty(pwd) || pwd.length() != 32) {
            return ResponseResult.error(CodeEnum.accountPwdParamInvalid);
        }

        Long userId = jwtService.getUserInfo().getId();
        ResponseResult<String> res = userInfoBiz.savePwd(PwdType.withdraw, userId, pwd, mobileCode);

        return res;
    }
}
