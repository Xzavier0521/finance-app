package finance.web.controller.oauth.detialinfo;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import finance.api.model.base.XMap;
import finance.api.model.response.ResponseResult;
import finance.api.model.vo.BankCardIDSearchVo;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.BankCardUtil;
import finance.domain.dto.UserBankCardDto;
import finance.domainservice.service.jwt.JwtService;
import finance.domainservice.service.userinfo.UserInfoBiz;
import finance.core.dal.dataobject.FinanceUserInfo;

/**
 * 银行卡信息接口.
 * @author hewenbin
 * @version v1.0 2018年7月6日 下午5:00:35 hewenbin
 */
@RestController
@RequestMapping("bankcard")
public class BankCardApi {

    @Resource
    private JwtService  jwtService;
    @Resource
    private UserInfoBiz userInfoBiz;

    /**
     * <pre>
     * @api {POST} /bankcard/saveBankCardInfo 保存个人银行卡信息
     * @apiName saveBankCardInfo 
     * @apiGroup BANKCARD
     * @apiVersion 0.1.0
     * @apiDescription 保存个人银行卡信息
     * @apiParam {String{16..19}} accountNo 银行卡号       
     * @apiParamExample {JSON} Request-example
     * {
     *     "accountNo":"11111111111111111"
     * }
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {NULL} data NULL
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
     * @apiError 0103001 参数不合法
     * @apiError 0104003 不支持的银行卡号
     * @apiError 0104004 个人信息未完善
     * </pre>
     * @author hewenbin
     * @version BankCardApi.java, v1.0 2018年7月6日 下午5:18:16 hewenbin
     */
    @PostMapping("saveBankCardInfo")
    public ResponseResult<String> saveBankCardInfo(@RequestBody XMap xmap) {
        UserBankCardDto bankCardDto = xmap.toBean(UserBankCardDto.class);
        //数据校验
        if (!bankCardDto.validateParam()) {
            return ResponseResult.error(CodeEnum.bankCardParamInvalid);
        }
        //获取银行名称
        String bankName = BankCardUtil.getName(bankCardDto.getAccountNo());
        if (StringUtils.isEmpty(bankName)) {
            return ResponseResult.error(CodeEnum.accountNotExist);
        }
        bankCardDto.setBankName(bankName);
        //获取userId
        FinanceUserInfo userInfo = jwtService.getUserInfo();
        bankCardDto.setUserId(userInfo.getId());
        bankCardDto.setMobileNum(userInfo.getMobileNum());
        ResponseResult<String> res = userInfoBiz.saveBankCard(bankCardDto);
        return res;
    }

    /**
     * <pre>
     * @api {GET} /bankcard/BaseInfo 获取银行卡信息通过卡号
     * @apiName getBankCardInfoByCardNo
     * @apiGroup BANKCARD
     * @apiVersion 0.1.0
     * @apiDescription text
     * @apiParam {String{16..19}} accountNo 银行卡号
     * @apiSuccess {Boolean} succeed 是否成功
     * @apiSuccess {String} errorCode 结果码
     * @apiSuccess {String} errorMessage 消息说明
     * @apiSuccess {JSON} data 数据
     * @apiSuccess {String} bankName 银行名称
     * @apiSuccess {String} accountNo 银行卡号
     * @apiSuccessExample {JSON} Success-Response
     *  HTTP/1.1 200 OK
     *  {
     *   "errorCode":"0000000",
     *   "errorMessage":"成功",
     *   "succeed",true,
     *   "data":{
     *   	"bankName":"上海银行",
     *   	"accountNo":"2342973492734923"
     *   }
     *  }
     * @apiError 0000000 成功
     * @apiError 0104003 不支持的银行卡号
     * @apiError 9999999 网络返回错误
     * </pre>
     * @return
     * @author moruihai
     * @version BankCardApi.java, v1.0
     */
    @GetMapping("BaseInfo")
    public ResponseResult<BankCardIDSearchVo> getBankCardInfoByCardNo(@RequestParam(name = "accountNo") String accountNo) {

        BankCardIDSearchVo bankCardIDSearchVo = new BankCardIDSearchVo();
        bankCardIDSearchVo.setAccountNo(accountNo);
        String bankName = BankCardUtil.getName(accountNo);
        if (StringUtils.isEmpty(bankName)) {
            return ResponseResult.error(CodeEnum.accountNotExist);
        }
        bankCardIDSearchVo.setBankName(bankName);
        return ResponseResult.success(bankCardIDSearchVo);
    }

}
