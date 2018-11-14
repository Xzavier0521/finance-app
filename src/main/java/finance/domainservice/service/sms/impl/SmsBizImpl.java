package finance.domainservice.service.sms.impl;

import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.google.common.collect.Sets;

import finance.api.model.response.ResponseResult;
import finance.core.common.constant.Constant;
import finance.core.common.enums.CodeEnum;
import finance.core.common.enums.SmsUseType;
import finance.domain.dto.SendSmsCodeDto;
import finance.domainservice.service.sms.SmsBiz;
import finance.domainservice.service.sms.SmsService;
import finance.domainservice.service.validate.ImgValidateService;
import finance.domainservice.service.validate.SmsValidateService;
import finance.mapper.FinanceSmsSendLogDAO;
import finance.model.po.FinanceSmsSendLog;

/**
 * @author yaolei
 * @Title: SmsBizImpl
 * @ProjectName finance-app
 * @Description: 发送短信
 * @date 2018/7/6下午5:42
 */
@Slf4j
@Service
public class SmsBizImpl implements SmsBiz {

    @Resource
    private SmsService           smsServize;
    @Resource
    private SmsValidateService   smsValidateService;
    @Resource
    private ImgValidateService   imgValidateService;
    @Resource
    private FinanceSmsSendLogDAO smsSendLogMapper;

    @Override
    public ResponseResult<String> sendSmsValidateCode(SendSmsCodeDto paramDto) {
        String mobileNum = paramDto.getMobileNum();
        String vidateCode = this.getSmsVidateCode();
        String smsBody = "您的验证码为：" + vidateCode + "，5分钟内有效，如非本人操作，请忽略此短信。";

        //  白名单
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
        Boolean vaildateRes = this.vidateImgCode(paramDto.getUseType(), paramDto.getImgCodeId(),
            paramDto.getImgCode());
        if (!vaildateRes) {
            return ResponseResult.error(CodeEnum.smsImgValidateFail);
        }
        // 发送
        ResponseResult<String> sendRes = smsServize.sendMsg(mobileNum, smsHeader + smsBody);

        // 缓存短信验证码
        smsValidateService.cacheSmsVidateCode(mobileNum, vidateCode, paramDto.getUseType());

        // 记录日志
        FinanceSmsSendLog smsSendLog = new FinanceSmsSendLog();
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
            imgValidateRes = imgValidateService.vidateImgCode(imgCodeId, imgCode);
        }
        return imgValidateRes;
    }

    /**
     * 生成短信验证码.
     * @return
     * @author hewenbin
     * @version SmsBizImpl.java, v1.0 2018年7月11日 上午10:06:55 hewenbin
     */
    private String getSmsVidateCode() {
        StringBuffer charValue = new StringBuffer();
        String str = "0123456789";
        Random random = new Random();
        for (int i = 0; i < Constant.smscode_length; i++) {
            int number = random.nextInt(str.length());
            charValue.append(str.charAt(number));
        }
        return charValue.toString();
    }
}
