package finance.domainservice.service.sms;

import finance.api.model.response.ResponseResult;

/**
 * 短信服务
 * @author yaolei
 * @Title: SmsBiz
 * @ProjectName finance-app
 * @Description: 
 * @date 2018/7/6下午5:41
 */
public interface SmsService {

    /**
     * 发送短信
     * @param mobile
     * @param message 短信内容，短信签名请调用者自己添加
     * @return
     * @author yaolei
     * @version SmsService.java, v1.0 2018年7月6日 上午11:05:10 yaolei
     */
    ResponseResult<String> sendMsg(String mobile, String message);

}
