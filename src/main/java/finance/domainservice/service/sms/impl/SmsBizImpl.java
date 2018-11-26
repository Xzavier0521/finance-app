package finance.domainservice.service.sms.impl;

import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import finance.core.dal.dataobject.SmsSendLogDO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import finance.api.model.response.ResponseResult;
import finance.core.common.constants.Constant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.SmsUseType;
import finance.core.dal.dao.SmsSendLogDAO;
import finance.domain.dto.SendSmsCodeDto;
import finance.domainservice.service.sms.SmsBiz;
import finance.domainservice.service.sms.SmsService;
import finance.domainservice.service.validate.ImgValidateService;
import finance.domainservice.service.validate.SmsValidateService;
import finance.ext.integration.sms.DodoSmsClient;

/**
 * <p>发送短信</p>
 * 
 * @author lili
 * @version $Id: SmsBizImpl.java, v0.1 2018/11/24 8:42 PM lili Exp $
 */
@Slf4j
@Service
public class SmsBizImpl implements SmsBiz {

    @Resource
    private SmsService         smsService;
    @Resource
    private SmsValidateService smsValidateService;
    @Resource
    private ImgValidateService imgValidateService;
    @Resource
    private SmsSendLogDAO      smsSendLogMapper;

    @Resource
    private DodoSmsClient      dodoSmsClient;

    @Override
    public ResponseResult<String> sendSmsValidateCode(SendSmsCodeDto paramDto) {
        String mobileNum = paramDto.getMobileNum();
        String validateCode = this.getSmsValidateCode();
        String smsBody = "您的验证码为：" + validateCode + "，5分钟内有效，如非本人操作，请忽略此短信。";

        // 白名单
        Set<String> whitelist = Sets.newHashSet();
        whitelist.add("17192197807");
        // whitelist.add("18101625436");
        log.info("白名单:{}", whitelist);
        if (whitelist.contains(mobileNum)) {
            log.info("手机号码:{},白名单不校验[短信验证码&图片验证码]", mobileNum);
            return ResponseResult.success(null);
        }
        // 处理短信抬头
        String smsHeader = Constant.sms_header.get(paramDto.getPlatformCode());
        if (smsHeader == null) {
            smsHeader = Constant.sms_header.get("finance_home");
        }

        // 校验图片验证码
        Boolean validateRes = this.vidateImgCode(paramDto.getUseType(), paramDto.getImgCodeId(),
            paramDto.getImgCode());
        if (!validateRes) {
            return ResponseResult.error(CodeEnum.smsImgValidateFail);
        }
        // 发送
        ResponseResult<String> sendRes = smsService.sendMsg(mobileNum, smsHeader + smsBody);
        // 采用备用短信通道
        if (!sendRes.isSucceed()) {
            dodoSmsClient.sendSms(mobileNum, smsHeader + smsBody);
        }
        // 缓存短信验证码
        smsValidateService.cacheSmsValidateCode(mobileNum, validateCode, paramDto.getUseType());

        // 记录日志
        SmsSendLogDO smsSendLog = new SmsSendLogDO();
        smsSendLog.setMobileNum(paramDto.getMobileNum());
        smsSendLog.setHeader(smsHeader);
        smsSendLog.setBody(smsBody + (sendRes.isSucceed() ? "" : sendRes.getData()));
        smsSendLog.setSmsType(paramDto.getUseType());
        smsSendLog
            .setSendSuccess(sendRes.isSucceed() ? Integer.valueOf("1") : Integer.valueOf("0"));
        smsSendLogMapper.insertSelective(smsSendLog);

        if (!sendRes.isSucceed()) {
            return ResponseResult.error(CodeEnum.smsSendFail);
        }
        return ResponseResult.success(null);
    }

    private Boolean vidateImgCode(String userType, String imgCodeId, String imgCode) {
        Boolean imgValidateRes;
        if (SmsUseType.simpleRegist.toString().equals(userType)) {
            return true;
        } else {
            imgValidateRes = imgValidateService.validateImgCode(imgCodeId, imgCode);
        }
        return imgValidateRes;
    }

    /**
     * 生成短信验证码.
     * 
     * @return String
     */
    private String getSmsValidateCode() {
        StringBuilder charValue = new StringBuilder();
        String str = "0123456789";
        Random random = new Random();
        for (int i = 0; i < Constant.smscode_length; i++) {
            int number = random.nextInt(str.length());
            charValue.append(str.charAt(number));
        }
        return charValue.toString();
    }
}
