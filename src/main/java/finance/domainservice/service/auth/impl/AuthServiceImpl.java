package finance.domainservice.service.auth.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import finance.api.model.response.ResponseResult;
import finance.core.common.constants.Constant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.util.LogUtil;
import finance.domain.dto.DataPayAuthResDto;
import finance.domain.dto.UserBankCardDto;
import finance.domainservice.service.auth.AuthService;
import finance.ext.integration.datapay.DataPayClient;

/**
 * <p>注释</p>
 * @author lili
 * @version $Id: AuthServiceImpl.java, v0.1 2018/11/14 1:40 PM lili Exp $
 */
@Component
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Resource
    private DataPayClient       dataPayClient;

    @Value("${bank.card.auth.switch}")
    private String              authSwitch;

    @Override
    public ResponseResult<String> auth(UserBankCardDto bankCardDto) {

        //测试开关打开，直接验证通过
        if (!"1".equals(authSwitch)) {
            logger.info(
                LogUtil.getFormatLog("bank.card.auth.switch=" + authSwitch, "银行卡认证开关关闭，不发送验证请求"));
            return ResponseResult.success(null);
        }
        //调用接口
        DataPayAuthResDto authResDto = dataPayClient.bankCardAuth(bankCardDto);

        if (authResDto != null && Constant.authSucc.equals(authResDto.getCode())) {
            String data = authResDto.getData();
            Map<String, Object> dataMap = (Map) JSON.parse(data);
            if ("2".equals(dataMap.get("state"))) {
                return ResponseResult.error(CodeEnum.bankCardAuthDiffer);
            } else if ("3".equals(dataMap.get("state"))) {
                return ResponseResult.error(CodeEnum.bankCardAuthError);
            }
        } else {
            logger.error(LogUtil.getFormatLog(authResDto != null ? authResDto.getMessage() : null,
                "银行卡验证失败"), "");
            return ResponseResult.error(CodeEnum.bankCardAuthError);
        }
        return ResponseResult.success(null);
    }

}
