package cn.zhishush.finance.domainservice.service.sms;

import cn.zhishush.finance.api.model.response.ResponseResult;
import cn.zhishush.finance.domain.dto.SendSmsCodeDto;

/**
 * <p>短信</p>
 * 
 * @author lili
 * @version $Id: SmsBiz.java, v0.1 2018/11/14 2:42 PM lili Exp $
 */
public interface SmsBiz {

    /**
     * 发送短信验证码 
     * @param paramDto 参数
     * @return ResponseResult<String>
     */
    ResponseResult<String> sendSmsValidateCode(SendSmsCodeDto paramDto);

}
